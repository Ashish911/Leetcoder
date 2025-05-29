package solutions.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Course_Schedule_Two {

    public void run() {
        int numCourses = 4;
        int[][] prerequisite = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        };

        // Just making a graph with list of list of integers
        List<List<Integer>> graph = new ArrayList<>();

        // Just an arr of integers.
        int[] inDegree = new int[numCourses];

        // All we are doing here is looping through our number of courses and adding in our graph as new arr list
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Looping through our prerequisities.
        for (int[] pair: prerequisite) {
            // Getting the indexed graph and adding the value. Then incrementing our arr of integers.
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        // Creating a queue.
        Queue<Integer> queue = new LinkedList<>();

        // Loping through our num of courses and if value is 0 adding in queue.
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        // Track the number of courses that can be completed
        int index = 0;
        int[] order = new int[numCourses];

        // Process courses in topological order
        while (!queue.isEmpty()) {
            int curr = queue.poll(); // take course with no remaining prerequisites
//            What is different here is we are putting the value of curr in the next index as since 0 is almost always going to be first and after that the index is going to be greater and greater.
            order[index++] = curr;

            // Reduce in-degree of all dependent courses (neighbors)
            for (int neighbor: graph.get(curr)) {
                inDegree[neighbor]--;
                // If a neighbor now has no prerequisites, add it to the queue
                if (inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        System.out.println(index == numCourses ? order : new int[0]);

    }
}
