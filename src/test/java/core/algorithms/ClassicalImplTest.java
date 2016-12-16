package core.algorithms;

import core.expection.InvalidParameterException;
import org.apfloat.Apint;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Test suite for the classical algorithms introduced in the
 * ClassicalImpl class
 * @author Aaron Vontell
 * @version 0.1
 */
public class ClassicalImplTest {

    // ------------------------------------------------------------------------
    // Tests for Shor's Algorithm with a classical period finding sub-routine
    // Since this algorithm is slightly randomized (in picking X), multiple
    // iterations of each test are run.
    // ------------------------------------------------------------------------

    /**
     * Tests the prime factorization of 0, which should throw an exception
     */
    @Ignore("Method not yet implemented")
    @Test(expected = InvalidParameterException.class)
    public void shorsClassicalNisZero() {

        ClassicalImpl.shorsPrimeFactorization(0, false);

    }

    /**
     * Tests the prime factorization of N when N is a prime
     */
    @Ignore("Method not yet implemented")
    @Test
    public void shorsClassicalNisPrime() {

        for(int i = 0; i < 20; i++) {
            List<Integer> factors = ClassicalImpl.shorsPrimeFactorization(7, true);
            assertEquals("Expected one factor", 1, factors.size());
            assertEquals("Expected factor of 7", 7, factors.get(0).intValue());
        }

    }

    /**
     * Tests the prime factorization of N = p*p, where p is a prime
     */
    @Ignore("Method not yet implemented")
    @Test
    public void shorsClassicalNisSemiprimeSame() {

        for(int i = 0; i < 20; i++) {
            List<Integer> factors = ClassicalImpl.shorsPrimeFactorization(7*7, true);
            assertEquals("Expected two factors", 2, factors.size());
            assertEquals("Expected factor of 7", 7, factors.get(0).intValue());
            assertEquals("Expected factor of 7", 7, factors.get(1).intValue());
        }

    }

    /**
     * Tests the prime factorization of N = p*q, where p and q are primes
     */
    @Ignore("Method not yet implemented")
    @Test
    public void shorsClassicalNisDistinctSemiprime() {

        for(int i = 0; i < 20; i++) {

            List<Integer> factors = ClassicalImpl.shorsPrimeFactorization(13*23, true);

            assertEquals("Expected two factors", 2, factors.size());

            // Construct set
            TreeSet<Integer> orderedApints = new TreeSet<Integer>();
            orderedApints.add(factors.get(0));
            orderedApints.add(factors.get(1));

            assertEquals("Expected two distinct factors", 2, orderedApints.size());
            assertEquals("Expected factor of 13", 13, orderedApints.first().intValue());
            assertEquals("Expected factor of 23", 23, orderedApints.last().intValue());

        }

    }

    /**
     * Tests the prime factorization of N=p*q*r, where p, q, and r are primes
     */
    @Ignore("Method not yet implemented")
    @Test
    public void shorsClassicalNisCompositeDistinct() {

        for(int i = 0; i < 20; i++) {

            List<Integer> factors = ClassicalImpl.shorsPrimeFactorization(13*23*7, true);

            assertEquals("Expected three factors", 3, factors.size());

            // Construct ordered list
            Collections.sort(factors);

            assertEquals("Expected factor of 7", 7, factors.get(0).intValue());
            assertEquals("Expected factor of 13", 13, factors.get(1).intValue());
            assertEquals("Expected factor of 23", 23, factors.get(2).intValue());

        }

    }

    /**
     * Tests the prime factorizatino of N=p*p*p, where p is a prime
     */
    @Ignore("Method not yet implemented")
    @Test
    public void shorsClassicalNisCompositeSame() {

        for(int i = 0; i < 20; i++) {

            List<Integer> factors = ClassicalImpl.shorsPrimeFactorization(7*7*7, true);

            assertEquals("Expected three factors", 3, factors.size());

            // Construct ordered list
            Collections.sort(factors);

            assertEquals("Expected factor of 7", 7, factors.get(0).intValue());
            assertEquals("Expected factor of 7", 7, factors.get(1).intValue());
            assertEquals("Expected factor of 7", 7, factors.get(2).intValue());

        }

    }

}
