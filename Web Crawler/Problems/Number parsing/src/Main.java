import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String regex = "^[+-]?(?:0|[1-9]\\d*)(?:[.,](?:0|\\d*[1-9]))?$";
        System.out.println(Pattern.compile(regex).matcher(s.nextLine()).matches() ? "YES" : "NO");
    }

}
