import java.util.*;
import java.util.stream.Stream;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        return new TreeMap<>(){{ Stream.of(strings).forEach(s -> put(s, getOrDefault(s, 0) + 1)); }};
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((k, v) -> System.out.printf("%s : %s\n", k, v));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}
