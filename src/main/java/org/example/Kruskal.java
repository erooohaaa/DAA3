package org.example;

import java.util.*;

public class Kruskal {

    static class UnionFind {
        private Map<String, String> parent;
        private Map<String, Integer> rank;
        private int operations;

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
            operations = 0;
        }

        public void makeSet(List<String> vertices) {
            for (String vertex : vertices) {
                parent.put(vertex, vertex);
                rank.put(vertex, 0);
                operations++;
            }
        }

        public String find(String vertex) {
            operations++;
            if (!parent.get(vertex).equals(vertex)) {
                parent.put(vertex, find(parent.get(vertex)));
            }
            return parent.get(vertex);
        }

        public boolean union(String vertex1, String vertex2) {
            operations++;
            String root1 = find(vertex1);
            String root2 = find(vertex2);

            if (root1.equals(root2)) {
                return false;
            }

            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
            operations++;
            return true;
        }

        public int getOperations() {
            return operations;
        }
    }

    public static MSTResult run(Graph graph) {
        long startTime = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);

        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.getNodes());

        for (Edge edge : sortedEdges) {
            if (mstEdges.size() == graph.getVertexCount() - 1) {
                break;
            }

            if (unionFind.union(edge.getFrom(), edge.getTo())) {
                mstEdges.add(edge);
            }
        }

        long endTime = System.nanoTime();
        double executionTime = Util.msDuration(startTime, endTime);

        int totalCost = mstEdges.stream()
                .mapToInt(Edge::getWeight)
                .sum();

        return new MSTResult(mstEdges, totalCost, unionFind.getOperations(), executionTime);
    }
}