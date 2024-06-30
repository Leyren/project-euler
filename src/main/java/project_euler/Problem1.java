package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler001/problem
 */
public class Problem1 {

    // (100 - 1) / 3 = 33 -> there are 33 multiples of 3 in 100
    // sum(k=1 to n) 3*k = (3/2)*n*(n+1)
    // e.g. (3/2)*33*34 = 1683

    // (100 - 1) / 5 = 19 -> there are 19 multiples of 5 in 100
    // sum(k=1 to n) 5*k = (5/2)*n*(n+1)
    // e.g. (5/2)*19*20 = 950

    // final step: Remove duplicates (aka sum of those divisible by 15, since those are counted in both)
    // (100 - 1) / 15 = 6 -> there are 6 multiples of 15 in 100
    // sum(k=1 to n) 15*k = (15/2)*n*(n+1)
    // e.g. (15/2)*6*7 = 315

    // Total: 1683+950-315 = 2318

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                System.out.println(sumOfMultiplesOf3And5(n));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    public static long sumOfMultiplesOf3And5(int n) {
        return sumMultiplesOf((n - 1) / 3, 3)
                + sumMultiplesOf((n - 1) / 5, 5)
                - sumMultiplesOf((n - 1) / 15, 15);
    }

    private static long sumMultiplesOf(long count, long base) {
        return (base * count * (count + 1)) / 2;
    }
}

