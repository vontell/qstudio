package core.algorithms;

import core.expection.InvalidParameterException;
import org.apfloat.Apint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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
    @Test(expected = InvalidParameterException.class)
    public void shorsClassicalNisZero() {

        ClassicalImpl.shorsPrimeFactorization(0, false);

    }

    /**
     * Tests the prime factorization of N when N is a prime
     */
    @Test
    public void shorsClassicalNisPrime() {

        for(int i = 0; i < 20; i++) {
            Apint[] factors = ClassicalImpl.shorsPrimeFactorization(7, true);
            assertEquals("Expected one factor", 1, factors.length);
            assertEquals("Expected factor of 7", 7, factors[0].intValue());
        }

    }

    /**
     * Tests the prime factorization of N = p*p, where p is a prime
     */
    @Test
    public void shorsClassicalNisSemiprimeSame() {

        for(int i = 0; i < 20; i++) {
            Apint[] factors = ClassicalImpl.shorsPrimeFactorization(7*7, true);
            assertEquals("Expected two factors", 2, factors.length);
            assertEquals("Expected factor of 7", 7, factors[0].intValue());
            assertEquals("Expected factor of 7", 7, factors[1].intValue());
        }

    }

    /**
     * Tests the prime factorization of N = p*q, where p and q are primes
     */
    @Test
    public void shorsClassicalNisDistinctSemiprime() {

        for(int i = 0; i < 20; i++) {

            Apint[] factors = ClassicalImpl.shorsPrimeFactorization(13*23, true);

            assertEquals("Expected two factors", 2, factors.length);

            // Construct set
            TreeSet<Apint> orderedApints = new TreeSet<Apint>();
            orderedApints.add(factors[0]);
            orderedApints.add(factors[1]);

            assertEquals("Expected two distinct factors", 2, orderedApints.size());
            assertEquals("Expected factor of 13", 13, orderedApints.first().intValue());
            assertEquals("Expected factor of 23", 23, orderedApints.last().intValue());

        }

    }

    /**
     * Tests the prime factorization of N=p*q*r, where p, q, and r are primes
     */
    @Test
    public void shorsClassicalNisCompositeDistinct() {

        for(int i = 0; i < 20; i++) {

            Apint[] factors = ClassicalImpl.shorsPrimeFactorization(13*23*7, true);

            assertEquals("Expected three factors", 3, factors.length);

            // Construct ordered list
            ArrayList<Apint> factorList = new ArrayList<Apint>();
            factorList.add(factors[0]);
            factorList.add(factors[1]);
            factorList.add(factors[2]);
            Collections.sort(factorList);

            assertEquals("Expected factor of 7", 7, factorList.get(0).intValue());
            assertEquals("Expected factor of 13", 13, factorList.get(1).intValue());
            assertEquals("Expected factor of 23", 23, factorList.get(2).intValue());

        }

    }

    /**
     * Tests the prime factorizatino of N=p*p*p, where p is a prime
     */
    @Test
    public void shorsClassicalNisCompositeSame() {

        for(int i = 0; i < 20; i++) {

            Apint[] factors = ClassicalImpl.shorsPrimeFactorization(7*7*7, true);

            assertEquals("Expected three factors", 3, factors.length);

            // Construct ordered list
            ArrayList<Apint> factorList = new ArrayList<Apint>();
            factorList.add(factors[0]);
            factorList.add(factors[1]);
            factorList.add(factors[2]);
            Collections.sort(factorList);

            assertEquals("Expected factor of 7", 7, factorList.get(0).intValue());
            assertEquals("Expected factor of 7", 7, factorList.get(1).intValue());
            assertEquals("Expected factor of 7", 7, factorList.get(2).intValue());

        }

    }

}
