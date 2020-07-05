package ds.dp;

public class Paths {
    public static void main(String[] args) {
        Paths p = new Paths();
        System.out.println(p.uniquePathsWithObstacles(new int[2][3]));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < row; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < col; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[row - 1][col - 1];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return lcsLength(text1.toCharArray(), 0, text1.length() - 1, text2.toCharArray(), 0, text2.length() - 1);
    }

    private int lcsLength(char[] text1, int i, int m, char[] text2, int j, int n) {
        if (i == m || j == n) {
            return 0;
        } else if (text1[i] == text2[j]) {
            return 1 + lcsLength(text1, i + 1, m, text2, j + 1, n);
        } else {
            return Math.max(lcsLength(text1, i + 1, m, text2, j, n), lcsLength(text1, i, m, text2, j + 1, n));
        }
    }
}
