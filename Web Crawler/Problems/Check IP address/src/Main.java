import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String d = "(25[0-5]|2[0-4][0-9]|[01]?[0-9]?[0-9])";
        String pattern = String.format("%1$s\\.%1$s\\.%1$s\\.%1$s", d);
        boolean valid = input.matches(pattern);
        System.out.println(valid ? "YES" : "NO");
    }

}
