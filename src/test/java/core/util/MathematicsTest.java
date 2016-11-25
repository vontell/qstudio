package core.util;

import org.apfloat.Apfloat;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit tests for static mathematical methods
 * @author Aaron Vontell
 * @version 0.1
 */
public class MathematicsTest {


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
