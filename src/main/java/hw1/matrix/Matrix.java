package hw1.matrix;

import hw1.complex.ComplexNumber;

/**
 * <code>ComplexNumber</code> matrix class. Supports addition, subtraction, multiplication by another matrix,
 * multiplication by a number,transposition, and determinant acquisition.
 * These methods come in two variants - a regular method that can throw an exception and a static method that
 * handles exceptions.
 */
public class Matrix {
    private ComplexNumber[][] data;
    private int rows;
    private int columns;


    /**
     * Constructor
     * @param rows number of rows
     * @param columns number of columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new ComplexNumber[rows][columns];
    }

    /**
     * get number of rows in current matrix
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * get number of columns in current matrix
     * @return number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets an element of type <code>ComplexNumber</code> in the specified cell
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @param i cell row
     * @param j cell column
     * @param value element to set
     */
    public void setElementAt(int i, int j, ComplexNumber value) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            throw new IndexOutOfBoundsException("Invalid matrix index");
        }
        data[i][j] = value;
    }

    /**
     * Returns the element in the given matrix cell
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @param i cell ros
     * @param j cell column
     * @return element of ComplexNumber type
     */
    public ComplexNumber getElementAt(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= columns) {
            throw new IndexOutOfBoundsException("Invalid matrix index");
        }
        return data[i][j].clone();
    }

    /**
     * Adds the <code>other</code> matrix to the current one and returns the result as a new matrix
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @param other Matrix type
     * @return new Matrix
     */
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


    /**
     * Subtracts <code>other</code> matrix from the current matrix and returns the result as a new matrix
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @param other Matrix type
     * @return new Matrix
     */
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

    /**
     * Multiplies <code>other</code> matrix by the current matrix and returns the result as a new matrix
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @param other Matrix type
     * @return new Matrix
     */
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

    /**
     * Multiplies each cell of the current matrix by a <code>ComplexNumber</code> number and returns the result as a new matrix
     * @param scalar number
     * @return new Matrix
     */
    public Matrix multiply(ComplexNumber scalar) {
        Matrix result = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[i][j] = this.data[i][j].multiply(scalar);
            }
        }
        return result;
    }

    /**
     * Transforms the current matrix and returns the result as a new matrix
     * @return new Matrix
     */
    public Matrix transpose() {
        Matrix result = new Matrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    /**
     * Calculates the determinant of the current matrix
     * <p>
     * This method can throw exceptions. To avoid them, use static methods
     * @return determinant as <code>Complex number</code>
     */
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

    /**
     * static wrapper over a method <code>add</code> that handles exceptions
     * @param first first matrix to add
     * @param second second matrix to add
     * @param result result will be overwritten
     */
    public static void add(Matrix first, Matrix second, Matrix result) {
        try {
            Matrix.setData(first.add(second), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Cannot add matrices of different sizes\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    /**
     * static wrapper over a method <code>subtract</code> that handles exceptions
     *
     * @param deductible what is subtracted from
     * @param subtracted what is subtracted
     * @param result result will be overwritten
     */
    public static void subtract(Matrix deductible, Matrix subtracted, Matrix result) {
        try {
            Matrix.setData(deductible.subtract(subtracted), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Cannot subtract matrices of different sizes\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    /**
     * static wrapper over a method <code>multiply</code> that handles exceptions
     * @param first first matrix to multiply
     * @param second second matrix to multiply
     * @param result result will be overwritten
     */
    public static void multiply(Matrix first, Matrix second, Matrix result) {
        try {
            Matrix.setData(first.multiply(second), result);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.print("Matrices cannot be multiplied, dimensions do not match\n");
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    /**
     * static wrapper over a method <code>multiply</code> that handles exceptions
     * @param matrix matrix to multiply
     * @param scalar the number by which the matrix is multiplied
     * @param result result will be overwritten
     */
    public static void multiply(Matrix matrix, ComplexNumber scalar, Matrix result) {
        try {
            Matrix.setData(matrix.multiply(scalar), result);
        } catch (Exception exception) {
            System.out.print("Something went wrong. Check arguments and try again\n");
        }
    }

    /**
     * static wrapper over a method <code>determinant</code> that handles exceptions
     * @param first matrix to calculate
     * @param result result will be overwritten
     */
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
