package ru.hse.hw1;

import ru.hse.hw1.complex.ComplexNumber;

public class MainClass {
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(0, -1);
        ComplexNumber b = new ComplexNumber(5, -2);

        ComplexNumber c = a.root(7)[2];

        System.out.print(c.getReal() + " " + c.getImag());
    }
}
