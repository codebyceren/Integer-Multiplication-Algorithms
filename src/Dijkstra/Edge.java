package Dijkstra;

public class Edge {

    public int neighbor;
    public int weight;

    public Edge(int neighbor, int weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }

    public int getNeighbor() {
        return neighbor;
    }

    public int getWeight() {
        return weight;
    }
    
}
