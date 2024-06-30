package project_euler;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;


/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler002/problem?isFullScreen=true
 */
public class Problem2 {

    // Use memoization and build up the range of all possible sums beforehand, so that later it just needs to be queried
    private static TreeMap<Long, Long> fibCache = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        fillFibCache(40_000_000_000_000_000L);
        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());
                System.out.println(fibCache.floorEntry(n).getValue());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    private static void fillFibCache(long limit) {
        long x1 = 1;
        long x2 = 2;
        long sum = 2;
        while (x2 < limit) {
            long tmp = x2;
            x2 = x1 + x2;
            x1 = tmp;
            if (x2 % 2 == 0) {
                sum += x2;
                fibCache.put(x2, sum);
            }
        }
    }

}

