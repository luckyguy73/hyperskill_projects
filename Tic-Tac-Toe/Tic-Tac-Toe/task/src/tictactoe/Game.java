package tictactoe;

public class Game {

    private char[][] board = new char[3][3];

    public Game(String s) {
        initializeBoard(s);
    }

    private void initializeBoard(String s) {
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                board[row][col] = s.charAt(3 * row + col);
            }
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int row = 0; row < 3; ++row) {
            System.out.print("| ");
            for (int col = 0; col < 3; ++col) {
                System.out.printf("%s ", board[row][col]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
