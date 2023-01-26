package ru.hse.hw1.complex;

public class ComplexNumber implements Cloneable {
    private double real;
    private double imag;

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
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

    public ComplexNumber pow(double exponent) {
        if (exponent == 1) {
            return this.clone();
        }

        if (exponent == 2) {
            return multiply(this);
        }

        double mod = Math.pow(getModulus(), exponent);
        double arg = getArgument() * exponent;
        return new ComplexNumber(mod * Math.cos(arg), mod * Math.sin(arg));
    }

    public ComplexNumber[] root(int n) {
        ComplexNumber[] roots = new ComplexNumber[n];

        if (n == 1) {
            roots[0] = clone();
            return roots;
        }

        double magnitude = Math.pow(getModulus(), 1.0/n);
        double angle = getArgument() / n;
        for (int k = 0; k < n; k++) {
            double newReal = magnitude * Math.cos((angle + 2 * (double) k * Math.PI) / (double) n);
            double newImag = magnitude * Math.sin((angle + 2 * (double) k * Math.PI) / (double) n);
            roots[k] = new ComplexNumber(newReal, newImag);
        }
        return roots;
    }

    @Override
    public ComplexNumber clone() {
        return new ComplexNumber(real, imag);
    }
}

