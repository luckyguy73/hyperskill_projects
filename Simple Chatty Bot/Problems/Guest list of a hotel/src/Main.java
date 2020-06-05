//put imports you need here

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> guests = new ArrayList<>();
        while (scan.hasNext()) guests.add(scan.next());
        Collections.reverse(guests);
        guests.forEach(System.out::println);
    }
}
