import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int[] n = Stream.of(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(new Random(n[1] + n[2]).ints(n[0], n[1], n[2] + 1).sum());
    }
}
