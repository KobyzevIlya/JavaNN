package ru.hse.hw1.matrix;

import ru.hse.hw1.complex.ComplexNumber;

public class Matrix {
    private final ComplexNumber[][] data;
    private final int rows;
    private final int cols;


    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ComplexNumber[rows][cols];
    }

    public Matrix add(Matrix other) {
//        if (this.rows != other.rows || this.cols != other.cols) {
//            throw new IllegalArgumentException("Cannot add matrices of different sizes.");
//        }
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.data[i][j] = this.data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }


    public Matrix subtract(Matrix other) {
//        if (this.rows != other.rows || this.cols != other.cols) {
//            throw new IllegalArgumentException("Cannot subtract matrices of different sizes.");
//        }
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.data[i][j] = this.data[i][j].subtract(other.data[i][j]);
            }
        }
        return result;
    }

    public Matrix multiply(ComplexNumber scalar) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.data[i][j] = this.data[i][j].multiply(scalar);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

}
