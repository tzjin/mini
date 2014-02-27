// Implement a trie data structure 

import java.util.*;

public class Trie {

    private class Node {
        private Character a;
        private HashMap<Character, Node> children;
        private boolean end;

        public Node (char c) {
            children = new HashMap<Character, Node>();
            a = c;
        }

        public Node add(char c) {
            Node next;
            if (hasChild(c))
                next = children.get(c);
            else {
                next = new Node(a);
                children.put(c, next);
            }

            return next;      
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        private boolean hasChild(char c) {
            return children.containsKey(c);
        }

    }

    private Node head;

    public Trie(String word) { 
        if (word.length() == 0)
            throw new IllegalArgumentException();

        head = new Node(word.charAt(0));
        add(word);
    }

    public void add(String word) {
        Node current = head;
        for (int i = 0; i < word.length(); i++) 
            current = current.add(word.charAt(i));
        
        current.setEnd(true);
    }

    public boolean contains(String word) {
        return true; // not implemented
    }

    public static void main(String [] args) {
        Trie trie = new Trie("hit");
        trie.add("hi");
        trie.contains("hi");
        trie.contains("boo");
    }    
}