import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearch<V> extends Search<V> {
    private UnweightedGraph<V> graph;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        super(start);
        this.graph = graph;
        dfs(start, new HashSet<>());
    }

    private void dfs(V current, Set<V> visited) {
        visited.add(current);
        Vertex<V> currentVertex = graph.getVertex(current);

        for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
            V neighbor = entry.getKey().getData();

            if (!visited.contains(neighbor)) {
                edgeTo.put(neighbor, current);
                dfs(neighbor, visited);
            }
        }
    }
}
