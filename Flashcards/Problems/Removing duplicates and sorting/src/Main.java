import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(s.nextLine());
        }
        set.forEach(System.out::println);
    }
}
