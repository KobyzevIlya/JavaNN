package hw1.complex;

/**
 * A class of complex numbers that uses <code>double</code> for the real and image parts.
 * Supports addition, subtraction, multiplication, and division. Can also return modulus and argument
 */
public class ComplexNumber implements Cloneable {
    private double real;
    private double imag;

    /**
     * Constructs a new complex number with the given real and imaginary parts.
     *
     * @param real the real part of the complex number
     * @param imag the imaginary part of the complex number
     */
    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * Adds the given complex number to the current complex number and returns a new complex number that is the sum of the two.
     *
     * @param other the complex number to add to the current complex number
     * @return a new complex number that is the sum of the two complex numbers
     * @throws IllegalArgumentException if the other complex number is null
     */
    public ComplexNumber add(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return new ComplexNumber(this.real + other.real, this.imag + other.imag);
    }

    /**
     * Returns a new complex number that is the result of subtracting the given complex number from the current complex number
     *
     * @param other the complex number to subtract from the current number
     * @return a new complex number that is the result of the subtraction
     * @throws IllegalArgumentException if the other argument is null
     */
    public ComplexNumber subtract(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return new ComplexNumber(this.real - other.real, this.imag - other.imag);
    }

    /**
     * Returns a new complex number that is the result of multiplying the current complex number by the given complex number.
     *
     * @param other the complex number to multiply by the current number
     * @return a new complex number that is the result of the multiplication
     * @throws IllegalArgumentException if the other argument is null
     */
    public ComplexNumber multiply(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNumber(newReal, newImag);
    }

    /**
     * Returns a new complex number that is the result of dividing the current complex number by the given complex number.
     *
     * @param other the complex number to divide by the current number
     * @return a new complex number that is the result of the division
     * @throws IllegalArgumentException if the other argument is null
     */
    public ComplexNumber divide(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        double denominator = other.real * other.real + other.imag * other.imag;
        double newReal = (this.real * other.real + this.imag * other.imag) / denominator;
        double newImag = (this.imag * other.real - this.real * other.imag) / denominator;
        return new ComplexNumber(newReal, newImag);
    }

    /**
     * Returns the modulus (magnitude) of a complex number
     *
     * @return the modulus of the complex number
     */
    public double getModulus() {
        return Math.sqrt(real * real + imag * imag);
    }

    /**
     * Returns the argument (phase) of a complex number
     *
     * @return the argument of the complex number
     */
    public double getArgument() {
        return Math.atan2(imag, real);
    }

    /**
     * Returns the real part of a complex number
     *
     * @return the real part of the complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Sets the real part of a complex number
     *
     * @param real the new value for the real part of the complex number
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     * Sets the imaginary part of a complex number
     *
     * @param imag the new value for the imaginary part of the complex number
     */
    public void setImag(double imag) {
        this.imag = imag;
    }

    /**
     * Returns the imaginary part of a complex number in Cartesian form.
     *
     * @return the imaginary part of the complex number
     */
    public double getImag() {
        return imag;
    }

    /**
     * clones a complex number
     *
     * @return copy of complex number
     */
    @Override
    public ComplexNumber clone() {
        return new ComplexNumber(real, imag);
    }
}

