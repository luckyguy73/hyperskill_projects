import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] array = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                array[i][j] = s.nextInt();
            }
        }
        boolean answer = true;
        for (int i = 0; i < n && answer; ++i) {
            for (int j = 0; j < n && answer; ++j) {
                answer = array[i][j] == array[j][i];
            }
        }
        System.out.println(answer ? "YES" : "NO");
    }

}
