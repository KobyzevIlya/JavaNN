package ru.hse.hw1.console;

import ru.hse.hw1.complex.ComplexNumber;
import ru.hse.hw1.matrix.Matrix;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for handling console input/output operations
 */
public class ConsoleHandler {
    private ConsoleHandler() {
    }

    /**
     * Prompts the user to input a complex number in the form a + bi.
     *
     * @return a ComplexNumber object with the user-specified real and imaginary parts
     */
    public static ComplexNumber getComplexNumber() {
        System.out.print("->complex number: a + bi. Insert a:");
        Scanner scanner = new Scanner(System.in);

        double first = 0;
        double second = 0;

        try {
            first = scanner.nextDouble();
        } catch (InputMismatchException exception) {
            System.out.print("<-\n->wrong input. Try again<-");
            System.exit(0);
        }

        System.out.print("->complex number: a + bi. Insert b:");

        try {
            second = scanner.nextDouble();
        } catch (InputMismatchException exception) {
            System.out.print("<-\n->wrong input. Try again<-");
            System.exit(0);
        }

        return new ComplexNumber(first, second);
    }

    /**
     * Prompts the user to input the number of rows and columns for a matrix.
     *
     * @return a Matrix object with the user-specified dimensions and elements
     */
    public static Matrix getMatrix() {
        Scanner scanner = new Scanner(System.in);
        int rows = 0;
        int columns = 0;

        System.out.print("->Insert rows count<-\n");
        try {
            rows = scanner.nextInt();
            if (rows <= 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            System.out.print("<-\n->wrong input. Try again<-");
            System.exit(0);
        }

        System.out.print("->Insert columns count<-\n");
        try {
            columns = scanner.nextInt();
            if (columns <= 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            System.out.print("<-\n->wrong input. Try again<-");
            System.exit(0);
        }

        Matrix matrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                System.out.print("->insert matrix element at i=" + i + " j=" + j + "\n");
                matrix.setElementAt(i, j, getComplexNumber());
            }
        }
        return matrix;
    }

    /**
     * Prints the specified ComplexNumber object in the format a + bi.
     *
     * @param complexNumber the ComplexNumber object to be printed
     */
    public static void printComplexNumber(ComplexNumber complexNumber) {
        System.out.print(complexNumber.getReal() + "_i*" + complexNumber.getImag() + "\n");
    }

    /**
     * Prints the specified ComplexNumber object in trigonometric form, i.e. magnitude * (cos(theta) + i*sin(theta)).
     *
     * @param complexNumber the ComplexNumber object to be printed in trigonometric form
     */
    public static void printComplexNumberInTrigForm(ComplexNumber complexNumber) {
        double magnitude = complexNumber.getModulus();
        double angle = complexNumber.getArgument();

        System.out.print(magnitude + " * (cos(" + angle + ") + i*sin(" + angle + "))\n");
    }

    /**
     * Prints the specified Matrix object to the console.
     *
     * @param matrix the Matrix object to be printed
     */
    public static void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); ++i) {
            for (int j = 0; j < matrix.getColumns(); ++j) {
                System.out.print(matrix.getElementAt(i, j).getReal() + " i*" + matrix.getElementAt(i, j).getImag() + " ");
            }
            System.out.print("\n");
        }
    }
}
