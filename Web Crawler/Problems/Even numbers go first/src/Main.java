import java.util.*;

class Main {

    public static void main(String[] args) {
        Deque<Integer> q = new ArrayDeque<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int e;
        while (n-- > 0) {
            if ((e = s.nextInt()) % 2 == 0) {
                q.offerFirst(e);
            } else {
                q.offerLast(e);
            }
        }
        q.forEach(System.out::println);
    }

}
