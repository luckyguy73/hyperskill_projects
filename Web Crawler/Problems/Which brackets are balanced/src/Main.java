import java.util.*;

class Main {

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        Deque<String> stack = new ArrayDeque<>();
        boolean balanced = true;
        for (int i = 0; i < input.length() && balanced; ++i) {
            String s = String.valueOf(input.charAt(i));
            if ("({[".contains(s)) {
                stack.push(s);
            } else {
                if (stack.isEmpty() || "({[".indexOf(stack.pop()) != ")}]".indexOf(s)) {
                    balanced = false;
                }
            }
        }
        System.out.println(balanced && stack.isEmpty());
    }

}
