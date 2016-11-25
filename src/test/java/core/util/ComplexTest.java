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

        // Test normalization
        Complex normalized = complexNumber.getNormalized();
        assertEquals("Expected real part 1/sqrt(2)", PRECISE_ONE_OVER_SQRT_2,  normalized.getRealPart());
        assertEquals("Expected imaginary part 1/sqrt(2)", PRECISE_ONE_OVER_SQRT_2, normalized.getImaginaryPart());
        assertEquals("Expected magnitude of 1", PRECISE_ONE.floatValue(), normalized.getMagnitude().floatValue(), EPSILON.floatValue());

    }

}
