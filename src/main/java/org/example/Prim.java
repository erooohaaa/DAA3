package org.example;

import java.util.*;

public class Prim {

    public static MSTResult run(Graph graph) {
        long startTime = System.nanoTime();
        int operations = 0;

        // Build adjacency list
        Map<String, List<Edge>> adjacencyList = new HashMap<>();
        for (String node : graph.getNodes()) {
            adjacencyList.put(node, new ArrayList<>());
        }

        for (Edge edge : graph.getEdges()) {
            adjacencyList.get(edge.getFrom()).add(new Edge(edge.getFrom(), edge.getTo(), edge.getWeight()));
            adjacencyList.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        List<Edge> mstEdges = new ArrayList<>();

        // Start from first node
        String startNode = graph.getNodes().get(0);
        visited.add(startNode);
        priorityQueue.addAll(adjacencyList.get(startNode));

        while (!priorityQueue.isEmpty() && mstEdges.size() < graph.getVertexCount() - 1) {
            Edge currentEdge = priorityQueue.poll();
            operations++;

            if (visited.contains(currentEdge.getTo())) {
                continue;
            }

            visited.add(currentEdge.getTo());
            mstEdges.add(currentEdge);

            for (Edge neighborEdge : adjacencyList.get(currentEdge.getTo())) {
                if (!visited.contains(neighborEdge.getTo())) {
                    priorityQueue.add(neighborEdge);
                    operations++;
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTime = Util.msDuration(startTime, endTime);

        int totalCost = mstEdges.stream()
                .mapToInt(Edge::getWeight)
                .sum();

        return new MSTResult(mstEdges, totalCost, operations, executionTime);
    }
}