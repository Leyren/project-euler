package project_euler;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler013/problem
 */
public class Problem13 {

    /*
     * Idea: We compute the sum of 1000 numbers of 50-digits each, but only care about the first (most significant)
     * 10 digits.
     * This means that only the first 13 digits can impact this result (since 1000 values of 1 on the 13th digit would sum up to 1 on the 10th digit)
     * So all digits past the 13th can simply be ignored.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long sum = 0;
        for (int a0 = 0; a0 < t; a0++) {
            sum += Long.parseLong(in.next().substring(0, 13));
        }
        System.out.println(String.valueOf(sum).substring(0, 10));
    }
}
