import java.util.*;

class Main {

    public static void main(String[] args) {
        Arrays.stream(new Scanner(System.in).nextLine().split("")).forEach(s -> System.out.print(s.repeat(2)));
    }

}
