package core.util;

import core.algorithms.ClassicalImpl;
import core.expection.InvalidParameterException;
import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit tests for static mathematical methods
 * @author Aaron Vontell
 * @version 0.2
 */
public class MathematicsTest {

    /**
     * Simple test for coverage on the class name
     */
    @Test
    public void constructTest() {
        new Mathematics();
    }

    /**
     * Tests the continued fraction expansion of an integer
     */
    @Test
    public void testContinuedFractionInteger() {

        float integerFloat = 3.0f;
        int[] desired = {3};

        int[] floatResult = Mathematics.continuedFraction(integerFloat, 100);

        assertTrue("Expected single number array from float", intArraysAreEqual(floatResult, desired));

    }

    /**
     * Tests the continued fraction expansion of a BigDecimal
     */
    @Test
    public void testContinuedFractionBigDecimal() {

        float integerFloat = 3.0f;
        BigDecimal bigDecimal = new BigDecimal(integerFloat);
        int[] desired = {3};

        int[] floatResult = Mathematics.continuedFraction(bigDecimal, 100);

        assertTrue("Expected single number array from float", intArraysAreEqual(floatResult, desired));

    }

    /**
     * Tests the continued fraction expansion of PI (as defined by Math.PI)
     */
    @Test
    public void testContinuedFractionIrrational() {

        Apfloat PI = Mathematics.PI;
        int[] desired = {3, 7, 15, 1, 292, 1, 1, 1, 2, 1};

        int[] floatResult = Mathematics.continuedFraction(PI, 10);

        assertEquals("Expected correct array sizes", desired.length, floatResult.length);
        assertTrue("Expected correct representation", intArraysAreEqual(floatResult, desired));

    }

    /**
     * Tests the continued fraction of a rational fraction, 768/1024
     */
    @Test
    public void testContinuedFractionFraction() {

        Apfloat fraction = new Apfloat(768, 100).divide(new Apfloat(1024, 100));
        int[] desired = {0, 1, 3};

        int[] result = Mathematics.continuedFraction(fraction, 10);

        assertEquals("Expected correct array sizes", desired.length, result.length);
        assertTrue("Expected correct representation", intArraysAreEqual(result, desired));

    }

    /**
     * Computes the GCD when b is already zero
     */
    @Test
    public void testGCDBisZero() {

        int result = Mathematics.greatestCommonDenominator(312, 0);

        assertEquals("Expected GCD of a", 312, result);

    }

    /**
     * Computes the GCD when b is greater than a
     */
    @Test
    public void testGCDAisGreater() {

        int result = Mathematics.greatestCommonDenominator(32, 456);

        assertEquals("Expected correct GCD", 8, result);

    }

    /**
     * Computes the GCD when a and b are equal
     */
    @Test
    public void testGCDAisB() {

        int result = Mathematics.greatestCommonDenominator(32, 32);

        assertEquals("Expected GCD of a or b", 32, result);

    }

    /**
     * Computers the GCD when the GCD of a and b is 1
     * i.e. a and b are prime numbers
     */
    @Test
    public void testGCDisOne() {

        int result = Mathematics.greatestCommonDenominator(13, 27);

        assertEquals("Expected GCD of a", 1, result);

    }

    /**
     * Computes the GCD when b is already zero (arbitrary precision)
     */
    @Test(expected = InvalidParameterException.class)
    public void testGCDBisZeroAP() {

        int result = Mathematics.greatestCommonDenominator(new Apint(312), new Apint(0)).intValue();

    }

    /**
     * Computes the GCD when b is greater than a (arbitrary precision)
     */
    @Test
    public void testGCDAisGreaterAP() {

        int result = Mathematics.greatestCommonDenominator(new Apint(32), new Apint(456)).intValue();

        assertEquals("Expected correct GCD", 8, result);

    }

    /**
     * Computes the GCD when a and b are equal (arbitrary precision)
     */
    @Test
    public void testGCDAisBAP() {

        int result = Mathematics.greatestCommonDenominator(new Apint(32), new Apint(32)).intValue();

        assertEquals("Expected GCD of a or b", 32, result);

    }

    /**
     * Computers the GCD when the GCD of a and b is 1 (arbitrary precision)
     * i.e. a and b are prime numbers
     */
    @Test
    public void testGCDisOneAP() {

        int result = Mathematics.greatestCommonDenominator(new Apint(13), new Apint(27)).intValue();

        assertEquals("Expected GCD of a", 1, result);

    }

    /**
     * Finds the period r of x^r mod N for an integer x and N
     */
    @Test
    public void testFindPeriodClassicallyInt() {

        int period = Mathematics.findPeriodClassically(3, 5);

        assertEquals("Expected period of 4", 4, period);

    }

    /**
     * Finds the period r of x^r mod N for an arbitrary precision integer x and N
     */
    @Test
    public void testFindPeriodClassicallyApint() {

        Apint period = Mathematics.findPeriodClassically(new Apint(3), new Apint(5));

        assertEquals("Expected period of 4", new Apint(4), period);

    }

    /**
     * Finds the period r of x^r mod N for when N is zero (should throw an exception)
     */
    @Test(expected = InvalidParameterException.class)
    public void testFindPeriodClassicallyInvalid() {

        Mathematics.findPeriodClassically(3, 0);

    }

    /**
     * Finds the period r of x^r mod N for when X is zero (should throw an exception)
     */
    @Test(expected = InvalidParameterException.class)
    public void testFindPeriodClassicallyZeroX() {

        Mathematics.findPeriodClassically(0, 3);

    }

    /**
     * Finds the period r of x^r mod N when x and N are (not coprime?)
     */
    @Test(expected = InvalidParameterException.class)
    public void testFindPeriodClassicallyNoPeriod() {

        Mathematics.findPeriodClassically(3, 9);

    }

    /**
     * Determines if two integer arrays are equal
     * @param arr1 The first array
     * @param arr2 The second array
     * @return true if the two arrays are exactly equal
     */
    private boolean intArraysAreEqual(int[] arr1, int[] arr2) {

        if(arr1.length != arr2.length) {
            return false;
        }

        for(int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;

    }

    /**
     * Prints the given integer array to the console
     * @param array The array to pretty print
     */
    private void printArray(int[] array) {

        String result = "[";
        for(int i = 0; i < array.length; i++) {
            result += array[i];
            if(i < array.length - 1) {
                result += ", ";
            }
        }
        result += "]";

        System.out.println(result);

    }

}
