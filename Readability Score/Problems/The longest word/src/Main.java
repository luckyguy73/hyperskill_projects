import java.util.*;

class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.stream(new Scanner(System.in).nextLine().split(" "))
                .max(Comparator.comparing(String::length)).orElse(""));
    }

}
