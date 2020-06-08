package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter cells: ");
        Game game = new Game(scan.nextLine());
        game.go();
    }

}
