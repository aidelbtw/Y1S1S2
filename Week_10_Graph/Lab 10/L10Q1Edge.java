public class L10Q1Edge<T extends Comparable<T>, N extends Comparable<N>> {
    L10Q1Vertex<T,N> toVertex;
    N weight;
    L10Q1Edge<T, N> nextEdge;

    public L10Q1Edge() {
        toVertex = null;
        weight = null;
        nextEdge = null;
    }

    public L10Q1Edge(L10Q1Vertex<T,N> destination, N w, L10Q1Edge<T,N> a){
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }
}
