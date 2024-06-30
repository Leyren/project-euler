package project_euler;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler014/problem
 */
public class Problem14 {

    private static final int[] cache = new int[5000001];
    private static final int[] longest = new int[5000001];

    /*
     * Idea: Memoization
     * Considering starting values up to 5 million, and the fact that we are generating number sequences (hence might
     * quickly fall into a sequence that was already seen) caching is very powerful here.
     *
     * cache: store the computed collatz sequence value for this index
     * longest: store the number which, used as input, would produce the longest collatz sequence
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int lastFilledIndex = 0;

        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            for (int i = lastFilledIndex + 1; i <= n; i++) {
                int value = collatz(i);
                int lastValue = lastFilledIndex == 0 ? 0 : collatz(longest[lastFilledIndex]);
                // if the newly computed chain length is longer than the previously longest one, set the new one
                // otherwise keep the previous one
                longest[i] = value >= lastValue ? i : longest[lastFilledIndex];
                lastFilledIndex = i;
            }
            System.out.println(longest[n]);
        }
    }

    // Since during the sequence generation the value can surpass the size limit of the cache, don't use
    // memoization past that threshold (could use different techniques but then no simple array would suffice)
    public static int collatz(long n) {
        return n < cache.length ? cachedCollatz((int) n) : uncachedCollatz(n);
    }

    public static int uncachedCollatz(long n) {
        if (n == 1) return 0;
        if (n % 2 == 0) return collatz(n / 2) + 1;
        return collatz(3 * n + 1) + 1;
    }

    public static int cachedCollatz(int n) {
        if (cache[n] != 0) return cache[n];
        if (n == 1) cache[n] = 0;
        else if (n % 2 == 0) cache[n] = collatz(n / 2) + 1;
        else cache[n] = collatz(3 * n + 1) + 1;
        return cache[n];
    }
}
