import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numberOfKnownWords = Integer.parseInt(s.nextLine());
        Set<String> knownWords = new HashSet<>();
        Set<String> words = new HashSet<>();
        for (int i = 0; i < numberOfKnownWords; ++i) {
            knownWords.add(s.nextLine().toLowerCase());
        }
        int numberOfUnknownWords = Integer.parseInt(s.nextLine());
        for (int i = 0; i < numberOfUnknownWords; ++i) {
            String strings = s.nextLine().toLowerCase();
            words.addAll(Arrays.asList(strings.split(" ")));
        }
        words.removeAll(knownWords);
        words.forEach(System.out::println);
    }
}
