package solutions.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pacific_Atlantic_Water_Flow {

    public List<List<Integer>>  run() {

        int[][] heights = new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };

        int row = heights.length;
        int cols = heights[0].length;
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] pacific = new boolean[row][cols];
        boolean[][] atlantic = new boolean[row][cols];

        for (int i = 0; i < row; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            dfs(heights, pacific, 0, j);
            dfs(heights, atlantic, row - 1, j);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        System.out.println(result);
        return result;

    }

    private void dfs(int[][] heights, boolean[][] ocean, int row, int col) {
        int n = heights.length;
        int m = heights[0].length;
        ocean[row][col] = true;
        int[] delRow = {-1, 1, 0, 0};
        int[] delCol = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m &&
                    !ocean[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, ocean, newRow, newCol);
            }
        }
    }
}
