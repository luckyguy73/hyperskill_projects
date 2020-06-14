import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        StringReverser reverser = new StringReverser() {
            @Override
            public String reverse(String str) {
                return new StringBuilder(str).reverse().toString();
            }
        };
        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);

    }

}
