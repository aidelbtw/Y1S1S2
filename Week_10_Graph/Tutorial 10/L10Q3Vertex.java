public class L10Q3Vertex<T extends Comparable<T>, N extends Comparable<N>> {
    T vertexInfo;
    int indeg;
    int outdeg;
    L10Q3Vertex<T, N> nextVertex;
    L10Q3Edge<T, N> firstEdge;

    public L10Q3Vertex(){
        vertexInfo = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
    }

    public L10Q3Vertex(T vInfo, L10Q3Vertex<T,N> next) {
        vertexInfo = vInfo;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }
}