package flashcards;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Flashcards {

    enum State {
        MAIN_MENU, ADD_TERM, ADD_DEF, REMOVING, IMPORT_CARDS, EXPORT_CARDS, BEGIN_ASKING, ASK_QUESTION, LOG, EXIT
    }

    private final Map<String, String> map = new LinkedHashMap<>();
    private final Map<String, Integer> errorMap = new LinkedHashMap<>();
    private final List<String> logList = new ArrayList<>();
    private String[] questions;
    private String[] answers;
    private String lastKey;
    private String exportFile = "";
    private State state;
    private boolean finalExportExit = false;
    private boolean hasExport = false;
    private int times;

    public Flashcards(String[] args) {
        processArgs(args);
    }

    private void processArgs(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        if (list.contains("-export")) {
            hasExport = true;
            exportFile = list.get(list.indexOf("-export") + 1);
        }
        if (list.contains("-import")) {
            importCards(list.get(list.indexOf("-import") + 1));
        } else {
            setDefaultState();
        }

    }

    public void execute(String input) {
        logList.add(input);
        switch (state) {
            case MAIN_MENU: processInput(input); break;
            case ADD_TERM: addTerm(input); break;
            case ADD_DEF: addDefinition(input); break;
            case REMOVING: removeCard(input); break;
            case IMPORT_CARDS: importCards(input); break;
            case EXPORT_CARDS: exportCards(input); break;
            case BEGIN_ASKING: getNumberOfTimes(input); break;
            case ASK_QUESTION: quiz(input); break;
            case LOG: exportLog(input); break;
            default: setDefaultState();
        }
    }

    private void processInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "add": addCard(); break;
            case "remove": removeCardMessage(); break;
            case "import": importCardsMessage(); break;
            case "export": exportCardsMessage(); break;
            case "ask": askHowMany(); break;
            case "exit": shutdown(); break;
            case "log": logMessage(); break;
            case "hardest card": getHardestCard(); break;
            case "reset stats": resetStats(); break;
            default: setDefaultState();
        }
    }

    private void addCard() {
        state = State.ADD_TERM;
        print("The card:");
    }

    private void addTerm(String input) {
        if (map.containsKey(input)) {
            print(String.format("The card \"%s\" already exists.", input));
            setDefaultState();
        } else {
            state = State.ADD_DEF;
            lastKey = input;
            print("The definition of the card:");
        }
    }

    private void addDefinition(String input) {
        if (map.containsValue(input)) {
            print(String.format("The definition \"%s\" already exists.", input));
        } else {
            map.put(lastKey, input);
            print(String.format("The pair (\"%s\":\"%s\") has been added.", lastKey, input));
        }
        setDefaultState();
    }

    private void removeCardMessage() {
        state = State.REMOVING;
        print("The card:");
    }

    private void removeCard(String input) {
        if (map.containsKey(input)) {
            map.remove(input);
            errorMap.remove(input);
            print("The card has been removed.");
        } else {
            print(String.format("Can't remove \"%s\": there is no such card.", input));
        }
        setDefaultState();
    }

    private void importCardsMessage() {
        state = State.IMPORT_CARDS;
        print("File name:");
    }

    private void importCards(String input) {
        File file = new File(input);
        try (Scanner scan = new Scanner(file)) {
            int count = 0;
            while (scan.hasNext()) {
                String[] strings = scan.nextLine().split("/");
                map.put(strings[0], strings[1]);
                errorMap.put(strings[0], Integer.parseInt(strings[2]));
                count++;
            }
            print(String.format("%d cards have been loaded.", count));
        } catch (IOException e) {
            print("File not found.");
        } finally {
            setDefaultState();
        }
    }

    private void exportCardsMessage() {
        state = State.EXPORT_CARDS;
        print("File name:");
    }

    private void exportCards(String input) {
        File file = new File(input);
        try (PrintWriter writer = new PrintWriter(file)) {
            for (var entry : map.entrySet()) {
                int errors = 0;
                if (errorMap.containsKey(entry.getKey())) {
                    errors = errorMap.get(entry.getKey());
                }
                writer.println(entry.getKey() + "/" + entry.getValue() + "/" + errors);
            }
            print(String.format("%d cards have been saved.", map.size()));
        } catch (IOException e) {
            print("Something went wrong");
        } finally {
            if (!finalExportExit) {
                setDefaultState();
            }
        }
    }

    private void askHowMany() {
        if (map.isEmpty()) {
            print("There are no cards yet.");
            setDefaultState();
        } else {
            state = State.BEGIN_ASKING;
            print("How many times to ask?");
        }
    }

    private void getNumberOfTimes(String input) {
        try {
            times = Integer.parseInt(input);
            prepareQuiz();
        } catch (NumberFormatException e) {
            print("\nYou must enter a number, please try again:");
        }
    }

    private void prepareQuiz() {
        questions = new String[times];
        answers = new String[times];
        int len = times;
        while (times > 0) {
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (times-- > 0) {
                    questions[len - times - 1] = e.getKey();
                    answers[len - times - 1] = e.getValue();
                } else {
                    break;
                }
            }
        }
        times = len;
        state = State.ASK_QUESTION;
        askQuestion();
    }

    private void askQuestion() {
        if (times-- > 0) {
            String question = questions[questions.length - times - 1];
            print(String.format("\nPrint the definition of \"%s\":", question));
        } else {
            setDefaultState();
        }
    }

    private void quiz(String input) {
        int len = answers.length - times - 1;
        String answer = answers[len];
        String term = questions[len];
        if (times >= 0) {
            if (answer.equals(input)) {
                print("Correct answer.");
            } else if (map.containsValue(input)) {
                String key = map.entrySet().stream().filter(f -> f.getValue().equals(input)).map(Map.Entry::getKey)
                        .collect(Collectors.joining());
                print(String.format("Wrong answer. The correct one is \"%s\", you've just written the " +
                        "definition of \"%s\".", answer, key));
                errorMap.put(term, errorMap.getOrDefault(term,0) + 1);
            } else {
                print(String.format("Wrong answer. The correct one is \"%s\".", answer));
                errorMap.put(term, errorMap.getOrDefault(term,0) + 1);
            }
            askQuestion();
        } else {
            setDefaultState();
        }
    }

    private void shutdown() {
        print("Goodbye!");
        if (hasExport) {
            finalExportExit = true;
            exportCards(exportFile);
        }
        state = State.EXIT;
    }

    private void logMessage() {
        state = State.LOG;
        print("File name:");
    }

    private void exportLog(String input) {
        File file = new File(input);
        try (PrintWriter writer = new PrintWriter(file)) {
            logList.forEach(writer::println);
            print("The log has been saved.");
        } catch (IOException e) {
            print("Something went wrong");
        } finally {
            setDefaultState();
        }
    }

    private void getHardestCard() {
        if (!errorMap.isEmpty()) {
            int max = Collections.max(errorMap.entrySet(), Map.Entry.comparingByValue()).getValue();
            StringBuilder keys = new StringBuilder();
            var iterator = errorMap.entrySet().iterator();
            keys.append(String.format("\"%s\"", iterator.next().getKey()));
            while (iterator.hasNext()) {
                keys.append(", ").append(String.format("\"%s\"", iterator.next().getKey()));
            }
            if (keys.indexOf(",") < 0) {
                print(String.format("The hardest card is %s. You have %d errors answering it.", keys, max));
            } else {
                print(String.format("The hardest cards are %s. You have %d errors answering them.", keys, max));
            }
        } else {
            print("There are no cards with errors.");
        }
        setDefaultState();
    }

    private void resetStats() {
        errorMap.clear();
        print("Card statistics has been reset.");
        setDefaultState();
    }

    private void print(String output) {
        System.out.println(output);
        logList.add(output);
    }

    public State getState() {
        return state;
    }

    private void setDefaultState() {
        state = State.MAIN_MENU;
        print("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, " +
                "reset stats):");
    }

}
