# Algorithms in Java

Java implementations of the algorithms from Stanford Online's Algorithms Specialization (via Coursera). Each algorithm is a separate file in `src/` and reads its input from `data/`.

## Algorithms

**Divide and Conquer**
Karatsuba Multiplication, Counting Inversions, QuickSort, Fixed Point, Local Minimum, Second Largest, Unimodal Array.
Problems are split into smaller subproblems, solved recursively and combined.

**Graph Algorithms and Data Structures**
Dijkstra, All-Pairs Shortest Path, Strongly Connected Components, Karger's Min Cut, Median Maintenance, Two Sum Target Range.
Shortest paths, graph connectivity and the data structures behind them.

**Greedy Algorithms**
Huffman Coding, Prim's MST, Clustering, Large-Scale Clustering, Weighted Completion Times.
Locally optimal choices that lead to a global solution.

**Dynamic Programming**
Knapsack, Large-Scale Knapsack, Maximum-Weight Independent Set.
Overlapping subproblems solved once and reused.

**NP-Complete Problems**
TSP, Nearest Neighbor TSP, 2-SAT.
An exact solution, a heuristic and a polynomial-time special case (2-SAT).

## Complexity

| Algorithm | Time |
|---|---|
| Karatsuba Multiplication | O(n^log₂3) |
| Dijkstra | O((V + E) log V) |
| Prim's MST | O(E log V) |
| Strongly Connected Components | O(V + E) |
| Huffman Coding | O(n log n) |
| Knapsack | O(nW) |
| 2-SAT | O(V + E) |

Standard complexities of the algorithms. Actual running time depends on the implementation.

## How to Run

From the project root:

```bash
javac -d out src/*.java src/Dijkstra/*.java
java -cp out TwoSAT
```

Replace `TwoSAT` with any other class name. Run the commands from the project root so the `data/` paths resolve.

`TwoSAT` prints SATISFIABLE or UNSATISFIABLE for each of the six datasets, followed by a six-digit answer string.

## Author

Ceren Günhan