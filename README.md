# Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)

## Objective
The purpose of this assignment is to apply Prim's and Kruskal's algorithms to optimise a city's transportation network by determining the minimum set of roads that connect all city districts with the lowest possible total construction cost. Students will also analyse the efficiency of both algorithms and compare their performance.

## Task Description
The city administration plans to construct roads connecting all districts in such a way that:
- Each district is reachable from any other district
- The total cost of construction is minimised

This scenario is modelled as a weighted undirected graph, where:
- Vertices represent city districts
- Edges represent potential roads  
- The edge weight represents the cost of constructing the road

## Assignment Requirements

### 1. Input Data Processing
Read the input data describing the transportation network from a JSON file according to a provided template.

### 2. Algorithm Implementation
Implement both:
- Prim's algorithm
- Kruskal's algorithm
to find the Minimum Spanning Tree (MST).

### 3. Metrics Collection
For each algorithm, determine and record:
- The list of edges forming the MST
- The total cost of the MST
- The number of vertices and edges in the original graph
- The number of operations performed (key algorithmic actions such as comparisons, unions, etc.)
- The execution time in milliseconds

### 4. Results Comparison
Compare the results of both algorithms. The MST total cost must be identical, though the specific structure of the tree may differ.

## Project Structure
assignment3-mst/
├── src/
│ └── main/
│ ├── java/org/example/
│ │ ├── Edge.java
│ │ ├── Graph.java
│ │ ├── Kruskal.java
│ │ ├── Main.java
│ │ ├── MSTResult.java
│ │ ├── Prim.java
│ │ └── Util.java
│ └── resources/
│ ├── ass_3_input.json
│ └── ass_3_output.json
├── target/
├── test/
├── pom.xml
└── README.md


## Quick Start
```bash
# Compile and run the project
mvn compile exec:java

# Or run directly
mvn exec:java -Dexec.mainClass="org.example.Main"



Input Format (ass_3_input.json)
json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    },
    {
      "id": 2,
      "nodes": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 1},
        {"from": "A", "to": "C", "weight": 4},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "C", "to": "D", "weight": 3},
        {"from": "B", "to": "D", "weight": 5}
      ]
    }
  ]
}
Output Format (ass_3_output.json)
json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 5,
        "edges": 7
      },
      "prim": {
        "mst_edges": [
          {"weight": 3, "from": "A", "to": "C"},
          {"weight": 2, "from": "C", "to": "B"},
          {"weight": 5, "from": "B", "to": "D"},
          {"weight": 6, "from": "D", "to": "E"}
        ],
        "total_cost": 16,
        "execution_time_ms": 0.35,
        "operations_count": 10
      },
      "kruskal": {
        "mst_edges": [
          {"weight": 2, "from": "B", "to": "C"},
          {"weight": 3, "from": "A", "to": "C"},
          {"weight": 5, "from": "B", "to": "D"},
          {"weight": 6, "from": "D", "to": "E"}
        ],
        "total_cost": 16,
        "execution_time_ms": 0.66,
        "operations_count": 27
      }
    },
    {
      "graph_id": 2,
      "input_stats": {
        "vertices": 4,
        "edges": 5
      },
      "prim": {
        "mst_edges": [
          {"weight": 1, "from": "A", "to": "B"},
          {"weight": 2, "from": "B", "to": "C"},
          {"weight": 3, "from": "C", "to": "D"}
        ],
        "total_cost": 6,
        "execution_time_ms": 0.03,
        "operations_count": 6
      },
      "kruskal": {
        "mst_edges": [
          {"weight": 1, "from": "A", "to": "B"},
          {"weight": 2, "from": "B", "to": "C"},
          {"weight": 3, "from": "C", "to": "D"}
        ],
        "total_cost": 6,
        "execution_time_ms": 0.02,
        "operations_count": 18
      }
    }
  ]
}
