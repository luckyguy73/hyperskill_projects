import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String string = s.next();
        int n = s.nextInt();
        if (n < string.length()) {
            string = string.substring(n) + string.substring(0, n);
        }
        System.out.println(string);
    }

}
