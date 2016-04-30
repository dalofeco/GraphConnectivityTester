// Daniel Lopez - dql5295@psu.edu
// April 24, 2016
// BFS.java

// For testing the connectivity of directed
//   graphs represented by adjacency matrices by using Kosaraju's DFS search algorithm.

package connectivitytester;

import java.util.LinkedList;

public class KosarajuDFS {
    
    int size; // stores the number of vertices (size = n)
    boolean[] visited; // stores whether the vertex has been visited by algorithm
    boolean[]visitedRev; // stores wheter the vertex has been visited in reversed (second) pass
    int[][]adjMatrix; // adjacency matrix to anaylize
    int counter = 1;
//    int[][]reversedAdjMatrix; // reversed adjacency matrix for second pass
    LinkedList<Integer> stack = new LinkedList<>();  // stack to keep elements visited
    
    public KosarajuDFS(int size, int[][]adjMatrix) {
        this.size = size; 
        visited = new boolean[size];
        visitedRev = new boolean[size];

        this.adjMatrix = adjMatrix;
//        this.reversedAdjMatrix = reverseAdjMatrix(adjMatrix);
    }
    
    // O(n^2) complexity
    public boolean connectedGraph() {
        boolean pass = false;
        visit(0); // start at arbitrary vertex 0
        if (checkAllVisited(visited)) { // if all vertices have been visited
            reverseVisit(stack.pop()); // try to visit them from last to last finished vertex (w/ reversed graph)
            if (checkAllVisited(visitedRev)) // if all visited this reversed
                pass = true;    // then the graph is strongly connected
            
        }
        
        
        return pass;
    }
    
    // O(n) complexity
    private boolean checkAllVisited(boolean[] visits) {
        boolean pass = true;
        for (int i = 0; i < size; i++) {
            if (!visits[i]) {
                pass = false;
                break;
            }
        }
        return pass;
    }
    
    // O(n^2) complexity
    @Deprecated
    private int[][] reverseAdjMatrix(int[][] matrix) {
        int[][]reversedMatrix = new int[size][size];
        for (int i = 0; i < size; i++) { // n times
            for (int ii = 0; ii < size; ii++) { // n times
                reversedMatrix[ii][i] = matrix[i][ii];
            }
        }
        return reversedMatrix;
    }
    
    // O(n^2) complexity 
    private void visit(int vertex) { // DFS visiting using recursion
        visited[vertex] = true;  // mark the vertex as visited

        for (int i = 0; i < size; i++) {  // check every entry on the adj matrix for the vertex being visited
            if (adjMatrix[vertex][i] == 1) { // if one of these is adjacent 
                if (!visited[i]) {
                    visit(i);   // call the visit function on it
                    stack.push(i);
                }
            }
        }
        
    }
    
    // O(n^2) complexity
    private void reverseVisit(int vertex) {
        if (!visitedRev[vertex]) { // if body only runs up to N times in recursive calls 
            visitedRev[vertex] = true; // mark the vertex as visited
            for (int i = 0; i < size; i++) { 
                if (adjMatrix[i][vertex] == 1) { // the adj matrix is reversed by flipping i and vertex
                    reverseVisit(i);

                }
            }
        }
    }
    
    
    
    
    
}
