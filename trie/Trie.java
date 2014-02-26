public class Trie {

    private class Node {
        public Node [] children;


        public Node () { 
            children = new Node[26];
        }
        
        public void add(String word) {
            word = word.toLowerCase();
            char c = word.charAt(0);
            if(!hasChild(c));
                children[c - 97] = new Node();

            charToNode(c).add(word.substring(1));
        }

        public boolean contains(String word) {
            return hasChild(word.charAt(0)) && contains(word.substring(1));
        }

        private boolean hasChild(char c) {
            return (charToNode(c) != null);
        }

        private Node charToNode(char c) { 
            return children[c - 97]; 
        }
    }

    private Node head;

    public Trie() {
        head = new Node();
    }

    public Trie(String word) { 
        head = new Node();
        head.add(word);
    }

    public void add(String word) {
        head.add(word);
    }

    public boolean contains(String word) {
        return head.contains(word);
    }

    public static void main(String [] args) {
        Trie trie = new Trie();
        trie.add("hi");
        trie.contains("hi");
        trie.contains("boo");
    }    
}