package com.colossus.yandex_contest.decisions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class SomethingDreamed {

    class Node{
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public String run(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String[] firstLine = reader.readLine().split(" ");
        int countOfNode = Integer.parseInt(firstLine[0]);
        int countOfChanges = Integer.parseInt(firstLine[1]);

        int[] changes = new int[countOfChanges];
        String[] secondLine = reader.readLine().split(" ");
        reader.close();

        for (int i = 0; i < countOfChanges; i++) {
            changes[i] = Integer.parseInt(secondLine[i]);
        }

        Node root = new Node(1,null,null);
        createTree(root, countOfNode);

        for (int change : changes) {
            find(root, change);
        }

        return printLVR(root);
    }

    private String printLVR(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root.left != null) sb.append(printLVR(root.left)).append(" ");
        sb.append(root.val).append(" ");
        if (root.right != null) sb.append(printLVR(root.right)).append(" ");
        return sb.toString().trim();
    }

    private void find(Node root, int leaf) {
        if (root.left != null) {
            if (root.left.val != leaf) find(root.left, leaf);
            else {
                swapNode(root, root.left, false);
                return;
            }
        }
        if (root.right != null) {
            if (root.right.val != leaf) find(root.right, leaf);
            else {
                swapNode(root, root.right, true);
                return;
            }
        }

    }

    private void swapNode(Node main, Node sub, boolean b) {
        Node transfer = new Node(main.val,main.left,main.right);
        main.val = sub.val;
        sub.val = transfer.val;

        if (b) {
            main.left = sub.left;
            sub.left = transfer.left;
        } else {
            main.right = sub.right;
            sub.right = transfer.right;
        }
    }

    private void createTree(Node root, int countOfNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int value = 2;
        for (int i = 1; i < countOfNode; i++) {
            Node head = queue.poll();
            Node left = new Node(value++, null,null);
            head.left = left;
            queue.add(left);
            if (++i < countOfNode){
                Node right = new Node(value++, null, null);
                head.right = right;
                queue.add(right);
            }
        }
    }
}
