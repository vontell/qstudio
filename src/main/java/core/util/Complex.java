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
    public static final int MAX_PRECISION = 20;

    /**
     * A default value of epsilon for error tolerance
     */
    public static final Apfloat EPSILON = new Apfloat(0.0000001);

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
     * angle of the vector in the complex plane. Returns
     * null if phase is undefined (for example, the point)
     * @return the phase of this complex number
     */
    public Apfloat getPhase() {

        if (getMagnitude().equals(Apcomplex.ZERO)) {
            return null;
        }

        return ApcomplexMath.arg(this.complexValue);

    }

    /**
     * Returns the normalized version of this complex number.
     * Throws UnsupportedOperationException if this vector is
     * simply a point
     * @return the normalized complex number
     */
    public Complex getNormalized() throws UnsupportedOperationException {

        if (getMagnitude().equals(Apfloat.ZERO)) {
            throw new UnsupportedOperationException("Cannot normalize a point");
        }

        Apcomplex normalized = complexValue.divide(getMagnitude());
        return new Complex(normalized.real(), normalized.imag());

    }

    /**
     * Returns the complex conjugate of this complex number
     * @return the complex conjugate
     */
    public Complex getConjugate() {
        return new Complex(complexValue.real(), complexValue.conj().imag());
    }

    /**
     * Rotate this complex number in the complex plane by the given
     * amount in radians
     * @param radians The angle to rotate this vector by counterclockwise
     * @return The new complex number, rotated by radians
     */
    public Complex rotate(Apfloat radians) {

        Apcomplex rotation = new Apcomplex(ApfloatMath.cos(radians),
                ApfloatMath.sin(radians));
        Apcomplex rotated = complexValue.multiply(rotation);
        return new Complex(rotated.real(), rotated.imag());

    }

    /**
     * Returns true if this complex number is normalized, or has
     * a magnitude of 1, up to a tolerance of epsilon
     * @param epsilon The amount of error allowable
     * @return true if this complex number has magnitude 1
     */
    public boolean isNormalized(Apfloat epsilon) {
        return ApfloatMath.abs(getMagnitude().subtract(Apfloat.ONE)).compareTo(epsilon) == -1;
    }

    /**
     * Returns a string representation of this complex number
     * @return the number as "a + i * b" where a is the real part, and b is
     *         the imaginary part
     */
    public String toString() {

        return complexValue.toString();

    }

    /**
     * A custom equals function that returns equal if
     * the given complex number has the same real and
     * imaginary components as this complex number
     * @return if these two complex numbers are equivalent
     */
    public boolean valueEquals(Complex other) {
        return getRealPart().equals(other.getRealPart()) &&
                getImaginaryPart().equals(other.getImaginaryPart());
    }

    /**
     * A custom equals function that returns equal if
     * the given complex number has the same real and
     * imaginary components as this complex number, up to
     * an error tolerance epsilon
     * @return if these two complex numbers are equivalent, up to
     *         the given epsilon
     */
    public boolean valueEquals(Complex other, Apfloat epsilon) {

        Apfloat realDiff = ApfloatMath.abs(getRealPart().subtract(other.getRealPart()));
        Apfloat imagDiff = ApfloatMath.abs(getImaginaryPart().subtract(other.getImaginaryPart()));

        return realDiff.compareTo(epsilon) == -1 &&
                imagDiff.compareTo(epsilon) == -1;
    }


}
