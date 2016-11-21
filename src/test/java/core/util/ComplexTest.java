package core.util;

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
    private static final BigDecimal EPSILON = new BigDecimal(0.0000000000000001f);
    private static final BigDecimal BIG_DECIMAL_ONE = new BigDecimal(1.0);
    private static final BigDecimal BIG_DECIMAL_SQRT_2 = new BigDecimal(Math.sqrt(2));
    private static final BigDecimal BIG_DECIMAL_ONE_OVER_SQRT_2 =
            BIG_DECIMAL_ONE.divide(BIG_DECIMAL_SQRT_2, Complex.PRECISION);

    /**
     * Tests the creation and methods of a simple complex number
     */
    @Test
    public void simpleComplexNumberBig() {

        BigDecimal realPart = new BigDecimal(1.0);
        BigDecimal imaginaryPart = new BigDecimal(1.0);
        Complex complexNumber = new Complex(realPart, imaginaryPart);

        // Test basic operations
        assertEquals("Real part should equal 1.0", complexNumber.getRealPart(), BIG_DECIMAL_ONE);
        assertEquals("Imaginary part equal 1.0", complexNumber.getImaginaryPart(), BIG_DECIMAL_ONE);
        assertEquals("Magnitude should be sqrt(2)", complexNumber.getMagnitude(), BIG_DECIMAL_SQRT_2);
        assertEquals("Expected string representation", complexNumber.toString(), "1 + i * 1");

        // Test normalization
        Complex normalized = complexNumber.getNormalized();
        assertEquals("Expected real part 1/sqrt(2)", normalized.getRealPart(), BIG_DECIMAL_ONE_OVER_SQRT_2);
        assertEquals("Expected imaginary part 1/sqrt(2)", normalized.getImaginaryPart(), BIG_DECIMAL_ONE_OVER_SQRT_2);
        assertEquals("Expected magnitude of 1", normalized.getMagnitude().floatValue(),
                BIG_DECIMAL_ONE.floatValue(), EPSILON.floatValue());

    }

}
