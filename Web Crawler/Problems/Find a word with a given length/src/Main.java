import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(?i)\\b[a-zA-Z]{" + Integer.parseInt(s.nextLine()) + "}\\b");
        System.out.println(pattern.matcher(s.nextLine()).find() ? "YES" : "NO");
    }

}
