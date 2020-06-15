import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        String[] counts = new Scanner(System.in).nextLine().split("\\s+");
        String[] upper = generateTokens('A', 'Z');
        String[] lower = generateTokens('a', 'z');
        Integer[] nums = IntStream.rangeClosed(0, 9).boxed().toArray(Integer[]::new);
        StringBuilder sb = new StringBuilder();
        generatePassword(Integer.parseInt(counts[0]), upper.length, upper, sb);
        generatePassword(Integer.parseInt(counts[1]), lower.length, lower, sb);
        generatePassword(Integer.parseInt(counts[3]) - sb.length(), nums.length, nums, sb);
        System.out.println(sb);
    }

    private static String[] generateTokens(char a, char z) {
        return IntStream.rangeClosed(a, z).mapToObj(m -> "" + (char) m).toArray(String[]::new);
    }

    private static <T> void generatePassword(int len, int n, T[] arr, StringBuilder sb) {
        for (int i = len - 1; i >= 0; --i) {
            sb.append(arr[i % n]);
        }
    }

}
