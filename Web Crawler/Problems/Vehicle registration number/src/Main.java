import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regNum = scanner.nextLine();
        String pattern = "\\s*[ABCEHKMOPTXY]\\d{3}[ABCEHKMOPTXY]{2}\\s*";
        boolean valid = regNum.matches(pattern);
        System.out.println(valid);
    }

}
