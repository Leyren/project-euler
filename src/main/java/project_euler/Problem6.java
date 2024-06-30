package project_euler;
import java.io.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler006/problem
 */
public class Problem6 {

    /*
     * Just math using sum formula.
     *
     * https://www.wolframalpha.com/input?i=%28sum%28k%3D1%2Cn%29+k%5E2%29+-+%28sum%28k%3D1%2Cn%29k%29%5E2
     * (sum_(k=1)^n k)^2 - sum_(k=1)^n k^2
     * = 1/4 n^2 (n + 1)^2 - 1/6 n (n + 1) (2 n + 1)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Integer.parseInt(bufferedReader.readLine().trim());

                long sumOfSquares = n * (n + 1) * (2 * n + 1) / 6;
                long squareOfSum = n * n * (n + 1) * (n + 1) / 4;
                System.out.println(squareOfSum - sumOfSquares);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
