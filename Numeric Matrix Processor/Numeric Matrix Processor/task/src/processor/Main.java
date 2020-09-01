package processor;

import java.util.Scanner;

public class Main {
    enum State { MAIN_MENU, TRANSPOSE, EXIT }

    private static State state;
    private static Matrix A;
    private static Matrix B;

    static { setDefault(); }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (state != State.EXIT) execute(scan.nextLine());
    }

    private static void setDefault() {
        state = State.MAIN_MENU;
        A = null;
        B = null;
        displayMenu();
    }

    private static void displayMenu() {
        String menu =   "1) Add matrices\n" +
                        "2) Multiply matrix to a constant\n" +
                        "3) Multiply matrices\n" +
                        "4) Transpose matrix\n" +
                        "5) Calculate a determinant\n" +
                        "6) Inverse Matrix\n" +
                        "0) Exit\n\n" +
                        "Your choice: ";
        System.out.print("\n" + menu);
    }

    private static void execute(String command) {
        switch (state) {
            case MAIN_MENU: mainMenu(command); break;
            case TRANSPOSE: transposeMatrix(command); break;
            case EXIT: exitProgram(); break;
            default: setDefault();
        }
    }

    private static void mainMenu(String command) {
        switch (command) {
            case "1": addMatrices(); break;
            case "2": multiplyByConstant(); break;
            case "3": multiplyMatrices(); break;
            case "4": transposeMenu(); break;
            case "5": calculateDeterminant(); break;
            case "6": inverseMatrix(); break;
            case "0": exitProgram(); break;
            default: setDefault();
        }
    }

    private static void transposeMenu() {
        String menu =   "1) Main diagonal\n" +
                        "2) Side diagonal\n" +
                        "3) Vertical line\n" +
                        "4) Horizontal line\n\n" +
                        "Your choice: ";
        System.out.print("\n" + menu);
        state = State.TRANSPOSE;
    }

    private static void addMatrices() {
        createMatrix();
        createMatrix();
        Matrix X = Matrix.addTwoMatrices(A, B);
        System.out.println("\nThe addition result is: ");
        X.displayMatrix();
        setDefault();
    }

    private static void multiplyByConstant() {
        createMatrix();
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter constant to multiply matrix by: ");
        int constant = scan.nextInt();
        Matrix X = Matrix.multiplyByConstant(A, constant);
        System.out.printf("\nThe result of multiplying by %d is: \n", constant);
        X.displayMatrix();
        setDefault();
    }

    private static void multiplyMatrices() {
        createMatrix();
        createMatrix();
        Matrix X = Matrix.multiplyMatrices(A, B);
        System.out.println("\nThe multiplication result is: ");
        if (X != null) X.displayMatrix();
        else System.out.println("\nThe first matrix columns and second matrix rows must be equal!");
        setDefault();
    }

    private static void transposeMatrix(String command) {
        createMatrix();
        Matrix X;
        switch (command) {
            case "1": X = Matrix.transposeMainDiagonal(A); break;
            case "2": X = Matrix.transposeSideDiagonal(A); break;
            case "3": X = Matrix.transposeVerticalLine(A); break;
            case "4": X = Matrix.transposeHorizontalLine(A); break;
            default: X = A;
        }
        System.out.println("\nThe result is: ");
        X.displayMatrix();
        setDefault();
    }

    private static void calculateDeterminant() {
        createMatrix();
        double determinant = Matrix.determinant(A.getData());
        System.out.println("\nThe result is: ");
        System.out.println(determinant);
        setDefault();
    }

    private static void inverseMatrix() {
        createMatrix();
        Matrix X = Matrix.inverse(A);
        System.out.println("\nThe result is: ");
        X.displayMatrix();
        setDefault();
    }

    private static void createMatrix() {
        boolean isFirst = A == null;
        String s = isFirst ? "first" : "second";
        System.out.printf("\nEnter size of %s matrix: ", s);
        Scanner scan = new Scanner(System.in);
        Matrix X = new Matrix(scan.nextInt(), scan.nextInt());
        System.out.println("Enter matrix data: ");
        double[][] data = new double[X.getRows()][X.getCols()];
        for (int i = 0; i < X.getRows(); i++)
            for (int j = 0; j < X.getCols(); j++) data[i][j] = scan.nextDouble();
        X.setData(data);
        if (isFirst) A = X;
        else B = X;
    }

    private static void exitProgram() {
        state = State.EXIT;
        System.out.println("\nExiting program...");
        System.out.println("Goodbye!");
    }
}
