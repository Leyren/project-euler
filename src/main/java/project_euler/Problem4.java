package project_euler;

import java.io.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;


public class Problem4 {

    /*
    * Idea:
    * With a number-range from 101'101 - 1'000'000 there are only ~900 possible palindromes
    * Since we want to find the biggest palindrome, construct palindromes starting from the provided maximum
    *
    * e.g. N=987123
    * start with 986689, and see if there are any divisors for it
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim()) - 1;
                int leftSideStart = n / 1000;
                out:
                for (int left = leftSideStart; left > 100; left--) {
                    // construct palindrome
                    int palindrome = left * 1000 + reverse(left);

                    // might need to skip initially (e.g. N=987123, first palindrome would be 987789)
                    if (palindrome > n) continue;

                    // find divisor for palindrome (has to be at least 'left', since otherwise the second factor would be >1000)
                    // e.g. 987789 -> factor would need to be at least 987 for the second factor to be <1000
                    for (int div = left + 1; div < 1000; div++) {
                        if (palindrome % div == 0) {
                            System.out.println(palindrome);
                            break out;
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    // Helper method to reverse a number, e.g. 579 -> 975
    public static int reverse(int n) {
        int result = 0;
        while (n > 0) {
            int digit = n % 10;
            n = n / 10;
            result = result * 10 + digit;
        }
        return result;
    }
}
