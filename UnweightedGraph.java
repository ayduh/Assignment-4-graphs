import java.util.HashMap;
import java.util.Map;

public class UnweightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest) {
        if (!vertices.containsKey(source)) {
            addVertex(source);
        }
        if (!vertices.containsKey(dest)) {
            addVertex(dest);
        }

        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(dest);

        // weight 1.0 for unweighted
        sourceVertex.addAdjacentVertex(destVertex, 1.0);

        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, 1.0);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}
