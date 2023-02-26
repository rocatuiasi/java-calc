package main;

public class Node {
    String data;
    Node left, right;

    public Node(String item) {
        data = item;
        left = right = null;
    }

    public Node(String item, Node leftNode, Node rightNode) {
        data = item;
        left = leftNode;
        right = rightNode;
    }

    public void printTree(String prefix) {
        System.out.println(prefix + data);

        if (left != null)
            left.printTree(prefix + "├── ");
        if (right != null)
            right.printTree(prefix + "└── ");
    }
}
