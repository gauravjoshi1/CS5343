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
    private final Map<String, Integer> costMap;

    public WeightedGraph() {
        this.adjacentVertices = new LinkedHashMap<>();
        costMap = new HashMap<>();
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

            for (Pair edgeWeightPair : edgeWeightPairList) {
                System.out.println(vertex + " " + edgeWeightPair.vertex + " " +edgeWeightPair.edgeWeight);
            }
        }
    }

    // Dijkstra's algorithm
    void dijkstraRunner(String startVertex) {
        for (String vertices : adjacentVertices.keySet()) {
            if (vertices.equals(startVertex)) {
                costMap.put(startVertex, 0);
                continue;
            }

            costMap.put(vertices, null);
        }

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

                if (costMap.get(vertex) == null || costMap.get(vertex) > edgeWeight + node.edgeWeight) {
                    costMap.put(vertex, edgeWeight + node.edgeWeight);
                    nodes.add(new Pair(vertex, edgeWeight + node.edgeWeight));
                }
            }

            visited.add(node.vertex);
        }

        System.out.println(costMap);
    }
}
