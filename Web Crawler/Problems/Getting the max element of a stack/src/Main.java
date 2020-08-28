import java.util.*;

class Main {
    public static void main(String[] args) {
        Deque<Integer> stk = new ArrayDeque<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        while (n-- > 0) {
            switch (s.next()) {
                case "push": int i = s.nextInt();
                    stk.push(stk.isEmpty() || i >= stk.peek() ? i : stk.peek());
                    break;
                case "pop": stk.pop();
                    break;
                default: System.out.println(stk.peek());
            }
        }
    }
}
