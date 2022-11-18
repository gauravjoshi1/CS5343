import java.util.*;

public class Main {
    private void buildGraph(WeightedGraph graph, String[] vertices, String[][] weightedEdges) {
        // add vertex
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }

        // add edges
        for (String[] weightedEdge : weightedEdges) {
            String firstVertex = weightedEdge[0], secondVertex = weightedEdge[1];
            Integer edgeWeight = Integer.parseInt(weightedEdge[2]);

            graph.addEdge(firstVertex, secondVertex, edgeWeight);
        }
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph();
        Main main = new Main();

        String[] v1 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8"};
        String[][] weightedEdges = new String[][]{
                {"0", "1", "4"},
                {"0", "6", "7"},
                {"1", "6", "11"},
                {"1", "7", "20"},
                {"1", "2", "9"},
                {"2", "3", "6"},
                {"2", "4", "2"},
                {"3", "4", "10"},
                {"3", "5", "5"},
                {"4", "5", "15"},
                {"4", "7", "1"},
                {"4", "8", "5"},
                {"5", "8", "12"},
                {"6", "7", "1"},
                {"7", "8", "3"}
        };

        main.buildGraph(wg, v1, weightedEdges);
        wg.dijkstraRunner("0");
    }
}