import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int[][] s = initializeArray();
        int m = s.length;
        int n = s[0].length;
        int[][] e = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                e[i][j] = s[(m + i + 1) % m][j] + s[(m + i - 1) % m][j] + s[i][(n + j + 1) % n] + s[i][(n + j - 1) % n];
                System.out.print(e[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] initializeArray() {
        Scanner s = new Scanner(System.in);
        ArrayList<String[]> list = new ArrayList<>();
        String[] strings;
        while (!(strings = s.nextLine().split(" "))[0].equals("end")) {
            list.add(strings);
        }
        int[][] matrix = new int[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < list.get(0).length; ++j) {
                matrix[i][j] = Integer.parseInt(list.get(i)[j]);
            }
        }
        return matrix;
    }

}
