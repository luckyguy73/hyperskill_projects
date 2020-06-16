import java.util.Scanner;

class RemoveExtraSpacesProblem {

    public static void main(String[] args) {
        System.out.println(new Scanner(System.in).nextLine().replaceAll("\\s+", " "));
    }

}
