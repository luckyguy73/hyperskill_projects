import java.util.*;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        return this.algorithm.select(persons);
    }

}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);

}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    private final int step;

    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        List<Person> selected = new ArrayList<>();
        for (int i = 0; i < (int) Math.ceil((double) persons.length / step); ++i) {
            selected.add(persons[i * step]);
        }
        return selected.toArray(new Person[0]);
    }

}

class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    private final int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        return Arrays.copyOfRange(persons, persons.length - count, persons.length);
    }

}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }

}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];
        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }
        final String[] configs = scanner.nextLine().split("\\s+");
        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);
        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }

}