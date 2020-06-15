import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        boolean substring = true;
        for (int i = 1; i < s.length() && substring; ++i) {
            if (s.charAt(i) - s.charAt(i - 1) != 1) {
                substring = false;
            }
        }
        System.out.println(substring);
    }

}
