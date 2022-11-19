import java.util.*;

public class WeightedGraph {
    static class Pair {
        String vertex;
        Integer edgeWeight;

        Pair(String vertex, Integer edgeWeight) {
            this.vertex = vertex;
            this.edgeWeight = edgeWeight;
        }
    }
    private final Map<String, List<Pair>> adjacentVertices;
    private final Set<String> visited = new HashSet<>();
    private final Map<String, Pair> shortestCostMap;

    public WeightedGraph() {
        this.adjacentVertices = new LinkedHashMap<>();
        this.shortestCostMap = new HashMap<>();
    }

    void addEdge(String firstVertex, String secondVertex, Integer edgeWeight) {
        adjacentVertices.get(firstVertex).add(new Pair(secondVertex, edgeWeight));
        adjacentVertices.get(secondVertex).add(new Pair(firstVertex, edgeWeight));
    }

    void addVertex(String vertex) {
        if (!adjacentVertices.containsKey(vertex)) {
            adjacentVertices.put(vertex, new ArrayList<>());
        }
    }

    void printAdjVertices() {
        for (String vertex : adjacentVertices.keySet()) {
            List<Pair> edgeWeightPairList = adjacentVertices.get(vertex);
            System.out.print(vertex + ": ");
            for (Pair edgeWeightPair : edgeWeightPairList) {
                System.out.print("(" + edgeWeightPair.vertex + ", " + edgeWeightPair.edgeWeight + ")" + " ");
            }

            System.out.println();
        }
    }

    private void setShortestPathTree(String startVertex) {
        for (String vertices : adjacentVertices.keySet()) {
            if (vertices.equals(startVertex)) {
                shortestCostMap.put(startVertex, new Pair(startVertex, 0));
                continue;
            }

            shortestCostMap.put(vertices, null);
        }
    }

    // Dijkstra's algorithm
    void dijkstraRunner(String startVertex) {

        // initalize shortest path tree from start vertex
        setShortestPathTree(startVertex);

        PriorityQueue<Pair> nodes = new PriorityQueue<>(Comparator.comparingInt(a -> a.edgeWeight));

        // first vertex is at distance 0 from itself
        nodes.add(new Pair(startVertex, 0));

        while (!nodes.isEmpty()) {
            Pair node = nodes.remove();
            List<Pair> weightedEdges = adjacentVertices.get(node.vertex);

            for (Pair weightedNeighbor : weightedEdges) {
                String vertex = weightedNeighbor.vertex;
                Integer edgeWeight = weightedNeighbor.edgeWeight;

                if (visited.contains(vertex)) continue;

                if (shortestCostMap.get(vertex) == null || shortestCostMap.get(vertex).edgeWeight > edgeWeight + node.edgeWeight) {
                    shortestCostMap.put(vertex, new Pair(node.vertex, edgeWeight + node.edgeWeight));
                    nodes.add(new Pair(vertex, edgeWeight + node.edgeWeight));
                }
            }

            visited.add(node.vertex);
        }

        for (String vertexKey : shortestCostMap.keySet()) {
            System.out.println("edge == " +"(" + shortestCostMap.get(vertexKey).vertex + ", " + vertexKey + ")" +  ", edgeWeight == " + shortestCostMap.get(vertexKey).edgeWeight);
        }
    }
}
