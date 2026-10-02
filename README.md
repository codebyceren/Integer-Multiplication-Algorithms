# Algorithms in Java

Java implementations of the algorithms from Stanford Online's Algorithms Specialization (via Coursera). Each algorithm is a separate file in `src/` and reads its input from `data/`.

## Algorithms

**Divide and Conquer**
Karatsuba Multiplication, Counting Inversions, QuickSort, Fixed Point, Local Minimum, Second Largest, Unimodal Array

**Graph Algorithms and Data Structures**
Dijkstra, All-Pairs Shortest Path, Strongly Connected Components, Karger's Min Cut, Median Maintenance, Two Sum Target Range

**Greedy Algorithms**
Huffman Coding, Prim's MST, Clustering, Large-Scale Clustering, Weighted Completion Times

**Dynamic Programming**
Knapsack, Large-Scale Knapsack, Maximum-Weight Independent Set

**NP-Complete Problems**
TSP, Nearest Neighbor TSP, 2-SAT

## How to Run

From the project root:

```bash
javac -d out src/*.java src/Dijkstra/*.java
java -cp out TwoSAT
```

Replace `TwoSAT` with any other class name. Run the commands from the project root so the `data/` paths resolve.

## Author

Ceren Günhan