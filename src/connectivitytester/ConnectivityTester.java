// Daniel Lopez - dql5295@psu.edu
// April 18, 2016
// ConnectivityTester.java

// For testing the connectivity of undirected
//   graphs represented by adjacency matrices.

package connectivitytester;

public class ConnectivityTester {
    
    public static void main(String[] args) {
        
        Graphs graphs = new Graphs();
        final int SIZE = graphs.adjMatrixA.length; // assuming all matrices are of same size
        
        System.out.println("Kosaraju Algorithm (Directed Graphs): ");
        KosarajuDFS kdfs1 = new KosarajuDFS(SIZE, graphs.adjMatrixA);
        System.out.print("\tAdjacency Matrix A: ");
        System.out.println(kdfs1.connectedGraph());
        
        KosarajuDFS kdfs2 = new KosarajuDFS(SIZE, graphs.adjMatrixB);
        System.out.print("\tAdjacency Matrix B: ");
        System.out.println(kdfs2.connectedGraph());
        
        BFSUndirected bfs = new BFSUndirected(SIZE);
        System.out.println("BFS Algorithm (Undirected Graphs): ");
        System.out.println("\tAdjacency Matrix C: " + bfs.connectedGraph(graphs.adjMatrixC));
        System.out.println("\tAdjacency Matrix D: " + bfs.connectedGraph(graphs.adjMatrixD));
        
//      UnionFindUndirected uf = new UnionFindUndirected(SIZE);        
//      System.out.print("Union-Find Algorithm: ");      
//      System.out.println(uf.connectivityForAdjMatrix(graphs.adjMatrixC)); 
    } 
}

/****************** Sample Output *******************\
 
Kosaraju Algorithm (Directed Graphs): 
	Adjacency Matrix A: true
	Adjacency Matrix B: false
BFS Algorithm (Undirected Graphs): 
	Adjacency Matrix C: true
	Adjacency Matrix D: false
 
\****************************************************/

class Graphs {
    int adjMatrixA[][];
    int adjMatrixB[][];
    int adjMatrixC[][];
    int adjMatrixD[][];
    
    public Graphs() {
        
        // Directed Graphs (A & B)
        adjMatrixA = new int[][]{
                {0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0}
        };
        
        adjMatrixB = new int[][]{
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1}
        };
        
        // Undirected Graphs (C & D)
        adjMatrixC = new int[][]{
                {0, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0}
        };
        
        adjMatrixD = new int[][]{
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0}
        }; 
        
    }
}