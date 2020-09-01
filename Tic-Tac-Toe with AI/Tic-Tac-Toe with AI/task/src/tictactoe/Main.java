package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Game game = new Game();
        while (game.getState() != Game.State.END) game.execute(scan.nextLine());
    }

}
