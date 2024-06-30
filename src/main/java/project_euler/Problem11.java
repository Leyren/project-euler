package project_euler;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler011/problem
 */
public class Problem11 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int[][] grid = new int[20][20];
        for (int grid_i = 0; grid_i < 20; grid_i++) {
            for (int grid_j = 0; grid_j < 20; grid_j++) {
                grid[grid_i][grid_j] = in.nextInt();
            }
        }

        long max = -1;
        // Rows
        for (int r = 0; r < 20; r++) {
            long p;
            for (int c = 3; c < 20; c++) {
                p = 1;
                for (int i = 0; i < 4; i++) {
                    p *= grid[r][c - i];
                }
                max = Math.max(p, max);
            }
        }

        // Columns
        for (int c = 0; c < 20; c++) {
            long p;
            for (int r = 3; r < 20; r++) {
                p = 1;
                for (int i = 0; i < 4; i++) {
                    p *= grid[r - i][c];
                }
                max = Math.max(p, max);
            }
        }

        // Diagonals 1
        for (int r = 3; r < 20; r++) {
            long p;
            for (int c = 3; c < 20; c++) {
                p = 1;
                for (int i = 0; i < 4; i++) {
                    p *= grid[r - i][c - i];
                }
                max = Math.max(p, max);
            }
        }

        // Diagonals 2
        for (int r = 3; r < 20; r++) {
            long p;
            for (int c = 0; c < 17; c++) {
                p = 1;
                for (int i = 0; i < 4; i++) {
                    p *= grid[r - i][c + i];
                }
                max = Math.max(p, max);
            }
        }

        System.out.println(max);
    }
}
