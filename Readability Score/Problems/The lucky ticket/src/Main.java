import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i < s.length() / 2) {
                sum1 += s.charAt(i);
            } else {
                sum2 += s.charAt(i);
            }
        }
        System.out.println(sum1 == sum2 ? "Lucky" : "Regular");
    }

}
