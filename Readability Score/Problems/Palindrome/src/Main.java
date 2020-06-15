import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String string = new Scanner(System.in).nextLine();
        System.out.println(string.equals(new StringBuilder(string).reverse().toString()) ? "yes" : "no");
    }

}
