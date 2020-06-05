import java.util.*;
import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        String s2 = s.nextLine();
        go(s.nextLine(), s1, s2);
        go(s.nextLine(), s2, s1);
    }

    private static void go(String string, String en, String de) {
        char[] chars = string.toCharArray();
        IntStream.range(0, chars.length).forEach(i -> chars[i] = de.charAt(en.indexOf(chars[i])));
        System.out.println(new String(chars));
    }

}
