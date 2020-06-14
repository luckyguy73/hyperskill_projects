import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String pw = scan.nextLine();
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)\\w{12,}$";
        boolean isHard = pw.matches(pattern);
        System.out.println(isHard ? "YES" : "NO");
    }

}
