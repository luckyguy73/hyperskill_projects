import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String string = new Scanner(System.in).nextLine();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < string.length(); ++i) {
            count++;
            if (i == string.length() - 1 || string.charAt(i + 1) != string.charAt(i)) {
                sb.append(string.charAt(i)).append(--count);
                count = 1;
            }
        }
        System.out.println(sb.toString());
    }

}
