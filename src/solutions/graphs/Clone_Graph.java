package solutions.graphs;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Clone_Graph {
    public Node run() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // Set neighbors
        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        // Entry point
        Node node = node1;

        // First doing a edge case of if node is empty return null or its neighbor empty return new node with val.
        if (node == null) return null;
        if (node.neighbors.isEmpty()) return new Node(node.val);

//      A HashMap to keep track of which nodes we've copied. A Queue for BFS traversal.
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

//      Since were just starting put the value of the first node in our map and offer it up in queue for traversal.
        map.put(node, new Node(node.val));
        queue.offer(node);

//      Now all we are doing is looping our queue until it is empty.
        while (!queue.isEmpty()) {

//          First what we do in our loop is takeout our value or node from our queue.
            Node curr = queue.poll();

//          Now what we are doing is basically checking for its neighbors as for our values or examples above we can see that node1 has node2 and node4.
            for(Node neighbor: curr.neighbors) {
//              Now all we are doing is checking if we do or don't have neighbor in our hashmap. if we don't we basically create one node and add the value in our map and put the node up for offer.
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
//              Either way we put the neighbor or add it for our curr node anyway. We are getting the neighbor from the cp.get because if we just put neighbor it is going to reference to our original node and since we are cloning a graph we don't want that to happen as similar references can result to failure in cloning.
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }

        System.out.println(map.get(node));

        return map.get(node);
    }
}
