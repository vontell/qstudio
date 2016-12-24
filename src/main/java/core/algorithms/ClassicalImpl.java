package core.algorithms;

import core.expection.InvalidParameterException;
import core.util.Mathematics;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A collection of classical implementations of Quantum Algorithms
 * (For example, Shor's algorithm with a classical period-finding
 * sub-routine)
 * @author Aaron Vontell
 * @version 0.1
 */
public class ClassicalImpl {

    /**
     * Computes the prime factors of N using a classical implementation of
     * Shor's algorithm (i.e. Shor's algorithm using a classical period
     * finding subroutine). Algorithm and notes can be found at
     * https://en.wikipedia.org/wiki/Shor's_algorithm or at
     * https://github.com/vontell/Quantum-Computing-Collection
     * @param N The integer N = pq to find the prime factors of
     * @param verbose Prints out steps and calculations if set to True
     * @return A length 2 array which is [p, q], high precision
     */
    public static List<Integer> shorsPrimeFactorization(int N, boolean verbose) {

        // Initial check for bad or obvious N
        if (N == 0) {
            throw new InvalidParameterException("N = 0 has no prime factorization");
        } else if (N == 1 || N == 2 || N == 3) {
            List<Integer> factors = new ArrayList<Integer>();
            factors.add(N);
            return factors;
        }

        // A loop which goes through a queue of numbers until all are primes
        List<Integer> factors = new ArrayList<Integer>();
        factors.add(N);
        int index = 0;

        // Within this loop, N is the currently selected value in the factor list
        while (true) {

            // Done if at end of list
            if(index == factors.size()) {

                if (verbose) {
                    System.out.println("Went through all factors, exiting");
                }

                break;
            }

            // If prime, move to the next factor in the list
            // TODO: Use an efficient primality test
            if (Mathematics.isPrime(factors.get(index))) {

                if (verbose) {
                    System.out.println("" + factors.get(index) + " is a prime, move to next value");
                }

                index++;
                continue;
            }

            N = factors.remove(index);

            // Pick a random number X < N
            int X = new Random().nextInt(N - 2) + 1;

            if(verbose) {
                System.out.println("Found an X: " + X);
            }

            // Compute the GCD of X and N
            int gcd = Mathematics.greatestCommonDenominator(X, N);

            if(gcd != 1) {

                factors.add(gcd);
                factors.add(N / gcd);

                if (verbose) {
                    System.out.println("Good guess on GCD: " + gcd);
                }

                // Good guess on X! Lucky you!
                continue;

            }

            // Otherwise, need to continue, and use high precision
            // Find the period of f(x) = X^r mod N
            Apint bigX = new Apint(X);
            Apint bigN = new Apint(N);
            Apint r = Mathematics.findPeriodClassically(bigX, bigN);

            if(verbose) {
                System.out.println("Found period r: " + r);
            }

            // Compute the prime factors p and q!
            Apint a = ApintMath.pow(bigX, r.intValue()/2).add(Apint.ONE);
            Apint b = a.subtract(Apint.ONE.add(Apint.ONE));

            if(verbose) {
                System.out.println("Found (x^{r/2} + 1): " + a);
                System.out.println("Found (x^{r/2} - 1): " + b);
            }

            Apint p = Mathematics.greatestCommonDenominator(a, bigN);
            //Apint q = Mathematics.greatestCommonDenominator(b, bigN);

            factors.add(p.intValue());
            factors.add(bigN.divide(p).intValue());

            if(verbose) {
                System.out.println("Found new factors, new collection: " + factors);
            }

        }

        // Remove all `1` factors from the results, since they are trivial
        while(factors.contains(new Integer(1))) {
            factors.remove(new Integer(1));
        }

        if (verbose) {
            System.out.println("Found final factors: " + factors);
        }

        return factors;

    }

}
