// Daniel Lopez - dql5295@psu.edu
// April 18, 2016
// BFS.java

// For testing the connectivity of undirected
//   graphs represented by adjacency matrices by using a BFS search algorithm.

package connectivitytester;

import java.util.LinkedList;

public class BFSUndirected {
    
    int size;
    public BFSUndirected(int size) {
        this.size = size;
    }
    
    // time complexity O(n^2)
    boolean connectedGraph(int[][]adjMatrix) {
        boolean pass = true;
        
        int[] compID = new int[size];
        int graphID = size+1; // can be anything but 0, just an ID to identify
        // the components connected to the first vertex, 0
        
        // use a LinkedList as a queue
        LinkedList<Integer> queue = new LinkedList<>();
        compID[0] = graphID; // 0 is connected to 0, so it is part of the connected graph
        
        // add the first vertex to the queue and start from there
        queue.add(0);
        
        // worst case for the while loop is O(n) complexity for the longest graph  
        // of type: (0 <-> 1 <-> 2 <-> 3 <-> 4 [and no other connections])
        while (!queue.isEmpty()) { // remove a labeled vertex from the queue
            
            // get the vertex to be analyzed for adjacenct vertices from queue
            int vertexInQuestion = queue.remove();
            
            // O(n) complexity
            for (int i = 1; i < size; i++) {
                // if vertexInQuestion and i are adjancent, and i is not a part 
                // of the connected component of vertex 0
                if (adjMatrix[vertexInQuestion][i] == 1 && compID[i] == 0) { 
                    // add i to the queue, to later analyze it's adjacent edges
                    queue.add(i);
                    // set its component ID to the main connected component of the graph
                    compID[i] = graphID;
                }
            }
        }
        
        // O(n) complexity
        for (int i = 0; i < size; i++) {  
            // if all vertices have the same ID, then the graph is connected
            if (compID[i] != graphID) {
                pass = false;
                i = size;
            }
        }
        return pass;
    }
}
