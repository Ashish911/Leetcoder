package solutions.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Redundant_Connection {

    private int[] parent;
    private int[] rank;

    public void run() {
        int[][] edges = {
                {1,2},
                {2,3},
                {3,4},
                {1,4},
                {1,5}
        };

        int len = edges.length;

        parent = new int[len + 1];
        rank = new int[len + 1];

        // Initialize each node as its own parent
        for (int i = 1; i <= len; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (find(u) == find(v)) {
                System.out.println(edge[0]); // Cycle detected
            }
            join(u, v); // Merge sets
        }

        System.out.println(new int[0]);

    }

    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    private void join(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            // Union by rank
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}
