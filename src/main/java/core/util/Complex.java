package core.util;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.math.BigDecimal;

/**
 * An immutable, high precision representation of a complex number
 * This is a portal into the Apcomplex class
 * @author Aaron Vontell
 * @version 0.2
 */
public class Complex {

    /**
     * The high-precision complex value
     */
    private final Apcomplex complexValue;

    /**
     * The max precision to use when there is an infinite expansion
     */
    public static final int MAX_PRECISION = 100;

    /**
     * Creates a complex number from the given real and imaginary parts (BigDecimal)
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(BigDecimal realPart, BigDecimal imaginaryPart) {

        Apfloat realFloat = new Apfloat(realPart, MAX_PRECISION);
        Apfloat imagFloat = new Apfloat(imaginaryPart, MAX_PRECISION);
        this.complexValue = new Apcomplex(realFloat, imagFloat);

    }

    /**
     * Creates a complex number from the given real and imaginary parts (float)
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(float realPart, float imaginaryPart) {

        Apfloat realFloat = new Apfloat(realPart, MAX_PRECISION);
        Apfloat imagFloat = new Apfloat(imaginaryPart, MAX_PRECISION);
        this.complexValue = new Apcomplex(realFloat, imagFloat);

    }

    /**
     * Creates a complex number from the given real and imaginary parts (Apfloat)
     * @param realPart The real part of the complex number
     * @param imaginaryPart The imaginary part of this complex number
     */
    public Complex(Apfloat realPart, Apfloat imaginaryPart) {

        realPart.precision(MAX_PRECISION);
        realPart.precision(MAX_PRECISION);
        this.complexValue = new Apcomplex(realPart, imaginaryPart);

    }

    /**
     * Returns the real part of this complex number, as a high precision float
     * @return the real part of this complex number
     */
    public Apfloat getRealPart() {
        return this.complexValue.real();
    }

    /**
     * Returns the imaginary part of this complex number, as a high precision float
     * @return the imaginary part of this complex number
     */
    public Apfloat getImaginaryPart() {
        return this.complexValue.imag();
    }

    /**
     * Returns the magnitude of this complex number
     * @return the magnitude of this complex number
     */
    public Apfloat getMagnitude() {

        return ApfloatMath.sqrt(ApfloatMath.pow(getRealPart(), 2)
                .add(ApfloatMath.pow(getImaginaryPart(), 2))) ;

    }

    /**
     * Returns the phase of this complex number, or the
     * angle of the vector in the complex plane
     * @return the phase of this complex number
     */
    public Apfloat getPhase() {

        return ApcomplexMath.arg(this.complexValue);

    }

    /**
     * Returns the normalized version of this complex number
     * @return the normalized complex number
     */
    public Complex getNormalized() {

        Apcomplex normalized = complexValue.divide(getMagnitude());
        return new Complex(normalized.real(), normalized.imag());

    }

    /**
     * Returns a string representation of this complex number
     * @return the number as "a + i * b" where a is the real part, and b is
     *         the imaginary part
     */
    public String toString() {

        return complexValue.toString();

    }


}
