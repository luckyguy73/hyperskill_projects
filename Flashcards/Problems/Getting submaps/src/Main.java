import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SortedMap<Integer, String> map = new TreeMap<>();
        int start = s.nextInt();
        int end = s.nextInt();
        int n = s.nextInt();
        while (n-- > 0) {
            map.put(s.nextInt(), s.next());
        }
        map.subMap(start, end + 1).forEach((k, v) -> System.out.println(k + " " + v));
    }
}
