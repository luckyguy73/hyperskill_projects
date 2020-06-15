import java.util.Scanner;
import java.util.regex.*;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        String regex = s.nextLine();
        System.out.println(Pattern.compile("(?i)" + regex).matcher(line).results().count());
    }

}
