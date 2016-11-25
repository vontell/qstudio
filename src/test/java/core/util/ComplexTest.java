package core.util;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Unit tests for the representation of high precision complex numbers
 * @author Aaron Vontell
 * @version 0.1
 */
public class ComplexTest {

    // Constants to make references to
    private static final Apfloat EPSILON = new Apfloat(0.0000000000000001f);
    private static final Apfloat PRECISE_ONE = Apfloat.ONE;
    private static final Apfloat PRECISE_ZERO = Apcomplex.ZERO;
    private static final Apfloat PRECISE_PI_OVER_FOUR = Mathematics.PI.divide(new Apfloat(4));
    private static final Apfloat PRECISE_PI_OVER_TWO = Mathematics.PI.divide(new Apfloat(2));
    private static final Apfloat PRECISE_SQRT_2 = ApfloatMath.sqrt(new Apfloat(2, Complex.MAX_PRECISION));
    private static final Apfloat PRECISE_ONE_OVER_SQRT_2 = PRECISE_ONE.divide(PRECISE_SQRT_2);

    /**
     * Tests the creation and methods of a simple complex number
     */
    @Test
    public void testSimpleComplexNumberBig() {

        BigDecimal realPart = new BigDecimal(1.0);
        BigDecimal imaginaryPart = new BigDecimal(1.0);
        Complex complexNumber = new Complex(realPart, imaginaryPart);

        // Test basic operations
        assertEquals("Real part should equal 1.0", PRECISE_ONE, complexNumber.getRealPart());
        assertEquals("Imaginary part equal 1.0", PRECISE_ONE, complexNumber.getImaginaryPart());
        assertEquals("Expected string representation", "(1, 1)", complexNumber.toString());
        assertEquals("Magnitude should be sqrt(2)", PRECISE_SQRT_2, complexNumber.getMagnitude());
        assertEquals("Phase should be pi / 4", PRECISE_PI_OVER_FOUR.floatValue(), complexNumber.getPhase().floatValue(), EPSILON.floatValue());
        assertEquals("Expected complex conjugate", new Apfloat(-1), complexNumber.getConjugate().getImaginaryPart());
        assertEquals("Expected complex conjugate", new Apfloat(1), complexNumber.getConjugate().getRealPart());

        // Test normalization
        Complex normalized = complexNumber.getNormalized();
        assertEquals("Expected real part 1/sqrt(2)", PRECISE_ONE_OVER_SQRT_2,  normalized.getRealPart());
        assertEquals("Expected imaginary part 1/sqrt(2)", PRECISE_ONE_OVER_SQRT_2, normalized.getImaginaryPart());
        assertEquals("Expected magnitude of 1", PRECISE_ONE.floatValue(), normalized.getMagnitude().floatValue(), EPSILON.floatValue());
        assertTrue("Expected normalized", normalized.isNormalized(Complex.EPSILON));

    }

    /**
     * Tests the behavior of a zero length vector (for valid operations)
     */
    @Test
    public void testZeroMagnitudeNumberValidOperations() {

        Complex complexNumber = new Complex(0, 0);

        // Test basic operations
        assertEquals("Real part should equal 0.0", PRECISE_ZERO, complexNumber.getRealPart());
        assertEquals("Imaginary part equal 0.0", PRECISE_ZERO, complexNumber.getImaginaryPart());
        assertEquals("Expected string representation", "0", complexNumber.toString());
        assertEquals("Magnitude should be 0", PRECISE_ZERO, complexNumber.getMagnitude());
        assertEquals("Phase should be null", null, complexNumber.getPhase());
        assertEquals("Expected complex conjugate", PRECISE_ZERO, complexNumber.getConjugate().getImaginaryPart());
        assertEquals("Expected complex conjugate", PRECISE_ZERO, complexNumber.getConjugate().getRealPart());
        assertFalse("Should not be normalized", complexNumber.isNormalized(Complex.EPSILON));

    }


    /**
     * Tests the behavior of normalizing a point with no magnitude
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testZeroMagnitudeNumberGetNormalized() {

        Complex complexNumber = new Complex(0, 0);

        // Try normalizing a point
        complexNumber.getNormalized();

    }


    /**
     * Tests the behavior of phase calculation at
     * theta = 0, pi/2, pi, and 2pi/3
     */
    @Test
    public void testDifferentAngles() {

        Complex posOne = new Complex(1, 0);
        Complex posI = new Complex(0, 1);
        Complex negOne = new Complex(-1, 0);
        Complex negI = new Complex(0, -1);

        Apfloat radians = PRECISE_PI_OVER_TWO;

        assertTrue("Expected rotation by pi/2 to i", posI.valueEquals(posOne.rotate(radians), Complex.EPSILON));
        assertTrue("Expected rotation by pi/2 to -1", negOne.valueEquals(posI.rotate(radians), Complex.EPSILON));
        assertTrue("Expected rotation by pi/2 to -i", negI.valueEquals(negOne.rotate(radians), Complex.EPSILON));
        assertTrue("Expected rotation by pi/2 to 1", posOne.valueEquals(negI.rotate(radians), Complex.EPSILON));

    }

    /**
     * Tests the act of rotating a complex number
     * many times
     */
    @Test
    public void testRotationAccuracy() {

        Complex vector = new Complex(1, 0);
        Complex desired = new Complex(1, 0);
        Apfloat radians = PRECISE_PI_OVER_TWO;

        for(int i = 0; i < 16; i++) {
            vector = vector.rotate(radians);
        }

        assertTrue("Expected approximately equal", vector.valueEquals(desired, Complex.EPSILON));

    }

    /**
     * Tests the equality of two complex numbers
     */
    @Test
    public void testValueEquals() {

        Complex posOne = new Complex(1, 0);
        Complex posOneCopy = new Complex(1, 0);

        assertTrue("Expected same values", posOne.valueEquals(posOneCopy));

    }

    @Test
    public void testApproxValueEquals() {

        Complex one = new Complex(1, 0);
        Complex notOne = new Complex(1.1f, 0f);

        assertFalse("Should not be equal", one.valueEquals(notOne, Complex.EPSILON));

    }

}
