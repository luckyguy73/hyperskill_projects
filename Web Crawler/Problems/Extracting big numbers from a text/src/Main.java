import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\d{10,}").matcher(new Scanner(System.in).nextLine());
        while (matcher.find()) {
            System.out.printf("%s:%s\n", matcher.group(), matcher.end() - matcher.start());
        }
    }

}
