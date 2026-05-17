import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Search<V> {
    protected Map<V, V> edgeTo;   // key = current node, value = where we came from
    protected V start;

    public Search(V start) {
        this.start = start;
        this.edgeTo = new HashMap<>();
    }

    public List<V> pathTo(V dest) {
        List<V> path = new LinkedList<>();

        // if we never reached dest, return empty
        if (!edgeTo.containsKey(dest) && !dest.equals(start)) {
            return path;
        }

        // trace back from dest to start
        V current = dest;
        while (!current.equals(start)) {
            path.add(0, current);
            current = edgeTo.get(current);
        }
        path.add(0, start);

        return path;
    }
}
