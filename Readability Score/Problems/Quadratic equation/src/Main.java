import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a = s.nextDouble();
        double b = s.nextDouble();
        double c = s.nextDouble();
        double pos = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        double neg = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        System.out.println(pos < neg ? pos + " " + neg : neg + " " + pos);
    }

}
