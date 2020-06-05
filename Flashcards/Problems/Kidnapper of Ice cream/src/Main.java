import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> w = new ArrayList<>(List.of(s.nextLine().split("\\s+")));
        List<String> n = new ArrayList<>(List.of(s.nextLine().split("\\s+")));
        System.out.println(w.containsAll(n) && w.size() >= n.size() ? "You get money" : "You are busted");
    }
}
