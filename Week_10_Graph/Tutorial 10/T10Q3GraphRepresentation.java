public class T10Q3GraphRepresentation {
    public static void main(String[] args) {
        int[][] graph = {
            //A  B  C  D  E  F  G  H  I
            { 0, 0, 1, 1, 0, 0, 0, 0, 0 }, // A
            { 0, 0, 0, 1, 0, 0, 0, 0, 0 }, // B
            { 0, 0, 0, 0, 1, 1, 0, 0, 0 }, // C
            { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, // D
            { 0, 0, 0, 0, 0, 0, 1, 0, 0 }, // E
            { 0, 0, 0, 0, 0, 0, 0, 1, 0 }, // F
            { 0, 0, 0, 0, 0, 0, 0, 1, 0 }, // G
            { 0, 0, 0, 0, 0, 0, 0, 0, 1 }, // H
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }  // I
        };

        char[] labels = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        L10Q3Graph<Character, Integer> g = new L10Q3Graph<>();

        // Add all vertices
        for (char label : labels) {
            g.addVertex(label);
        }

        // Add edges based on the adjacency matrix
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1) {
                    g.addEdge(labels[i], labels[j]);
                }
            }
        }

        System.out.println("Number of vertices: " + g.size);
        System.out.println();
        System.out.println("Adjacency List (Linked-List Representation):");
        g.printEdges();
    }
}

