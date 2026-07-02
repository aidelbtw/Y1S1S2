public class L10Q3TestGraph {
    public static void main(String[] args) {
        L10Q3Graph<String, Integer> graph1 = new L10Q3Graph<>();

        String[] cities = {"Alor Setar", "Kuching", "Langkawi", "Melaka", "Penang", "Tawau"};
        for (String city : cities) {
            graph1.addVertex(city);
        }

        System.out.println("The number of vertices in graph1: " + graph1.getSize());
        System.out.println();

        System.out.println("Is Melaka in the Graph? " + graph1.hasVertex("Melaka"));
        System.out.println("Is Ipoh in the Graph? " + graph1.hasVertex("Ipoh"));
        System.out.println();

        // addEdge (directed)
        System.out.println("add edge Kuching - Melaka: " + graph1.addEdge("Kuching", "Melaka"));
        System.out.println("add edge Langkawi - Penang : " + graph1.addEdge("Langkawi", "Penang"));
        System.out.println("add edge Melaka - Penang : " + graph1.addEdge("Melaka", "Penang"));
        System.out.println("add edge Alor Setar - Kuching : " + graph1.addEdge("Alor Setar", "Kuching"));
        System.out.println("add edge Tawau - Alor Setar : " + graph1.addEdge("Tawau", "Alor Setar"));
        System.out.println("add edge Kuching - Tawau : " + graph1.addEdge("Kuching", "Tawau"));
        System.out.println();

        // addUndirectedEdge
        System.out.println("add undirected edge Penang - Tawau : " + graph1.addUndirectedEdge("Penang", "Tawau"));
        System.out.println();

        // hasEdge
        System.out.println("has edge from Kuching to Melaka? " + graph1.hasEdge("Kuching", "Melaka"));
        System.out.println("has edge from Melaka to Kuching? " + graph1.hasEdge("Melaka", "Kuching"));
        System.out.println();

        // removeEdge
        System.out.println("remove edge Kuching - Tawau: " + graph1.removeEdge("Kuching", "Tawau"));
        System.out.println("has edge from Kuching to Tawau after removal? " + graph1.hasEdge("Kuching", "Tawau"));
        System.out.println();

        // In/out degree
        System.out.println("In and out degree for Kuching is " + graph1.getIndeg("Kuching") + " and " + graph1.getOutdeg("Kuching"));
        System.out.println("In and out degree for Penang is " + graph1.getIndeg("Penang") + " and " + graph1.getOutdeg("Penang"));
        System.out.println();

        System.out.println("Print Edges : ");
        graph1.printEdges();
    }
}