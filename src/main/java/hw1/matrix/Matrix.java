package ru.hse.hw1.matrix;

import ru.hse.hw1.complex.ComplexNumber;

/**
 *
 */
public class Matrix {
    private ComplexNumber[][] data;
    private int rows;
    private int columns;


    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new ComplexNumber[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setElementAt(int i, int j, ComplexNumber value) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            throw new IndexOutOfBoundsException("Invalid matrix index");
        }
        data[i][j] = value;
    }

    public ComplexNumber getElementAt(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            throw new IndexOutOfBoundsException("Invalid matrix index");
        }
        return data[i][j].clone();
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Cannot add matrices of different sizes");
        }
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[i][j] = this.data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }


    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Cannot subtract matrices of different sizes");
        }
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[i][j] = this.data[i][j].subtract(other.data[i][j]);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows) {
            throw new IllegalArgumentException("Matrices cannot be multiplied, dimensions do not match");
        }

        Matrix result = new Matrix(this.rows, other.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.columns; k++) {
                    sum = sum.add(this.data[i][k].multiply(other.data[k][j]));
                }
                result.setElementAt(i, j, sum);
            }
        }

        return result;
    }

    public Matrix multiply(ComplexNumber scalar) {
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[i][j] = this.data[i][j].multiply(scalar);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    public ComplexNumber determinant() {
        if (rows != columns) {
            throw new UnsupportedOperationException("Determinant is defined only for square matrices");
        }
        ComplexNumber[][] A = new ComplexNumber[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                A[i][j] = new ComplexNumber(data[i][j].getReal(), data[i][j].getImag());
            }
        }
        ComplexNumber det = new ComplexNumber(1.0, 0.0);
        for (int k = 0; k < rows - 1; k++) {
            for (int i = k + 1; i < rows; i++) {
                ComplexNumber factor = A[i][k].divide(A[k][k]);
                for (int j = k + 1; j < rows; j++) {
                    A[i][j] = A[i][j].subtract(factor.multiply(A[k][j]));
                }
            }
            det = det.multiply(A[k][k]);
        }
        det = det.multiply(A[rows - 1][rows - 1]);
        return det;
    }

    private static void setData(Matrix source, Matrix destination) {
        destination.data = source.data;
        destination.rows = source.rows;
        destination.columns = source.columns;
    }

    public static void add(Matrix first, Matrix second, Matrix result) {
        try {
            Matrix.setData(first.add(second), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Cannot add matrices of different sizes\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    public static void subtract(Matrix first, Matrix second, Matrix result) {
        try {
            Matrix.setData(first.subtract(second), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Cannot subtract matrices of different sizes\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    public static void multiply(Matrix first, Matrix second, Matrix result) {
        try {
            Matrix.setData(first.multiply(second), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Matrices cannot be multiplied, dimensions do not match\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    public static void multiply(Matrix first, ComplexNumber scalar, Matrix result) {
        try {
            Matrix.setData(first.multiply(scalar), result);
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    public static void determinant(Matrix first, ComplexNumber result) {
        try {
            ComplexNumber temp = first.determinant();
            result.setReal(temp.getReal());
            result.setImag(temp.getImag());
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Determinant is defined only for square matrices\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }
}
