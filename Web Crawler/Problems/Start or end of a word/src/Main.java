import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Pattern pattern = Pattern.compile(String.format("(?i)\\b%1$s|%1$s\\b", s.nextLine()));
        System.out.println(pattern.matcher(s.nextLine()).find() ? "YES" : "NO");
    }

}
