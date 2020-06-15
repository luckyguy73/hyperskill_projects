import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println(LocalDate.parse(new Scanner(System.in).nextLine())
                           .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

}
