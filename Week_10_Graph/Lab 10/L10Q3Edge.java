public class L10Q3Edge<T extends Comparable<T>, N extends Comparable<N>> {
    L10Q3Vertex<T,N> toVertex;
    L10Q3Edge<T, N> nextEdge;

    public L10Q3Edge() {
        toVertex = null;
        nextEdge = null;
    }

    public L10Q3Edge(L10Q3Vertex<T,N> destination, L10Q3Edge<T,N> a){
        toVertex = destination;
        nextEdge = a;
    }
}
