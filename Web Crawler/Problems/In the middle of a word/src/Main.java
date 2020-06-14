import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(?i).*\\w+" + s.nextLine() + "\\w+.*");
        System.out.println(pattern.matcher(s.nextLine()).matches() ? "YES" : "NO");
    }

}
