package project_euler;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler010/problem
 */
public class Problem10 {

    /*
     * Idea: Memoization
     *
     * Determining primes is expensive, and we query up to 10'000 times, which in worst case would require
     * determining all primes <1'000'000 for 10'000 times each.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        TreeMap<Integer, Long> sums = primeSums(1_000_000);

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                System.out.println(sums.get(sums.floorKey(n)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    // Generate all the primes and their sum, using the Sieve of Erastothenes algorithm
    // https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
    public static TreeMap<Integer, Long> primeSums(int limit) {
        TreeMap<Integer, Long> result = new TreeMap<>();
        result.put(1, 0L);

        boolean[] primes = new boolean[limit+1];
        for (int i = 2; i < primes.length; i++) {
            primes[i] = true;
        }

        int last = 1;
        for (int n = 2; n <= limit; n++) {
            if (primes[n]) {
                result.put(n, result.get(last) + n);
                last = n;
                if ((long)n * n <= limit){
                    for(int i = n*n; i <= limit; i += n){
                        primes[i] = false;
                    }
                }
            }
        }

        return result;
    }
}
