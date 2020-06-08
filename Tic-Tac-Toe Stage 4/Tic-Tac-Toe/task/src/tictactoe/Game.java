package tictactoe;

import java.util.*;
import java.util.stream.Stream;

public class Game {

    private final String[][] board = new String[3][];
    private final HashSet<String> set = new HashSet<>();
    private String field;
    private String state;
    private boolean hasEmptyCells;
    private boolean oWins;
    private boolean xWins;
    private String c;
    private int oCount;
    private int xCount;

    public Game(String s) {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0, c = 0; c < field.length(); ++r, c += 3)
            board[r] = new String[]{String.valueOf(field.charAt(c)), String.valueOf(field.charAt(c + 1)),
                    String.valueOf(field.charAt(c + 2))};
    }

    public void go() {
        printBoard();
        checkBoard();
        setState();
        printState();
        updateBoard();
    }

    private void printBoard() {
        System.out.println("-".repeat(9));
        for (int r = 0; r < field.length(); r += 3)
            System.out.printf("| %s %s %s |\n", field.charAt(r), field.charAt(r + 1), field.charAt(r + 2));
        System.out.println("-".repeat(9));
    }

    private void checkBoard() {
        countChars();
        checkRows();
        checkCols();
        checkMainDiag();
        checkAltDiag();
    }

    private void countChars() {
        hasEmptyCells = field.contains("_");
        xCount = (int) field.chars().filter(c -> c == 'X').count();
        oCount = (int) field.chars().filter(c -> c == 'O').count();
    }

    private void checkRows() {
        for (String[] row : board) {
            set.clear();
            for (String col : row) set.add(c = col);
            checkForWinner();
        }
    }

    private void checkCols() {
        for (int col = 0; col < board.length; ++col) {
            set.clear();
            for (int row = 0; row < board[col].length; ++row) set.add(c = board[row][col]);
            checkForWinner();
        }
    }

    private void checkMainDiag() {
        set.clear();
        for (int cell = 0; cell < board.length; ++cell) set.add(c = board[cell][cell]);
        checkForWinner();
    }

    private void checkAltDiag() {
        set.clear();
        for (int i = 0; i < board.length; ++i) set.add(c = board[i][board.length - i - 1]);
        checkForWinner();
    }

    private void checkForWinner() {
        if (set.size() == 1 && !set.contains("_")) {
            c = set.stream().findAny().get();
            if (c.equals("X")) xWins = true;
            else oWins = true;
        }
    }

    private void setState() {
        int diff = Math.abs(xCount - oCount);
        if (xWins && oWins || diff > 1) state = "Impossible";
        else if (!xWins && !oWins && !hasEmptyCells) state = "Draw";
        else if (xWins) state = "X wins";
        else if (oWins) state = "O wins";
        else state = "Game not finished";
    }

    private void printState() { System.out.println(state); }

    private void updateBoard() {
        Scanner scan = new Scanner(System.in);
        int num1, num2;
        boolean loop = true;
        while (loop) {
            System.out.print("Enter the coordinates: ");
            String input = scan.nextLine();
            if (Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(2))) {
                num1 = Integer.parseInt(input.substring(0, 1));
                num2 = Integer.parseInt(input.substring(2));
                if (num1 > 0 && num1 < 4 & num2 > 0 & num2 < 4) {
                    int[] nums = convertCoordinates(num1, num2);
                    if (board[nums[0]][nums[1]].equals("_")) {
                        board[nums[0]][nums[1]] = "X";
                        loop = false;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        scan.close();
        field = String.join("", Stream.of(board).flatMap(Stream::of).toArray(String[]::new));
        printBoard();
    }

    private int[] convertCoordinates(int first, int second) {
        int row = 3 - second;
        int col = first - 1;
        return new int[]{row, col};
    }

}
