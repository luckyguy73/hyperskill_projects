import java.util.*;
import java.util.regex.*;

class Main {

    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("(?i)password:?\\s*(\\w+)").matcher(new Scanner(System.in).nextLine());
        if (matcher.find()) {
            do {
                System.out.printf("%s\n", matcher.group(1));
            } while (matcher.find());
        } else {
            System.out.println("No passwords found.");
        }
    }

}
