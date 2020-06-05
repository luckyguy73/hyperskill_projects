package flashcards;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Flashcards cards = new Flashcards(args);
        while (cards.getState() != Flashcards.State.EXIT) {
            cards.execute(scan.nextLine());
        }
    }

}
