package solutions.graphs;

import java.util.LinkedList;
import java.util.Queue;

//Just creating a class to track our time. How many minutes has it been if we do it without it we cant really track the time or we might have to come up with a different solution.
class Pair {
    int row, col, time;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

//QN No 994
public class Rotting_Oranges {
    public int run() {

        int[][] grid = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

//      Just the normal making rows, cols, fresh and a queue with directions.
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        Queue<Pair> queue = new LinkedList<>();

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

//              If value is 2 put it in our queue and change the grid value to -1 so that i cant be changed or used.
                if (grid[r][c] == 2) {
                    queue.offer(new Pair(r,c,0));
                    grid[r][c] = -1;
                } else if (grid[r][c] == 1) {
//                  Else Add fresh ++.
                    fresh++;
                }
            }
        }

        return bfs(grid, fresh, directions, queue);
    }

    private int bfs(int[][] grid, int count, int[][] directions, Queue<Pair> queue ) {
        int made=0;

        int rows = grid.length;
        int cols = grid[0].length;

        int time = 0;

//      Here all we are doing is going through our queue and taking out the value and checking its direction.
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int row = curr.row, col=curr.col;
            time = curr.time;

            for (int[] dir: directions) {
                int nr = row + dir[0], nc = col + dir[1];

//              Here all we are doing is checking the direction and putting the value in our queue like before and changing the made variable.
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    queue.offer(new Pair(nr,nc,time+1));
                    grid[nr][nc] = -1;
                    made++;
                }
            }
        }

//      This is basically checking if we made it through all the ones and if it is equal to the count from our previous function if so return our time which is going to be the latest value in queue whose time is changed.
        if(made==count) return time;
        return -1;
    }
}
