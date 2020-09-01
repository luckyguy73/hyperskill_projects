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

    public static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; ++i)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(minor(matrix, 0, i));
        return det;
    }

    public static Matrix inverse(Matrix A) {
        double[][] matrix = A.getData();
        double[][] inverse = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[i].length; ++j)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

        double det = 1.0 / determinant(matrix);

        for (int i = 0; i < inverse.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        Matrix M = new Matrix(matrix.length, matrix.length);
        M.setData(inverse);
        return M;
    }

    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }
}
