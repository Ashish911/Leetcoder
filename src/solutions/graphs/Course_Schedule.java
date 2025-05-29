package solutions.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Course_Schedule {
    public void run() {
        int numCourses = 2;
        int[][] prerequisite = {
                {1,0},
                {0,1},
        };

//      Just making a graph with list of list of integers.z
        List<List<Integer>> graph = new ArrayList<>();

//      Just an arr of integers.
        int[] inDegree = new int[numCourses];

//      All we are doing here is looping through our number of courses and adding in our graph as new arr list
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

//      Looping through our prerequisities.
        for (int[] pair: prerequisite) {
//            Getting the indexed graph and adding the value. Then incrementing our arr of integers.
            graph.get(pair[0]).add(pair[1]);
            inDegree[pair[1]]++;
        }

//        Creating a queue.
        Queue<Integer> queue = new LinkedList<>();

//        Loping through our num of courses and if value is 0 adding in queue.
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        // Track the number of courses that can be completed
        int visited = 0;

        // Process courses in topological order

        while (!queue.isEmpty()) {
            int curr = queue.poll(); // take course with no remaining prerequisites
            visited++; // mark it as "taken"

            // Reduce in-degree of all dependent courses (neighbors)
            for (int neighbor: graph.get(curr)) {
                inDegree[neighbor]--;

                // If a neighbor now has no prerequisites, add it to the queue
                if (inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        System.out.println(visited == numCourses);
    }
}
