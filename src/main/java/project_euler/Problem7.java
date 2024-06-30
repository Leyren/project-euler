package project_euler;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler007/problem
 */
public class Problem7 {

    public static final int PRIME_COUNT = 10000;

    /*
     * Idea: Memoization
     * Querying the first 10'000 prime numbers up to 1000 times, hence for worst case runtime computing the
     * 10'000 prime numbers beforehand is significantly faster.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        // Fill list of first PRIME_COUNT primes
        List<Integer> primes = determineFirstNPrimes(PRIME_COUNT);

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                // Simply return the prime at n-th position (-1 due to indexing)
                System.out.println(primes.get(n - 1));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    private static List<Integer> determineFirstNPrimes(int amount) {
        List<Integer> primes = new ArrayList<>(amount);
        primes.add(2);
        int count = 1;
        int candidate = 3;
        while (count < amount) {
            if (isPrime(candidate)) {
                primes.add(candidate);
                count++;
            }
            candidate += 2;
        }
        return primes;
    }

    public static boolean isPrime(int n) {
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int limit = (int) Math.sqrt(n);
        for (int div = 3; div <= limit; div += 2) {
            if (n % div == 0) return false;
        }
        return true;
    }
}
