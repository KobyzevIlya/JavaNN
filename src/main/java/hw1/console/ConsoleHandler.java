package ru.hse.hw1.console;

import ru.hse.hw1.complex.ComplexNumber;
import ru.hse.hw1.pair.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHandler {
    private ConsoleHandler() {
    }

    public static Pair<Double> getComplexNumber() {
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

        return new Pair<Double>(first, second);
    }

    public static void printComplexNumber(ComplexNumber complexNumber) {
        System.out.print("->" + complexNumber.getReal() + " " + complexNumber.getImag() + "<-\n");
    }

    public static void printComplexNumberInTrigForm(ComplexNumber complexNumber) {
        double magnitude = complexNumber.getModulus();
        double angle = complexNumber.getArgument();

        System.out.println(magnitude + " * (cos(" + angle + ") + i sin(" + angle + "))");
    }
}
