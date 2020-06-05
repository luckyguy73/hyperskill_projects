import java.util.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

class Main {
    public static void main(String[] args) {
        new Scanner(System.in).tokens().map(String::toLowerCase).collect(groupingBy(String::toString, counting()))
                .forEach((s, n) -> System.out.printf("%s %d\n", s, n));
    }
}
