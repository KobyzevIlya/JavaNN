package ru.hse.hw1;

import ru.hse.hw1.complex.ComplexNumber;
import ru.hse.hw1.console.ConsoleHandler;
import ru.hse.hw1.matrix.Matrix;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Matrix a = ConsoleHandler.getMatrix();
        Matrix b = ConsoleHandler.getMatrix();

        Matrix c = new Matrix(2, 2);
        Matrix.add(a, b, c);

        ConsoleHandler.printMatrix(c);
    }
}
