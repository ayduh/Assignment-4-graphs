import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<V> extends Search<V> {
    private UnweightedGraph<V> graph;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        super(start);
        this.graph = graph;
        bfs(start);
    }

    private void bfs(V start) {
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Vertex<V> currentVertex = graph.getVertex(current);

            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                V neighbor = entry.getKey().getData();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }
}
