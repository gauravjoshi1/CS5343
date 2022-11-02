package com.company;

import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacentVertices;
    private Map<String, Integer> indegree;
    private final Map<String, Integer> ordering;
    private final Set<String> visitedNodes;
    private final Queue<String> indegreeQueue;
    public int currentLength = 0;

    Graph() {
        this.adjacentVertices = new HashMap<>();
        this.indegree = new HashMap<>();
        this.ordering = new HashMap<>();
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

    void printOrdering() {
        for (String vertex : ordering.keySet()) {
            if (ordering.get(vertex) == -1) {
                System.out.println("Cycle detected");
                return;
            }
        }

        System.out.println(ordering);
    }

    void dfsTopologicalSortRunner(String naturalStartingVertex) {
        int length = currentLength;

        // fill default ordering with -1
        for (String vertex : adjacentVertices.keySet()) {
            ordering.put(vertex, -1);
        }

        // run the first traversal from the natural starting vertex
        dfsTopologicalSort(naturalStartingVertex);

        // run dfs for each of the unexplored vertices
        for (String vertex : adjacentVertices.keySet()) {
            if (!visitedNodes.contains(vertex)) dfsTopologicalSort(vertex);
        }

        currentLength = length;
        printOrdering();
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

//    void dfsTopologicalSortRunner() {
//        Set<String> visitedNodes = new HashSet<>();
//        Map<String, Integer> refIndegree = new HashMap<>(indegree);
//        int length = currentLength;
//
//        // default ordering
//        for (String vertex : indegree.keySet()) {
//            ordering.put(vertex, -1);
//        }
//
//        for (String vertex : indegree.keySet()) {
//            if (indegree.get(vertex) == 0) {
//                indegreeStack.add(vertex);
//            }
//        }
//
//        while (!indegreeStack.isEmpty()) {
//            String vertex = indegreeStack.pop();
//            if (!visitedNodes.contains(vertex)) {
//                dfsTopologicalSort(visitedNodes, vertex);
//            }
//        }
//
//        currentLength = length;
//        indegree = refIndegree;
//
//        printOrdering();
//    }
//
//    void dfsTopologicalSort(Set<String> visitedNodes, String root) {
//        visitedNodes.add(root);
//
//        List<String> edgesFromNode = getAdjacentVertices(root);
//
//        for (String vertex: edgesFromNode) {
//
//            if (visitedNodes.contains(vertex) && ordering.get(vertex) == -1) {
//                return;
//            }
//
//            if (!visitedNodes.contains(vertex)) {
//                indegree.put(vertex, indegree.get(vertex) - 1);
//                if (indegree.get(vertex) == 0) {
//                    indegreeStack.add(vertex);
//                }
//
//                dfsTopologicalSort(visitedNodes, vertex);
//            }
//        }
//
//        ordering.put(root, currentLength--);
//
//    }

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
                System.out.println("Cycle Detected");
                return;
            }
        }

        printOrdering();

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
