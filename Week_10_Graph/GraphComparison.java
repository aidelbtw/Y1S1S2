import java.util.*;

public class GraphComparison {

    // ===================== ADJACENCY MATRIX =====================
    static class MatrixGraph {
        int[][] matrix;
        int numVertices;
        String[] names; // just to print friendly labels

        MatrixGraph(String[] vertexNames) {
            numVertices = vertexNames.length;
            names = vertexNames;
            matrix = new int[numVertices][numVertices]; // all 0 = no edge
        }

        void addEdge(int i, int j, int weight) {
            matrix[i][j] = weight;
            matrix[j][i] = weight; // undirected: mirror it
        }

        boolean hasEdge(int i, int j) {
            return matrix[i][j] != 0;
        }

        List<Integer> getNeighbours(int i) {
            List<Integer> neighbours = new ArrayList<>();
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] != 0) {
                    neighbours.add(j);
                }
            }
            return neighbours;
        }

        void printMatrix() {
            System.out.print("      ");
            for (String n : names) System.out.printf("%-8s", n);
            System.out.println();
            for (int i = 0; i < numVertices; i++) {
                System.out.printf("%-6s", names[i]);
                for (int j = 0; j < numVertices; j++) {
                    System.out.printf("%-8d", matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    // ===================== ADJACENCY LIST =====================
    static class ListGraph {
        // each index = a vertex, its List<int[]> = {neighbourIndex, weight} pairs
        List<List<int[]>> adjList;
        String[] names;

        ListGraph(String[] vertexNames) {
            names = vertexNames;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertexNames.length; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int i, int j, int weight) {
            adjList.get(i).add(new int[]{j, weight});
            adjList.get(j).add(new int[]{i, weight}); // undirected: mirror it
        }

        boolean hasEdge(int i, int j) {
            for (int[] edge : adjList.get(i)) {
                if (edge[0] == j) return true;
            }
            return false;
        }

        List<Integer> getNeighbours(int i) {
            List<Integer> neighbours = new ArrayList<>();
            for (int[] edge : adjList.get(i)) {
                neighbours.add(edge[0]);
            }
            return neighbours;
        }

        void printList() {
            for (int i = 0; i < adjList.size(); i++) {
                System.out.print(names[i] + " -> ");
                List<String> parts = new ArrayList<>();
                for (int[] edge : adjList.get(i)) {
                    parts.add(names[edge[0]] + "(w=" + edge[1] + ")");
                }
                System.out.println(String.join(", ", parts));
            }
        }
    }

    // ===================== DEMO =====================
    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E"};
        // Sparse graph: only 4 edges among 5 vertices
        int[][] edges = {
            {0, 1, 5},  // A-B weight 5
            {0, 2, 3},  // A-C weight 3
            {1, 3, 7},  // B-D weight 7
            {3, 4, 2}   // D-E weight 2
        };

        MatrixGraph mg = new MatrixGraph(names);
        ListGraph lg = new ListGraph(names);
        for (int[] e : edges) {
            mg.addEdge(e[0], e[1], e[2]);
            lg.addEdge(e[0], e[1], e[2]);
        }

        System.out.println("===== ADJACENCY MATRIX =====");
        mg.printMatrix();
        System.out.println();

        System.out.println("===== ADJACENCY LIST =====");
        lg.printList();
        System.out.println();

        System.out.println("===== COMPARISON CHECKS =====");
        System.out.println("Does A-D edge exist?");
        System.out.println("  Matrix says: " + mg.hasEdge(0, 3));
        System.out.println("  List says:   " + lg.hasEdge(0, 3));

        System.out.println("Does A-C edge exist?");
        System.out.println("  Matrix says: " + mg.hasEdge(0, 2));
        System.out.println("  List says:   " + lg.hasEdge(0, 2));

        System.out.println("Neighbours of B (index 1):");
        System.out.println("  Matrix: " + mg.getNeighbours(1));
        System.out.println("  List:   " + lg.getNeighbours(1));

        System.out.println();
        System.out.println("===== SPACE USED (regardless of edge count) =====");
        System.out.println("Matrix always allocates: " + names.length + " x " + names.length
                + " = " + (names.length * names.length) + " ints");
        int listEntries = 0;
        for (List<int[]> l : lg.adjList) listEntries += l.size();
        System.out.println("List only stores: " + listEntries + " entries total (one per edge direction)");
        System.out.println("-> With only " + edges.length + " edges, the list uses far less space,");
        System.out.println("   while the matrix wastes space on all the non-existent edges (the many 0s above).");
    }
}