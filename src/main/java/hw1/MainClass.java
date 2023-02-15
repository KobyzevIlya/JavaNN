package ru.hse.hw1;

import ru.hse.hw1.complex.ComplexNumber;
import ru.hse.hw1.console.ConsoleHandler;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(ConsoleHandler.getComplexNumber());
        ComplexNumber b = new ComplexNumber(ConsoleHandler.getComplexNumber());

        ComplexNumber c = a.add(b);

        ConsoleHandler.printComplexNumber(c);
    }
}
