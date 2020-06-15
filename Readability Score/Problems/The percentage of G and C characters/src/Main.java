import java.util.Scanner;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        System.out.println((double) Pattern.compile("(?i)[cg]").matcher(s).results().count() / s.length() * 100);
    }

}
