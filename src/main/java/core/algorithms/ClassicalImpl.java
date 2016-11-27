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
     * @param N The integer N = pq to find the prime factors of
     * @param verbose Prints out steps and calculations if set to True
     * @return A length 2 array which is [p, q]
     */
    public static int[] shorsPrimeFactorization(int N, boolean verbose) {

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
            return new int[] {gcd, N / gcd};

        }

        // Otherwise, need to continue
        // Find the period of f(x) = X^r mod N
        int r = Mathematics.findPeriodClassically(X, N);

        if(verbose) {
            System.out.println("Found period r: " + r);
        }

        // Compute the prime factors p and q!
        Apint a = ApintMath.pow(new Apint(X), r/2).add(Apint.ONE);
        Apint b = ApintMath.pow(new Apint(X), r/2).subtract(Apint.ONE);

        if(verbose) {
            System.out.println("Found (x^{r/2} + 1): " + a.intValue());
            System.out.println("Found (x^{r/2} - 1): " + b.intValue());
        }

        int p = Mathematics.greatestCommonDenominator(a.intValue(), N);
        int q = Mathematics.greatestCommonDenominator(b.intValue(), N);

        if(verbose) {
            System.out.println("Found factors p and q: [" + p + ", " + q + "]");
        }

        return new int[] {p,q};

    }

}
