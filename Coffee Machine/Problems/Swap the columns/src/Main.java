import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[][] array = new int[s.nextInt()][s.nextInt()];
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                array[i][j] = s.nextInt();
            }
        }
        int col1 = s.nextInt();
        int col2 = s.nextInt();
        for (int[] row : array) {
            int t = row[col1];
            row[col1] = row[col2];
            row[col2] = t;
            for (int r : row) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }

// I much prefer this format without braces when possible, and multiple assignments per line
/*
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[][] array = new int[s.nextInt()][s.nextInt()];
        for (int i = 0; i < array.length; ++i)
            for (int j = 0; j < array[i].length; ++j) array[i][j] = s.nextInt();
        int col1 = s.nextInt(), col2 = s.nextInt();
        for (int[] row : array) {
            int t = row[col1];
            row[col1] = row[col2];
            row[col2] = t;
            for (int r : row) System.out.print(r + " ");
            System.out.println();
        }
    }
*/

}
