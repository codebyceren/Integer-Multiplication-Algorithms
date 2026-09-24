# Algorithms and Data Structures

A collection of algorithm implementations developed in Java while studying fundamental algorithms, data structures, graph algorithms, greedy algorithms, dynamic programming, and optimization problems.

This repository focuses on implementing algorithms from scratch, working with different datasets, and developing a practical understanding of algorithm design and computational complexity.

---

## Algorithms

### Divide and Conquer

* **Karatsuba Multiplication**
* **QuickSort**
* **Counting Inversions**
* **Second Largest Element**
* **Unimodal Array**
* **Fixed Point**
* **Local Minimum**

### Randomized Algorithms

* **Karger's Randomized Min Cut**

### Graph Algorithms

* **Dijkstra's Shortest Path**
* **Kosaraju's Strongly Connected Components**
* **Prim's Minimum Spanning Tree**
* **All-Pairs Shortest Path**
* **Clustering**
* **Large-Scale Clustering**
* **2-SAT using Strongly Connected Components**

### Greedy Algorithms

* **Median Maintenance**
* **Weighted Completion Times**
* **Huffman Coding**
* **Nearest Neighbor TSP**

### Dynamic Programming

* **0/1 Knapsack**
* **Large-Scale Knapsack**
* **Maximum-Weight Independent Set (MWIS)**

### Travelling Salesman Problem

* **TSP**
* **Nearest Neighbor TSP**

### Other Problems

* **Two Sum Target Range**

---

## Project Structure

```text
Integer-Multiplication-Algorithms/
│
├── data/
│   │
│   ├── allPairShortestPath/
│   │   ├── g1.txt
│   │   ├── g2.txt
│   │   └── g3.txt
│   │
│   ├── twoSAT/
│   │   ├── 2sat1.txt
│   │   ├── 2sat2.txt
│   │   ├── 2sat3.txt
│   │   ├── 2sat4.txt
│   │   ├── 2sat5.txt
│   │   └── 2sat6.txt
│   │
│   ├── IntegerArray.txt
│   ├── QuickSort.txt
│   ├── Median.txt
│   ├── SCC.txt
│   ├── algo1-programming_prob-2sum.txt
│   ├── clustering1.txt
│   ├── clustering_big.txt
│   ├── dijkstraData.txt
│   ├── edges.txt
│   ├── huffman.txt
│   ├── jobs.txt
│   ├── kargerMinCut.txt
│   ├── knapsack1.txt
│   ├── knapsack_big.txt
│   ├── mwis.txt
│   ├── nn.txt
│   └── tsp.txt
│
├── src/
│   │
│   ├── Dijkstra/
│   │   ├── Dijkstra.java
│   │   └── Edge.java
│   │
│   ├── AllPairsShortestPath.java
│   ├── Clustering1.java
│   ├── ClusteringBig.java
│   ├── CountInversions.java
│   ├── FixedPoint.java
│   ├── Huffman.java
│   ├── KaratsubaMultiplication.java
│   ├── KargerMinCut.java
│   ├── KnapSack1.java
│   ├── KnapSackBig.java
│   ├── LocalMinimum.java
│   ├── MedianMaintenance.java
│   ├── MWIS.java
│   ├── NearestNeighborTSP.java
│   ├── PrimMST.java
│   ├── QuickSort.java
│   ├── SCC.java
│   ├── SecondLargest.java
│   ├── TSP.java
│   ├── TwoSAT.java
│   ├── TwoSumTargetRange.java
│   ├── UnimodalArray.java
│   └── WeightedCompletionTimes.java
│
└── README.md
```

---

## Input Datasets

The `data/` directory contains the datasets used to test the implementations.

| Dataset                           | Used For                       |
| --------------------------------- | ------------------------------ |
| `IntegerArray.txt`                | Counting Inversions            |
| `QuickSort.txt`                   | QuickSort                      |
| `Median.txt`                      | Median Maintenance             |
| `SCC.txt`                         | Strongly Connected Components  |
| `dijkstraData.txt`                | Dijkstra's Shortest Path       |
| `edges.txt`                       | Minimum Spanning Tree          |
| `jobs.txt`                        | Weighted Completion Times      |
| `kargerMinCut.txt`                | Karger's Min Cut               |
| `clustering1.txt`                 | Clustering                     |
| `clustering_big.txt`              | Large-Scale Clustering         |
| `knapsack1.txt`                   | 0/1 Knapsack                   |
| `knapsack_big.txt`                | Large-Scale Knapsack           |
| `mwis.txt`                        | Maximum-Weight Independent Set |
| `huffman.txt`                     | Huffman Coding                 |
| `tsp.txt`                         | Travelling Salesman Problem    |
| `nn.txt`                          | Nearest Neighbor TSP           |
| `algo1-programming_prob-2sum.txt` | Two Sum Target Range           |

### All-Pairs Shortest Path

The `data/allPairShortestPath/` directory contains three graph datasets:

* `g1.txt`
* `g2.txt`
* `g3.txt`

These are used by the **All-Pairs Shortest Path** implementation.

### 2-SAT

The `data/twoSAT/` directory contains six test datasets:

* `2sat1.txt`
* `2sat2.txt`
* `2sat3.txt`
* `2sat4.txt`
* `2sat5.txt`
* `2sat6.txt`

These datasets are used by the **2-SAT** implementation.

---

## Complexity Overview

| Algorithm                | Main Technique         | Time Complexity    |
| ------------------------ | ---------------------- | ------------------ |
| Karatsuba Multiplication | Divide and Conquer     | O(n^log₂3)         |
| QuickSort                | Divide and Conquer     | O(n log n) average |
| Counting Inversions      | Divide and Conquer     | O(n log n)         |
| Karger's Min Cut         | Randomized             | O(V²) per trial    |
| Dijkstra                 | Greedy                 | O((V + E) log V)   |
| Prim's MST               | Greedy                 | O(E log V)         |
| Kosaraju's SCC           | Graph Traversal        | O(V + E)           |
| All-Pairs Shortest Path  | Dynamic Programming    | O(V³)              |
| Knapsack                 | Dynamic Programming    | O(nW)              |
| MWIS                     | Dynamic Programming    | O(n)               |
| Huffman Coding           | Greedy                 | O(n log n)         |
| 2-SAT                    | SCC                    | O(V + E)           |
| Nearest Neighbor TSP     | Greedy / Approximation | O(n²)              |

*Complexities represent the standard complexity of the corresponding algorithms and may vary depending on the implementation and data structures used.*

---

## Technologies

* **Java**
* **Java Collections Framework**
* **Object-Oriented Programming**
* **Data Structures**
* **Graph Algorithms**
* **Algorithm Analysis**
* **Git**
* **GitHub**

---

## What I Practiced

Through this repository, I practiced:

* Implementing algorithms from scratch in Java
* Choosing appropriate data structures
* Analyzing time and space complexity
* Working with large input datasets
* Solving graph and optimization problems
* Applying divide-and-conquer techniques
* Applying greedy algorithms
* Applying dynamic programming
* Working with randomized algorithms
* Implementing shortest-path algorithms
* Finding strongly connected components
* Solving clustering problems
* Working with the Travelling Salesman Problem
* Implementing 2-SAT using strongly connected components

---

## Repository Status

The main algorithm implementations covered in this repository are complete.

The repository will continue to be used for algorithm review, experimentation, and technical interview preparation.

---

## Author

**Ceren Günhan**

Built by [codebyceren](https://github.com/codebyceren).
