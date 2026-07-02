public class L10Q1Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    T vertexInfo;
    int indeg;
    int outdeg;
    L10Q1Vertex<T, N> nextVertex;
    L10Q1Edge<T, N> firstEdge;

    public L10Q1Vertex(){
        vertexInfo = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
    }

    public L10Q1Vertex(T vInfo, L10Q1Vertex<T,N> next) {
        vertexInfo = vInfo;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }
}
