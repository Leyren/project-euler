package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;


/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler009/problem
 */
public class Problem9 {

    // Idea: MATH
    // find max(a*b*c) where a + b + c = N and a^2 + b^2 = c^2

    // a^2 + b^2 = c^2
    // a+b+c = N

    // c = N - a - b
    // a^2 + b^2 = (N-a-b)^2
    // a^2 + 2 a b - 2 a n + b^2 - 2 b n + n^2 - a^2 - b^2 = 0
    // 2ab-2an-2bn+n^2 = 0
    // b = ((2 a - n) n)/(2 (a - n))
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                determinePythagoreanTriplet(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    private static void determinePythagoreanTriplet(int n) {
        if (n % 2 == 1) {
            System.out.println("-1");
        } else {
            long max = -1;
            for (int a = 1; a <= n / 3; a++) {
                int b = (n * n - 2 * n * a) / (2 * n - 2 * a);
                int c = n - a - b;
                if (a * a + b * b == c * c) {
                    long r = (long) a * b * c;
                    max = Math.max(r, max);
                }
            }

            System.out.println(max);
        }
    }
}
