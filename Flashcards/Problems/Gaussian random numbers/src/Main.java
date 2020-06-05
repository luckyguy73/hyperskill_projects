import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        int n = s.nextInt();
        double m = s.nextDouble();
        int index = 0;
        Random random = new Random(k);
        while (true) {
            double d = random.nextGaussian();
            if (d > m) {
                index = 0;
                random = new Random(++k);
            } else if (++index >= n) {
                break;
            }
        }
        System.out.println(k);
    }
}
