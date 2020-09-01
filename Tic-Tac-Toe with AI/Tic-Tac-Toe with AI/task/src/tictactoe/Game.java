package tictactoe;

import java.util.*;
import java.util.stream.Stream;

public class Game {

    public enum State { TAKE_TURN, END }

    private final String[][] board = new String[3][];
    private final HashSet<String> set = new HashSet<>();
    private State state;
    private String field;
    private String status;
    private boolean hasEmptyCells;
    private boolean oWins;
    private boolean xWins;
    private int oCount;
    private int xCount;

    public Game() {
        this.field = "_".repeat(9);
        initializeBoard();
        getInput();
    }

    private void initializeBoard() {
        for (int r = 0, c = 0; c < field.length(); ++r, c += 3)
            board[r] = new String[]{String.valueOf(field.charAt(c)), String.valueOf(field.charAt(c + 1)),
                    String.valueOf(field.charAt(c + 2))};
        state = State.TAKE_TURN;
        printBoard();
    }

    public void execute(String input) { if (state == State.TAKE_TURN) validateInput(input); }

    private void validateInput(String input) {
        int num1, num2;
        if (Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(2))) {
            num1 = Integer.parseInt(input.substring(0, 1));
            num2 = Integer.parseInt(input.substring(2));
            if (num1 > 0 && num1 < 4 & num2 > 0 & num2 < 4) {
                int[] nums = convertCoordinates(num1, num2);
                if (board[nums[0]][nums[1]].equals("_")) updateBoard(nums);
                else System.out.println("This cell is occupied! Choose another one!");
            } else System.out.println("Coordinates should be from 1 to 3!");
        } else System.out.println("You should enter numbers!");
        if (state != State.END) getInput();
    }

    private void getInput() { System.out.print("Enter the coordinates: "); }

    private void printBoard() {
        System.out.println("-".repeat(9));
        String temp = field.replace("_", " ");
        for (int r = 0; r < temp.length(); r += 3)
            System.out.printf("| %s %s %s |\n", temp.charAt(r), temp.charAt(r + 1), temp.charAt(r + 2));
        System.out.println("-".repeat(9));
    }

    private void updateBoard(int[] nums) {
        board[nums[0]][nums[1]] = "X";
        field = String.join("", Stream.of(board).flatMap(Stream::of).toArray(String[]::new));
        printBoard();
        checkBoard();
        playComputersTurn();
    }

    private void playComputersTurn() {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        while (true) {
            int num1 = random.nextInt(3) + 1, num2 = random.nextInt(3) + 1;
            int[] nums = convertCoordinates(num1, num2);
            if (board[nums[0]][nums[1]].equals("_")) {
                board[nums[0]][nums[1]] = "O";
                break;
            }
        }
        field = String.join("", Stream.of(board).flatMap(Stream::of).toArray(String[]::new));
        printBoard();
        checkBoard();
    }

    private void checkBoard() {
        countChars();
        checkRows();
        checkCols();
        checkMainDiag();
        checkAltDiag();
        setState();
    }

    private void countChars() {
        hasEmptyCells = field.contains("_");
        xCount = (int) field.chars().filter(c -> c == 'X').count();
        oCount = (int) field.chars().filter(c -> c == 'O').count();
    }

    private void checkRows() {
        for (String[] row : board) {
            set.clear();
            set.addAll(Arrays.asList(row));
            checkForWinner();
        }
    }

    private void checkCols() {
        for (int col = 0; col < board.length; ++col) {
            set.clear();
            for (int row = 0; row < board[col].length; ++row) set.add(board[row][col]);
            checkForWinner();
        }
    }

    private void checkMainDiag() {
        set.clear();
        for (int cell = 0; cell < board.length; ++cell) set.add(board[cell][cell]);
        checkForWinner();
    }

    private void checkAltDiag() {
        set.clear();
        for (int i = 0; i < board.length; ++i) set.add(board[i][board.length - i - 1]);
        checkForWinner();
    }

    private void checkForWinner() {
        if (set.size() == 1 && !set.contains("_")) {
            String c = set.stream().findAny().get();
            if (c.equals("X")) xWins = true;
            else oWins = true;
        }
    }

    private void setState() {
        int diff = Math.abs(xCount - oCount);
        if (xWins && oWins || diff > 1) status = "Impossible";
        else if (!xWins && !oWins && !hasEmptyCells) status = "Draw";
        else if (xWins) status = "X wins";
        else if (oWins) status = "O wins";
        else status = "Game not finished";
        if (!status.equals("Game not finished")) {
            state = State.END;
            printState();
        }
    }

    private void printState() { System.out.println(status); }

    private int[] convertCoordinates(int first, int second) {
        int row = 3 - second;
        int col = first - 1;
        return new int[]{row, col};
    }

    public State getState() { return this.state; }

}
