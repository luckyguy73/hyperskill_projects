import java.util.Scanner;
import java.util.regex.*;

class Main {

    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("(?i)program\\w*\\b").matcher(new Scanner(System.in).nextLine());
        while (matcher.find()) {
            System.out.printf("%s %s\n", matcher.start(), matcher.group());
        }
    }

}
