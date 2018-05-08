package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class FileTree {
    private final Node<File> root;
    private final PrintWriter out = new PrintWriter("out\\production\\FindFile\\com\\company\\Log.txt");
    private final Map<String, HashSet<Node<File>>> hashMap = new HashMap<>(4096);

    FileTree(File file) throws IOException {
        root = new Node<>(file);
        add2HashMap(root);
        addFile(root);
        out.close();
    }

    private void addFile(final Node<File> node) {
        try {
            File[] files = node.item.listFiles();
            if (files == null)
                return;
            int num = files.length;
            @SuppressWarnings("unchecked")
            Node<File>[] nodes = new Node[num];
            for (int i = 0; i < num; i++) {
                nodes[i] = new Node<>(files[i]);
//                nodes[i].prev = node;
                add2HashMap(nodes[i]);
                if (files[i].isDirectory()) {
                    addFile(nodes[i]);
                }
            }
            node.nextTree = nodes;
        } catch (NullPointerException e) {
            System.err.println(node);
            out.println(node);
        }
    }

    void show() {
        System.out.println(root.item);
        showNext(root);
    }//前序遍历

    private void showNext(Node<File> node) {
        Node<File>[] next = node.nextTree;
        for (Node<File> fileNode : next) {
            System.out.println(fileNode.item);
            if (fileNode.item.isDirectory()) {
                showNext(fileNode);
            }
        }
    }

    boolean search(String fileName) {
        HashSet<Node<File>> hashSet = hashMap.get(fileName);
        if (hashSet == null) {
            return false;
        }
        for (Node node : hashSet) {
            System.out.println(node);
        }
        return true;
    }

    private void add2HashMap(Node<File> node) {
        String key = node.item.getName();
        HashSet<Node<File>> value = hashMap.get(key);
        if (value == null) {
            value = new HashSet<>();
            value.add(node);
            hashMap.put(key, value);
        } else {
            value.add(node);
        }
    }

    private static class Node<E> {
        private E item;
        //        private Node<E> prev;
        private Node<E>[] nextTree;

        private Node(E data) {
            this.item = data;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }
}