package solutions.graphs;

import java.util.List;

public class Surrounded_Regions {

    public void run() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', '0', '0', 'X'},
                {'X', 'X', '0', 'X'},
                {'X', '0', 'X', 'X'}
        };

//         Here all i am doing is getting the total no of rows and cols in our matrix.
        int rows = board.length;
        int cols = board[0].length;

//      Making a matrix of booleans of the same size as our board to check if the current element is visited or not.
        boolean[][] visited = new boolean[rows][cols];

//        Looping through the rows.
        for(int r = 0; r < rows; r++) {
//            All we are checking is if the selected element value is 0 or not. If so DFS.
            if (board[r][0] == '0') dfs(r, 0, board, visited);
            if (board[r][cols - 1] == '0') dfs(r, cols - 1, board, visited);
        }

//        Looping through the cols.
        for (int j = 0; j < cols; j++) {
//            All we are checking is if the selected element value is 0 or not. If so DFS.
            if (board[0][j] == '0') dfs(0, j, board, visited);
            if (board[rows - 1][j] == '0') dfs(rows - 1, j, board, visited);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '0' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }

        System.out.println(board);

    }

    private void dfs(int x, int y, char[][] board, boolean[][] vis) {
//        Get rows and cols
        int n = board.length;
        int m = board[0].length;
//        Put current as visited.
        vis[x][y] = true;

//        All four indexes of x and y.
        int[] delx = {-1, 0, 1, 0};
        int[] dely = {0, 1, 0, -1};

//        Loop through the indexes.
        for (int i = 0; i < 4; i++) {
//            Get values after changes from index.
            int newx = x + delx[i];
            int newy = y + dely[i];
/* All we are checking here is 3 conditions. Like in almost any graph solutions.
    1: If the new direction x is greater than 0 and is less than our rows value. Cuz there is no -1 index in our matrix and if value is higher than rows it will be arr out of bounds.
    2: If the new direction y is greater than 0 and is less than our cols value. Same as above.
    3: If the new direction value is not visited and the value is 0 go backtrack in dfs with the new indexes as x and y.
    SIMPLEEEE
 */
            if (newx >= 0 && newx < n && newy >= 0 && newy < m &&
                    !vis[newx][newy] && board[newx][newy] == 'O') {
                dfs(newx, newy, board, vis);
            }
        }
    }


}
