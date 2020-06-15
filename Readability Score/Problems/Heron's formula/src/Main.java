import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();
        double p = (a + b + c) / 2.0;
        System.out.println(Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}