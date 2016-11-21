package util.math;

import java.math.BigDecimal;

/**
 * An immutable, high precision representation of a complex number
 * @author Aaron Vontell
 * @version 0.1
 */
public class Complex {

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
     * Creates a complex number from the given real and imaginary parts (float
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(float realPart, float imaginaryPart) {

        this.realPart = new BigDecimal(realPart);
        this.imaginaryPart = new BigDecimal(imaginaryPart);

    }

    /**
     * Returns the magnitude of this complex number
     * @return the magnitude of this complex number
     */
    public BigDecimal magnitude() {

        BigDecimal realSquared = realPart.pow(2);
        BigDecimal imaginarySquared = imaginaryPart.pow(2);
        BigDecimal addedSquares = realSquared.add(imaginarySquared);
        BigDecimal squareRoot = addedSquares.

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
