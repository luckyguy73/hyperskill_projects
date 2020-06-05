import java.util.*;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Map<String, Integer> m1 = new HashMap<>() {{ Stream.of(s.nextLine().toLowerCase().split(""))
                .forEach(s -> put(s, getOrDefault(s, 0) + 1)); }};
        Map<String, Integer> m2 = new HashMap<>() {{ Stream.of(s.nextLine().toLowerCase().split(""))
                .forEach(s -> put(s, getOrDefault(s, 0) + 1)); }};
        int n = m1.values().stream().reduce(0, Integer::sum) + m2.values().stream().reduce(0, Integer::sum);
        m1.forEach((k, v) -> m1.put(k, Math.min(v, m2.getOrDefault(k, 0))));
        m1.entrySet().removeIf(e -> e.getValue() < 1);
        System.out.println(n - (m1.values().stream().reduce(0, Integer::sum) * 2));
    }
}
