import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Map<Character, Integer> map1 = getMapFromString(s.nextLine());
        Map<Character, Integer> map2 = getMapFromString(s.nextLine());
        System.out.println(map1.equals(map2) ? "yes" : "no");
    }

    public static Map<Character, Integer> getMapFromString(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : string.toLowerCase().toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}

