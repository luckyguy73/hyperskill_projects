import java.util.Scanner;

class StringProcessor extends Thread {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println(s.matches("^[A-Z]+$") ? "FINISHED" : s.toUpperCase());
        }
    }

}
