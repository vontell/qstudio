package core.algorithms;

import core.util.Mathematics;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

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
     * NOTE: This function is currently limited to very small N (< 100)
     * @param N The integer N = pq to find the prime factors of
     * @param verbose Prints out steps and calculations if set to True
     * @return A length 2 array which is [p, q], high precision
     */
    public static Apint[] shorsPrimeFactorization(int N, boolean verbose) {

        // Pick a random number X < N
        int X = new Random().nextInt(N - 2) + 1;

        if(verbose) {
            System.out.println("Found an X: " + X);
        }

        // Compute the GCD of X and N
        int gcd = Mathematics.greatestCommonDenominator(X, N);

        if(gcd != 1) {

            if(verbose) {
                System.out.println("Good guess on X. Factors are " + gcd + " and " + (N / gcd));
            }

            // Found a nontrivial factor! Lucky you!
            return new Apint[] {new Apint(gcd), new Apint(N / gcd)};

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
        Apint b = ApintMath.pow(bigX, r.intValue()/2).subtract(Apint.ONE);

        if(verbose) {
            System.out.println("Found (x^{r/2} + 1): " + a);
            System.out.println("Found (x^{r/2} - 1): " + b);
        }

        Apint p = Mathematics.greatestCommonDenominator(a, bigN);
        Apint q = Mathematics.greatestCommonDenominator(b, bigN);

        while (p.multiply(q).intValue() != N || a.intValue() == N || b.intValue() == N) {

            if(verbose) {
                System.out.println("Bad result, trying again");
            }

            Apint[] newResults = ClassicalImpl.shorsPrimeFactorization(N, verbose);
            p = newResults[0];
            q = newResults[1];
        }

        if(verbose) {
            System.out.println("Found factors p and q: [" + p + ", " + q + "]");
        }

        return new Apint[] {p,q};

    }

}
