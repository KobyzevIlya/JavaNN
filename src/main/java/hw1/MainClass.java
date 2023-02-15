package ru.hse.hw1;

import ru.hse.hw1.complex.ComplexNumber;
import ru.hse.hw1.console.ConsoleHandler;
import ru.hse.hw1.matrix.Matrix;

public class MainClass {
    public static void main(String[] args) {
//        Matrix a = ConsoleHandler.getMatrix();
//        Matrix b = ConsoleHandler.getMatrix();
//
//        Matrix c = new Matrix(1, 1);
//        Matrix.add(a, b, c);
//
//        ConsoleHandler.printMatrix(c);

        ComplexNumber a = ConsoleHandler.getComplexNumber();
        ComplexNumber b = ConsoleHandler.getComplexNumber();

        ComplexNumber c = a.add(b);
        ConsoleHandler.printComplexNumberInTrigForm(c);
        ConsoleHandler.printComplexNumber(c);
    }
}
