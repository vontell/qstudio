package core.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * An immutable, high precision representation of a complex number (up to 128 bits)
 * @author Aaron Vontell
 * @version 0.1
 */
public class Complex {

    public static final MathContext PRECISION = MathContext.DECIMAL128;

    /**
     * The high-precision value of the real part of this complex number
     */
    private final BigDecimal realPart;

    /**
     * The high-precision value of the imaginary part of this complex number
     */
    private final BigDecimal imaginaryPart;

    /**
     * Creates a complex number from the given real and imaginary parts (BigDecimal)
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(BigDecimal realPart, BigDecimal imaginaryPart) {

        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;

    }

    /**
     * Creates a complex number from the given real and imaginary parts (float)
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(float realPart, float imaginaryPart) {

        this.realPart = new BigDecimal(realPart);
        this.imaginaryPart = new BigDecimal(imaginaryPart);

    }

    /**
     * Returns the real part of this complex number, as a BigDecimal
     * @return the real part of this complex number
     */
    public BigDecimal getRealPart() {
        return realPart;
    }

    /**
     * Returns the imaginary part of this complex number, as a BigDecimal
     * @return the imaginary part of this complex number
     */
    public BigDecimal getImaginaryPart() {
        return imaginaryPart;
    }

    /**
     * Returns the magnitude of this complex number
     * TODO: Make this more precise using BigDecimal all the way through?
     * @return the magnitude of this complex number
     */
    public BigDecimal getMagnitude() {

        BigDecimal realSquared = realPart.pow(2);
        BigDecimal imaginarySquared = imaginaryPart.pow(2);
        BigDecimal addedSquares = realSquared.add(imaginarySquared);
        BigDecimal squareRoot = new BigDecimal(Math.sqrt(addedSquares.doubleValue()));
        return squareRoot;

    }

    public BigDecimal getPhase() {

        throw new RuntimeException("Not yet implemented!");

    }

    /**
     * Returns the normalized version of this complex number
     * @return the normalized complex number
     */
    public Complex getNormalized() {

        BigDecimal realNormalized = realPart.divide(getMagnitude(), PRECISION);
        BigDecimal imaginaryNormalized = imaginaryPart.divide(getMagnitude(), PRECISION);
        return new Complex(realNormalized, imaginaryNormalized);

    }

    /**
     * Returns a string representation of this complex number
     * @return the number as "a + i * b" where a is the real part, and b is
     *         the imaginary part
     */
    public String toString() {

        return String.format("%s + i * %s", realPart.toString(),
                imaginaryPart.toString());

    }


}
