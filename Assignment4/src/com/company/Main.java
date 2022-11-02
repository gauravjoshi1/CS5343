package com.company;

public class Main {
    private void buildGraph(Graph graph, String[] vertices, String[][] edges) {
        // add vertex
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }

        // add edges
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        graph.calculateIndegree();
        graph.setLength(vertices.length);
    }

    public static void main(String[] args) {
        Graph g1 = new Graph();
        Graph g2 = new Graph();

        Main main = new Main();

        // example 1:
        String[] v1 = new String[]{"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[][] e1 = new String[][]{{"m", "q"},{"m", "r"},{"m", "x"},{"n", "o"},{"n", "q"},{"n", "u"},{"o", "r"},{"o", "s"},{"o", "v"},
                {"p", "o"},{"p", "s"},{"p", "z"},{"q", "t"},{"r", "u"},{"r", "y"},{"s", "r"},{"v", "x"},{"v", "w"},{"w", "z"},{"y", "v"}};

        // build the graph g1
        main.buildGraph(g1, v1, e1);

        /* Test the graph g1 with the following functions
         * g1.printAdjacencyList(); // print the adj list for g1
         * g1.printIndegree(); // print number of edges coming into each vertex
         */

        // run sorting algorithms
        System.out.print("DFS Topological Sort: ");
        g1.dfsTopologicalSortRunner();
        System.out.print("BFS Topological Sort: ");
        g1.bfsTopologicalSortRunner();

        System.out.println("\n");

        // example 2:
        String[] v2 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        String[][] e2 = new String[][]{{"1", "2"}, {"1", "5"}, {"1", "6"}, {"2", "3"}, {"2", "5"}, {"2", "7"}, {"3", "4"}, {"4", "5"}, {"5","7"}, {"5","8"}, {"6","5"}, {"6","8"}, {"7", "4"}};

        main.buildGraph(g2, v2, e2);

        System.out.print("DFS Topological Sort: ");
        g2.dfsTopologicalSortRunner();
        System.out.print("BFS Topological Sort: ");
        g2.bfsTopologicalSortRunner();
    }
}
