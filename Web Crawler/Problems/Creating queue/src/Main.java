import java.util.*;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        List.of(7, 1, 0, 2).forEach(queue::push);
        System.out.println(queue);
    }

}
