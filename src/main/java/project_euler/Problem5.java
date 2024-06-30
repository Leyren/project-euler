package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler005/problem
 */
public class Problem5 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        // Create for each number their prime factors, as a frequency map
        // E.g. 24 = 2 x 2 x 2 x 3
        // 2: 3
        // 3: 1
        Map<Integer, Map<Integer, Integer>> primfactors = new HashMap<>();
        for (int i = 1; i <= 40; i++) {
            primfactors.put(i, primfactors(i));
        }

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                Map<Integer, Integer> res = new HashMap<>();

                // For the queried value, aggregate the prime factors of each number it should be divisible by
                // the smallest multiple is the multiplication of the maximum of these prime factors
                // so if a value requires a higher amount, keep that (e.g. 24 requires 2^3, while 20 only requires 2^2)
                for (int i = 1; i <= n; i++) {
                    primfactors.get(i).forEach((k, v) ->
                            res.compute(k, (k2, v2) -> v2 == null || v2 < v ? v : v2));
                }

                // create the smallest multiple
                // the smallest multiple is the multiplication of the maximum of these prime factors
                int result = 1;
                for (Integer k : res.keySet()) {
                    result *= pow(k, res.get(k));
                }
                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    public static int pow(int a, int n) {
        if (n == 1) return a;
        if (n % 2 == 0) return pow(a * a, n / 2);
        return a * pow(a, n - 1);
    }

    public static Map<Integer, Integer> primfactors(int n) {
        int divisor = 2;
        int rest = n;
        Map<Integer, Integer> primfactors = new HashMap<>();
        if (n == 1) {
            primfactors.put(1, 1);
            return primfactors;
        }
        while (divisor <= n) {
            if (rest % divisor == 0) {
                rest = rest / divisor;
                primfactors.compute(divisor, (k, v) -> v == null ? 1 : v + 1);
            } else {
                divisor += divisor == 2 ? 1 : 2;
            }
        }
        return primfactors;
    }
}
