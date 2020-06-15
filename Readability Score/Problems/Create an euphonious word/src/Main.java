import java.util.*;

public class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        int changes = 0;
        int count = 0;
        boolean isVowel = true;
        for (int i = 0; i < s.length(); ++i) {
            count++;
            if ("aeiouy".indexOf(s.charAt(i)) >= 0 != isVowel) {
                isVowel = !isVowel;
                count = 1;
            }
            if (count == 3) {
                changes++;
                count = 1;
            }
        }
        System.out.println(changes);
    }

}
