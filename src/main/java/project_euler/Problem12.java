package project_euler;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler012/problem
 */
public class Problem12 {

    /*
     * Idea: Memoization
     * Not computing it beforehand (only 10 queries overall), but caching results as far as computation went previously
     * E.g. if a triangle number with over 100 divisors is needed, and previously 95 divisors was queried, we can
     * continue from there.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        TreeMap<Integer, Integer> cache = new TreeMap<>();
        for (int a0 = 0; a0 < t; a0++) {
            final int divisorLimit = in.nextInt() + 1;

            Map.Entry<Integer, Integer> ceilDivLimit = cache.ceilingEntry(divisorLimit);
            Map.Entry<Integer, Integer> floorDivLimit = cache.floorEntry(divisorLimit);

            int divisors = 0;
            int triangleNumber = 0;
            if (ceilDivLimit != null) {
                // Result is already known (either direct match, or next higher)
                // no need to calculate
                int i = ceilDivLimit.getValue();
                triangleNumber = (i * i + i) / 2; // triangle number: sum(k=1 to n) k = (k^2 + k) / 2
            } else {
                // Need to compute result, but can start with lowest key as starting value
                int i = floorDivLimit == null ? 1 : floorDivLimit.getValue();
                while (divisors < divisorLimit) {
                    triangleNumber = (i * i + i) / 2;
                    divisors = computeDivisors(triangleNumber);
                    // If the cache has no entry for this amount of divisors yet, that means this number is the first
                    // and hence the lowest one fot have this amount of divisors -> cache it for future use
                    if (cache.ceilingKey(divisors) == null) {
                        cache.put(divisors, i);
                    }
                    i++;
                }
            }
            System.out.println(triangleNumber);
        }
    }

    public static int computeDivisors(int n) {
        int divisors = 0;
        for (int div = 1; div < Math.sqrt(n); div++) {
            divisors += n % div == 0 ? 2 : 0;
        }
        if (Math.floor(Math.sqrt(n)) == Math.ceil(Math.sqrt(n))) divisors++;
        return divisors;
    }

}
