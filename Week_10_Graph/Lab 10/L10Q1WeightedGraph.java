import java.util.ArrayList;
import java.util.List;

public class L10Q1WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {
    L10Q1Vertex<T,N> head;
    int size;

    public L10Q1WeightedGraph(){
        head = null;
        size = 0;
    }

    public boolean addVertex(T vInfo){
        if (hasVertex(vInfo)){
            return false;
        }
        head = new L10Q1Vertex<>(vInfo, head);
        size++;
        return true;
    }

    private L10Q1Vertex<T,N> findVertex(T vInfo){
        L10Q1Vertex<T,N> current = head;
        while (current!= null) {
            if (current.vertexInfo.compareTo(vInfo) == 0){
                return current;
            }
            current = current.nextVertex;            
        }
        return null;
    }

    public boolean hasVertex(T vInfo){
        return findVertex(vInfo) != null;
    }

    public int getSize(){
        return size;
    }

    public boolean addEdge(T start, T end, N weight){
        L10Q1Vertex<T,N> startVertex = findVertex(start);
        L10Q1Vertex<T,N> endVertex = findVertex(end);

        if (startVertex == null || endVertex == null){
            return false;
        }

        L10Q1Edge<T,N> currentEdge = startVertex.firstEdge;
        while (currentEdge != null) {
            if (currentEdge.toVertex.vertexInfo.compareTo(end) == 0){
                return false;
            }
            currentEdge = currentEdge.nextEdge;
        }

        startVertex.firstEdge = new L10Q1Edge<>(startVertex, weight, endVertex.firstEdge);
        startVertex.outdeg++;
        endVertex.indeg++;
        return true;
    }

    public boolean addUndirectedEdge(T start, T end, N weight){
        boolean result1 = addEdge(start, end, weight);
        boolean result2 = addEdge(end, start, weight);
        return result1 && result2;
    }

    public boolean removeEdge(T start, T end){
        L10Q1Vertex<T,N> startVertex = findVertex(start);
        L10Q1Vertex<T,N> endVertex = findVertex(end);
       
        if (startVertex == null || endVertex == null){
            return false;
        }

        L10Q1Edge<T,N> current = startVertex.firstEdge;
        L10Q1Edge<T,N> previous = null;

        while (current != null) {
            if (current.toVertex.vertexInfo.compareTo(end) == 0){
                if (previous == null){
                    startVertex.firstEdge = current.nextEdge;
                } else {
                    previous.nextEdge = current.nextEdge;
                }
                startVertex.outdeg--;
                endVertex.indeg--;
            }
            previous = current;
            current = current.nextEdge;
        }
        return false;
    }

    public boolean hasEdge(T start, T end){
        L10Q1Vertex startVertex = findVertex(start);
        if (startVertex == null){return false;}

        L10Q1Edge<T,N> current = startVertex.firstEdge;
        while (current != null) {
            if (current.toVertex.vertexInfo.compareTo(end) == 0){
                return true;
            }
            current = current.nextEdge;
        }
        return false;
    }

    public N getEdgeWeight(T start, T end){
        L10Q1Vertex<T,N> startVertex = findVertex(start);
        if (startVertex == null){return null;}

        L10Q1Edge<T,N> current = startVertex.firstEdge;
        while (current != null) {
            if (current.toVertex.vertexInfo.compareTo(end) == 0){
                return current.weight;
            }
            current = current.nextEdge;
        }
        return null;        
    }

    public int getIndeg(T vInfo){
        L10Q1Vertex<T,N> vertex = findVertex(vInfo);
        return (vertex != null) ? vertex.indeg : -1;
    }
    
    public int getOutdeg(T vInfo){
    L10Q1Vertex<T,N> vertex = findVertex(vInfo);
    return (vertex != null) ? vertex.outdeg : -1;
    }

    public void printEdges(){
        L10Q1Vertex<T,N> currentVertex = head;
        while (currentVertex != null) {
            System.out.println(currentVertex.vertexInfo + ": ");
            L10Q1Edge<T,N> currentEdge = currentVertex.firstEdge;
            while (currentEdge!= null) {
                System.out.println("(" + currentVertex.vertexInfo + " -> " +
                                    currentEdge.toVertex.vertexInfo +
                                    ", weight: " + currentEdge.weight +")"
                                );
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            currentVertex = currentVertex.nextVertex;
        }
    }

    public ArrayList<L10Q1Vertex<T,N>> getAllVertexObjects(){
        ArrayList<L10Q1Vertex<T,N>> vertices = new ArrayList<>();
        L10Q1Vertex<T,N> current = head;
        while (current != null) {
            vertices.add(current);
            current = current.nextVertex;
        }
        return vertices;
    }

    public ArrayList<T> getNeighbours(T start){
        ArrayList<T> neighbours = new ArrayList<>();
        L10Q1Vertex<T,N> startVertex = findVertex(start);
        if (startVertex == null){
            return neighbours;
        }

        L10Q1Edge<T,N> current = startVertex.firstEdge;
        while (current != null) {
            neighbours.add(current.toVertex.vertexInfo);
            current = current.nextEdge;
        }
        return neighbours;
    }
}
