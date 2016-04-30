// Daniel Lopez - dql5295@psu.edu
// April 18, 2016
// UnionFind.java

// For testing the connectivity of undirected
//   graphs represented by adjacency matrices using an optimized UnionFind algorithm.

package connectivitytester;

public class UnionFindUndirected {
    int[] compID; // stores the ID of the connected component for each vertex
    int[] treeSize; // stores the size of the tree of connected components
    int size; // size of the matrix (number of vertices)
    
    public UnionFindUndirected(int size) {
        compID = new int[size];
        treeSize = new int[size];
        for (int i = 0; i < size; i++) {
            compID[i] = i;
            treeSize[i] = 1;
        }
        this.size = size;
    }
    
    public boolean connectivityForAdjMatrix(int[][] adjMatrix) {
        for (int i = 0; i < size; i++) { // O(n) complexity
            for (int ii = i+1; ii < size; ii++) { // O(n) complexity

                // if this statement == 1, then i and ii are connected
                if (adjMatrix[i][ii] == 1) {
                    union(i, ii);  // log(n) complexity [log base 2]
                }
            }
        }
        return connectedGraph();
    }
    
    private boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
    
    private boolean connectedGraph() {
        boolean connected = true;
        int root = findRoot(0);
        for (int i = 1; i < size; i++) {
            if (findRoot(i) != root)
                connected = false;
        }
        return connected;
    }
    
    // complexity log(n) [log base 2]
    private void union(int p, int q) {
        
        int firstRoot = findRoot(p); // complexity log(n)
        int secondRoot = findRoot(q); // complexity log(n)
        
        // append the smaller connected component tree onto the larger tree by
        // assigning the root of the smaller to the root of the larger
        if (treeSize[firstRoot] > treeSize[secondRoot]) {
            compID[firstRoot] = secondRoot;
            treeSize[firstRoot] += treeSize[secondRoot];
        } else {
            compID[secondRoot] = firstRoot;
            treeSize[secondRoot] += treeSize[firstRoot];    
        }
        
    }
    
    // complexity log(n) [log base 2]
    private int findRoot(int p) {
        while (p != compID[p]) {
            // set the root of each child to its parent, until it becomes the top root itself
            compID[p] = compID[compID[p]];
            p = compID[p];
        }
        return p;
    }
    
    
}
