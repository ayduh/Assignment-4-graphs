import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<V> extends Search<V> {
    private Map<V, Double> distTo;
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        super(start);
        this.graph = graph;
        this.distTo = new HashMap<>();

        // set all distances to infinity at the start
        for (V vertex : graph.getVertices().keySet()) {
            distTo.put(vertex, Double.MAX_VALUE);
        }
        distTo.put(start, 0.0);

        dijkstra(start);
    }

    private void dijkstra(V start) {
        Set<V> visited = new HashSet<>();

        // just loop until all vertices are settled
        for (int i = 0; i < graph.getVertices().size(); i++) {
            // pick the unvisited vertex with smallest distance
            V current = getMinVertex(visited);
            if (current == null) break;

            visited.add(current);

            Vertex<V> currentVertex = graph.getVertex(current);

            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                V neighbor = entry.getKey().getData();
                double weight = entry.getValue();

                double newDist = distTo.get(current) + weight;

                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                }
            }
        }
    }

    // finds the unvisited vertex with the smallest known distance
    private V getMinVertex(Set<V> visited) {
        V minVertex = null;
        double minDist = Double.MAX_VALUE;

        for (Map.Entry<V, Double> entry : distTo.entrySet()) {
            if (!visited.contains(entry.getKey()) && entry.getValue() < minDist) {
                minDist = entry.getValue();
                minVertex = entry.getKey();
            }
        }
        return minVertex;
    }
}
