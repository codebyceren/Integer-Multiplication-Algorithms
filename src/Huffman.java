import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Huffman {

    private static class Node implements Comparable<Node> {
        long weight;
        Node left;
        Node right;

        public Node(long weight) {
            this.weight = weight;
            this.left = null;
            this.right = null;
        }

        public Node(long weight, Node left, Node right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.weight, other.weight);
        }
    }

    private static int maxDepth(Node node, int depth) {

        
        if(node.left == null && node.right == null) {
            return depth;
        } else {
            int leftDepth = maxDepth(node.left, depth + 1);
            int rightDepth = maxDepth(node.right, depth + 1);
            return Math.max(leftDepth, rightDepth);
        }
    }

    private static int minDepth(Node node, int depth) {
    
        if(node.left == null && node.right == null) {
            return depth;
        } else {
            int leftDepth = minDepth(node.left, depth + 1);
            int rightDepth = minDepth(node.right, depth + 1);
            return Math.min(leftDepth, rightDepth);
        }
    }

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
            new FileReader("data/huffman.txt")
        )) {

            String[] firstLine = reader.readLine().trim().split("\\s+");
            int numNodes = Integer.parseInt(firstLine[0]);

            PriorityQueue<Node> queue = new PriorityQueue<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] bits = line.trim().split("\\s+");
                long weight = Long.parseLong(bits[0]);
                Node node = new Node(weight);
                queue.add(node);
            }

            while(queue.size() > 1) {

                Node first = queue.poll();
                Node second = queue.poll();

                long newWeight = first.weight + second.weight;

                Node merged = new Node(newWeight, first, second);
                queue.add(merged);

            }
            Node root = queue.poll();

            int answer = maxDepth(root, 0);
            System.out.println("Maximum codeword length: " + answer);

            int minAnswer = minDepth(root, 0);
            System.out.println("Minimum codeword length: " + minAnswer);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}