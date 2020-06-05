import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int[] s = Stream.of(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] output = {s[0], Integer.MAX_VALUE};
        for (int i = s[0]; i <= s[1]; ++i) {
            int randMax = new Random(i).ints(s[2], 0, s[3]).max().orElseThrow();
            if (randMax < output[1]) {
                output[1] = randMax;
                output[0] = i;
            }
        }
        Arrays.stream(output).forEach(System.out::println);
    }
}
