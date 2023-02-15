package ru.hse.hw1.complex;

import ru.hse.hw1.pair.Pair;

public class ComplexNumber implements Cloneable {
    private double real;
    private double imag;

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public ComplexNumber(Pair<Double> number) {
        this(number.getFirst(), number.getSecond());
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imag + other.imag);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imag - other.imag);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imag * other.imag;
        double newReal = (this.real * other.real + this.imag * other.imag) / denominator;
        double newImag = (this.imag * other.real - this.real * other.imag) / denominator;
        return new ComplexNumber(newReal, newImag);
    }

    public double getModulus() {
        return Math.sqrt(real * real + imag * imag);
    }

    public double getArgument() {
        return Math.atan2(imag, real);
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    @Override
    public ComplexNumber clone() {
        return new ComplexNumber(real, imag);
    }
}

