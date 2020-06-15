import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        int beg = s.length() % 2 == 0 ? s.length() / 2 - 1 : s.length() / 2;
        System.out.println(s.substring(0, beg) + s.substring(s.length() / 2 + 1));
    }

}
