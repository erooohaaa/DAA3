package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Parse input JSON
            JSONParser parser = new JSONParser();
            JSONObject inputJson = (JSONObject) parser.parse(new FileReader("src/main/resources/ass_3_input.json"));
            JSONArray graphsJson = (JSONArray) inputJson.get("graphs");

            JSONArray resultsArray = new JSONArray();

            // Process each graph
            for (Object graphObj : graphsJson) {
                JSONObject graphJson = (JSONObject) graphObj;

                int graphId = ((Long) graphJson.get("id")).intValue();

                // Parse nodes
                List<String> nodes = new ArrayList<>();
                JSONArray nodesArray = (JSONArray) graphJson.get("nodes");
                for (Object nodeObj : nodesArray) {
                    nodes.add((String) nodeObj);
                }

                // Parse edges
                List<Edge> edges = new ArrayList<>();
                JSONArray edgesArray = (JSONArray) graphJson.get("edges");
                for (Object edgeObj : edgesArray) {
                    JSONObject edgeJson = (JSONObject) edgeObj;
                    String from = (String) edgeJson.get("from");
                    String to = (String) edgeJson.get("to");
                    int weight = ((Long) edgeJson.get("weight")).intValue();
                    edges.add(new Edge(from, to, weight));
                }

                // Create graph
                Graph graph = new Graph(graphId, nodes, edges);

                // Run algorithms
                MSTResult primResult = Prim.run(graph);
                MSTResult kruskalResult = Kruskal.run(graph);

                // Build result JSON
                JSONObject graphResult = new JSONObject();
                graphResult.put("graph_id", graphId);

                JSONObject inputStats = new JSONObject();
                inputStats.put("vertices", graph.getVertexCount());
                inputStats.put("edges", graph.getEdgeCount());
                graphResult.put("input_stats", inputStats);

                graphResult.put("prim", primResult.toJSON());
                graphResult.put("kruskal", kruskalResult.toJSON());

                resultsArray.add(graphResult);
            }

            // Create output JSON
            JSONObject outputJson = new JSONObject();
            outputJson.put("results", resultsArray);

            // Write pretty JSON to file
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(outputJson.toJSONString(), Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

            try (FileWriter fileWriter = new FileWriter("src/main/resources/ass_3_output.json")) {
                fileWriter.write(prettyJson);
            }

            System.out.println("Results successfully written to ass_3_output.json");

        } catch (Exception e) {
            System.err.println("Error processing graphs: " + e.getMessage());
            e.printStackTrace();
        }
    }
}