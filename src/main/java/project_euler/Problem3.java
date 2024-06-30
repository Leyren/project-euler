package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler003/problem
 */
public class Problem3 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());
                long rest = determineHighestPrimeFactor(n);

                System.out.println(rest);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    // The highest prime factor will always be the last one left over, if we iterate through all divisors from bottom up
    private static long determineHighestPrimeFactor(long n) {
        long rest = n;
        int divisor = 2;
        long limit = (long) Math.sqrt(n);

        while (divisor <= limit && divisor < rest) {
            if (rest % divisor == 0) {
                rest = rest / divisor;
            } else {
                divisor += divisor == 2 ? 1 : 2;
            }
        }
        return rest;
    }

}


