package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Map<String, List<String>> adjacentVertices;
    private final Map<String, Integer> indegree;
    private final Map<String, Integer> ordering;
    private final Set<String> visitedNodes;
    private final Queue<String> indegreeQueue;
    private int currentLength = 0;

    Graph() {
        this.adjacentVertices = new HashMap<>();
        this.indegree = new HashMap<>();
        this.ordering = new LinkedHashMap<>();
        this.visitedNodes = new HashSet<>();
        this.indegreeQueue = new LinkedList<>();
    }

    void setLength(int currentLength) {
        this.currentLength = currentLength;
    }

    void addEdge(String firstVertex, String secondVertex) {
        adjacentVertices.get(firstVertex).add(secondVertex);
    }

    void addVertex(String vertex) {
        if (!adjacentVertices.containsKey(vertex)) {
            adjacentVertices.put(vertex, new ArrayList<>());
        }
    }

    void calculateIndegree() {
        for (String vertex : adjacentVertices.keySet()) {
            if (!indegree.containsKey(vertex)) indegree.put(vertex, 0);

            List<String> adjVertices = adjacentVertices.get(vertex);

            for (String adjVertex : adjVertices) {
                indegree.put(adjVertex, indegree.getOrDefault(adjVertex, 0) + 1);
            }
        }
    }

    void printIndegree() {
        System.out.println(indegree);
    }

    List<String> getAdjacentVertices(String vertex) {
        return adjacentVertices.get(vertex);
    }

    void printAdjacencyList() {
        System.out.println(adjacentVertices);
    }

    void printTopologicalOrdering() {
        System.out.println(ordering.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }

    void dfsTopologicalSortRunner() {
        int length = currentLength;

        // fill default ordering with -1
        for (String vertex : adjacentVertices.keySet()) {
            ordering.put(vertex, -1);
        }

        // run dfs for each of the unexplored vertices
        for (String vertex : adjacentVertices.keySet()) {
            if (!visitedNodes.contains(vertex)) dfsTopologicalSort(vertex);
        }

        currentLength = length;

        // detect if there is any cycle
        for (String vertex : ordering.keySet()) {
            if (ordering.get(vertex) == -1) {
                System.out.println("Cycle detected in DFS Topological Sort");
                return;
            }
        }

        // print results
        System.out.print("DFS Topological Ordering with vertices: ");
        System.out.println(ordering);
        System.out.print("DFS Topological Ordering: ");
        printTopologicalOrdering();
    }

    void dfsTopologicalSort(String root) {
        visitedNodes.add(root);

        for (String vertex : getAdjacentVertices(root)) {
            // cycle detected
            if (visitedNodes.contains(vertex) && ordering.get(vertex) == -1) {
                return;
            }

            if (!visitedNodes.contains(vertex)) {
                dfsTopologicalSort(vertex);
            }
        }

        ordering.put(root, currentLength--);
    }

    public void bfsTopologicalSortRunner() {
        int bfsOrdering = 1;

        // default ordering
        for (String vertex : indegree.keySet()) {
            if (indegree.get(vertex) == 0) {
                indegreeQueue.add(vertex);
            }
        }

        while (!indegreeQueue.isEmpty()) {
            String vertex = indegreeQueue.remove();
            bfsTopologicalSort(vertex, bfsOrdering++);
        }

        for (String vertex : indegree.keySet()) {
            if (indegree.get(vertex) > 0) {
                System.out.println("Cycle Detected in BFS Topological Sort");
                return;
            }
        }

        System.out.print("BFS Topological Ordering with vertices: ");
        System.out.println(ordering);
        System.out.print("BFS Topological Ordering: ");

        printTopologicalOrdering();
    }

    void bfsTopologicalSort(String root, int bfsOrdering) {

        ordering.put(root, bfsOrdering);

        List<String> verticesFromRoot = getAdjacentVertices(root);

        for (String vertex : verticesFromRoot) {
            indegree.put(vertex, indegree.get(vertex) - 1);

            if (indegree.get(vertex) == 0) {
                indegreeQueue.add(vertex);
            }
        }
    }
}
