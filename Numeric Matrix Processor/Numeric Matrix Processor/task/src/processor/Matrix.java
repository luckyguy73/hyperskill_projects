package processor;

public class Matrix {
    private final int rows;
    private final int cols;
    private double[][] data;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public static Matrix transposeMainDiagonal(Matrix A) {
        Matrix M = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getCols(); j++)
                M.data[i][j] = A.data[j][i];
        return M;
    }

    public static Matrix transposeSideDiagonal(Matrix A) {
        Matrix M = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getCols(); j++)
                M.data[i][j] = A.data[A.getRows() - j - 1][A.getCols() - i - 1];
        return M;
    }

    public static Matrix transposeVerticalLine(Matrix A) {
        Matrix M = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getCols(); j++)
                M.data[i][j] = A.data[i][A.getCols() - j - 1];
        return M;
    }

    public static Matrix transposeHorizontalLine(Matrix A) {
        Matrix M = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getCols(); j++)
                M.data[i][j] = A.data[A.getRows() - i - 1][j];
        return M;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void displayMatrix() {
        for (int i = 0; i < getRows(); ++i) {
            for (int j = 0; j < getCols(); ++j)
                System.out.print(getData()[i][j] + " ");
            System.out.println();
        }
    }

    public static Matrix addTwoMatrices(Matrix A, Matrix B) {
        Matrix C = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < C.getRows(); ++i)
            for (int j = 0; j < C.getCols(); ++j)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    public static Matrix multiplyByConstant(Matrix A, int constant) {
        Matrix C = new Matrix(A.getRows(), A.getCols());
        for (int i = 0; i < C.getRows(); ++i)
            for (int j = 0; j < C.getCols(); ++j)
                C.data[i][j] = A.data[i][j] * constant;
        return C;
    }

    public static Matrix multiplyMatrices(Matrix A, Matrix B) {
        if (A.getCols() != B.getRows()) return null;
        Matrix out = new Matrix(A.getRows(), B.getCols());
        for (int i = 0; i < A.getRows(); ++i)
            for (int j = 0; j < B.getCols(); ++j)
                for (int k = 0; k < A.getCols(); ++k)
                    out.data[i][j] += A.data[i][k] * B.data[k][j];
        return out;
    }
}
