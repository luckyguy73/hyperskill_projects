import java.util.*;
import java.util.regex.*;

class Main {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        Matcher m = Pattern.compile("<.+?>").matcher(input);
        Deque<String> stack = new ArrayDeque<>();
        while (m.find()) {
            if (m.group().contains("/")) {
                System.out.println(stack.pop());
            } else {
                Matcher p = Pattern.compile("<(\\w+)>(.*?)</\\1>").matcher(input);
                p.find(m.start());
                stack.push(p.group(2));
            }
        }
    }
}
