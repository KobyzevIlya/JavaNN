package ru.hse.hw1.complex;

public class ComplexNumber implements Cloneable {
    private double real;
    private double imag;

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public ComplexNumber add(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return new ComplexNumber(this.real + other.real, this.imag + other.imag);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return new ComplexNumber(this.real - other.real, this.imag - other.imag);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNumber(newReal, newImag);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
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

    public void setReal(double real) {
        this.real = real;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public double getImag() {
        return imag;
    }

    @Override
    public ComplexNumber clone() {
        return new ComplexNumber(real, imag);
    }
}

