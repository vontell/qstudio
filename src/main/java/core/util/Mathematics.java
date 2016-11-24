package core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class of static methods for completing certain mathematical computations
 * @author Aaron Vontell
 * @version 0.1
 */
public class Mathematics {

    /**
     * Computes the continued fraction for the given number, up to a given number
     * of iterations (simple continued fraction).
     * More information: http://mathworld.wolfram.com/SimpleContinuedFraction.html
     * @param number The number to compute the continued fraction for, as a float
     * @param iterations The maximum number of iterations to compute this continued
     *                   fraction for
     * @return the simple continued fraction in abbreviated notation
     */
    public static int[] continuedFraction(float number, int iterations) {

        List<Integer> fraction = new ArrayList<Integer>();

        int iter_count = 0;
        while (iter_count < iterations && number < Float.POSITIVE_INFINITY) {

            // Get the remainder and integer parts
            int integer = (int) number;
            float remainder = number % 1;

            fraction.add(integer);
            number = 1.0f / remainder;
            iter_count++;

        }

        // TODO: Gotta be another way... is this too slow?
        int[] result = new int[fraction.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = fraction.get(i);
        }

        return result;

    }

    /**
     * Computes the continued fraction for the given number, up to a given number
     * of iterations (simple continued fraction).
     * More information: http://mathworld.wolfram.com/SimpleContinuedFraction.html
     * @param number The number to compute the continued fraction for, as a BigDecimal
     * @param iterations The maximum number of iterations to compute this continued
     *                   fraction for
     * @return the simple continued fraction in abbreviated notation
     */
    public static int[] continuedFraction(BigDecimal number, int iterations) {
        throw new RuntimeException("Not yet implemented error");
    }

}
