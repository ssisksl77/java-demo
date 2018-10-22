package algorithm.tree.zigzag;

import java.util.Stack;

public class BinaryTree {
    Node rootNode;

    void printZigZag() {
        if (rootNode == null) return;

        Stack<Node> zig = new Stack<>();
        Stack<Node> zag = new Stack<>();

        zig.add(rootNode);

        Node node;
        int zigzagToggle = 0;
        while(!zig.empty() || !zag.empty()) {

            if (zigzagToggle == 0) {
                node = zig.pop();
                System.out.print(node.data + " ");
                //System.out.println("zig " + node.data);
                if (node.leftChild != null) { zag.add(node.leftChild); }
                if (node.rightChild != null) { zag.add(node.rightChild); }
                if (zig.empty()) { zigzagToggle = 1; }

            }
            else {
                node = zag.pop();
                System.out.print(node.data + " ");
                //System.out.println("zag " + node.data);
                if (node.rightChild != null) { zig.add(node.rightChild); }
                if (node.leftChild != null) { zig.add(node.leftChild); }
                if (zag.empty()) { zigzagToggle = 0; }
            }
        }
    }

    void zigzag() {
        if (rootNode == null) return;

        Stack<Node> zig = new Stack<>();
        Stack<Node> zag = new Stack<>();

        zig.add(rootNode);

        Node node;
        boolean zigzagToggle = true;
        while(!zig.isEmpty() || !zag.isEmpty()) {
            if (zigzagToggle) {
                node = zig.pop();
                System.out.print(node.data + " ");
                if (node.leftChild != null) zag.push(node.leftChild);
                if (node.rightChild != null) zag.push(node.rightChild);
                if (zig.isEmpty()) zigzagToggle = false;
            } else {
                node = zag.pop();
                System.out.print(node.data + " ");
                if (node.leftChild != null) zig.push(node.leftChild);
                if (node.rightChild != null) zig.push(node.rightChild);
                if (zag.isEmpty()) zigzagToggle = true;
            }
        }

    }


    static class Node {
        int data;
        Node leftChild;
        Node rightChild;

        Node(int data) {
            this.data = data;
        }
    }


}
