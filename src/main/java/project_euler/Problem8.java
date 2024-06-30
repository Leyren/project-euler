package project_euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler008/problem
 */
public class Problem8 {
    /*
     * Idea: To get the greatest product of consecutive digits, we need to have a sliding frame of digits to
     * determine all the products
     * e.g.
     * 3675356291 with K=5
     * 36753
     *  67535
     *   65356
     *    53562
     *     35629
     *      56291
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                int k = Integer.parseInt(firstMultipleInput[1]);
                String num = bufferedReader.readLine();

                int max = determineHighestProductOfConsecutiveDigits(k, num);
                System.out.println(max);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    private static int determineHighestProductOfConsecutiveDigits(int frameSize, String numString) {
        // Determine the first product (Note, number is expected to have at least K digits)
        int product = 1;
        for (int i = 0; i < frameSize; i++) {
            int digit = Character.getNumericValue(numString.charAt(i));
            product *= digit;
        }
        int max = product;

        // Determine the next product by dividing the last digit (removed from the frame)
        // and multiplying the next one (added to the frame)
        // NOTE: If the last digit was a 0, the product is 0 and the next one needs to be calculated from scratch
        // Technically could skip all of this immediately, but it works.
        for (int i = frameSize; i < numString.length(); i++) {
            int lastDigit = Character.getNumericValue(numString.charAt(i - frameSize));
            int digit = Character.getNumericValue(numString.charAt(i));
            if (lastDigit == 0) {
                // need to recalculate fully
                product = 1;
                for (int j = i - frameSize + 1; j <= i; j++) {
                    int d = Character.getNumericValue(numString.charAt(j));
                    product *= d;
                }
            } else {
                product = (product / lastDigit) * digit;
            }

            if (product > max) {
                max = product;
            }
        }
        return max;
    }
}
