package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class MSTResult {
    private List<Edge> edges;
    private int totalCost;
    private int operationsCount;
    private double executionTimeMs;

    public MSTResult(List<Edge> edges, int totalCost, int operationsCount, double executionTimeMs) {
        this.edges = edges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();

        // Add MST edges
        JSONArray edgesArray = new JSONArray();
        for (Edge edge : edges) {
            JSONObject edgeObject = new JSONObject();
            edgeObject.put("from", edge.getFrom());
            edgeObject.put("to", edge.getTo());
            edgeObject.put("weight", edge.getWeight());
            edgesArray.add(edgeObject);
        }
        result.put("mst_edges", edgesArray);

        // Add other metrics
        result.put("total_cost", totalCost);
        result.put("operations_count", operationsCount);
        result.put("execution_time_ms", executionTimeMs);

        return result;
    }

    // Getters for potential use
    public List<Edge> getEdges() {
        return edges;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getOperationsCount() {
        return operationsCount;
    }

    public double getExecutionTimeMs() {
        return executionTimeMs;
    }
}