/* Author: Tony Jin
 * Date: 2/26/14
 * 
 * Description: Implement a Trie (prefix tree) data structure that 
 *              stores and returns strings.
 *
 * Dependencies: java.util.*
 *
 * Compile:     % javac Trie.java
 * Runtime:     % java Trie
 *
 * Next steps:  Reduce read times by preclaculating values (doubly linked)
 *              Sort output lexicographically
 *              Sort output by most recent acess date
 *
 */

import java.util.*;

public class Trie {

    // Node in prefix tree
    private class Node {
        private HashMap<Character, Node> children;
        private boolean end;

        public Node() {
            children = new HashMap<Character, Node>();
            end = false;
        }

        public Node add(char c) {
            if (!hasChild(c)) children.put(c, new Node());
            return next(c);      
        }

        public Node next(char c) {
            return children.get(c);
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        private boolean hasChild(char c) {
            return children.containsKey(c);
        }
    }

    private Node head;

    public Trie() {
        head = new Node();
    }

    public Trie(String word) { 
        if (word.length() == 0)
            throw new IllegalArgumentException();

        head = new Node();
        add(word);
    }

    public void add(String word) {
        Node current = head;
        for (int i = 0; i < word.length(); i++) 
            current = current.add(word.charAt(i));
        
        current.setEnd(true);
    }

    public boolean contains(String word) {
        Node current = head;
        for (int i = 0; i < word.length(); i++) {
            current = current.next(word.charAt(i));
            if (current == null)
                return false;
        }

        return current.end;
    }

    public LinkedList<String> getPoss(String prefix) {
        Node current = head;
        for (int i = 0; i < prefix.length(); i++) {
            current = current.next(prefix.charAt(i));
            if (current == null)
                return null;
        }

        LinkedList<String> list = new LinkedList<String>();

        helper(current, prefix, list);

        return list;
    }

    private void helper(Node node, String prefix, LinkedList<String> list) { 
        if (node.end) list.addLast(prefix);

        for(Character c : node.children.keySet()) 
            helper(node.children.get(c), prefix + c, list);
        
    }

    public static void main(String [] args) {
        Trie trie = new Trie("bo");
        trie.add("hi");
        trie.add("him");
        trie.add("hiss");
        System.out.println(trie.contains("hi"));
        System.out.println(trie.contains("boo"));
        System.out.println(trie.contains("hit")); 
        System.out.println(trie.contains("h"));
        System.out.println(trie.contains(""));
        LinkedList<String> ls = trie.getPoss("h");
        System.out.println();
        for(String s : ls)
            System.out.println(s);
    }    
}