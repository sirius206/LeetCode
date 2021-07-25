//use count

class TrieNode{
    public char val;
    public TrieNode[] children;
    public boolean isEnd;
    public int endCount = 0;
    public int prefixCount = 0;
    public TrieNode(char c){
        this.val = c;
        this.children = new TrieNode[26];
    }
}
class Trie {
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode(' ');
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode(c);
            }
            node = node.children[c - 'a'];
            node.prefixCount++;
        }
        node.isEnd = true;
        node.endCount++;
    }
    
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) return 0;
            node = node.children[c - 'a'];
        }
        return node.endCount;
    }
    

    public int countWordsStartingWith(String prefix) {
        
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) return 0;
            node = node.children[c - 'a'];
        }
        return node.prefixCount;
        
    }
    
    // do not just decrease count, also free memory by setting null
    public void erase(String word) {
        TrieNode temp = root;
        boolean deleteNode = false;
        for (char c : word.toCharArray()) {
            TrieNode next = temp.children[c - 'a'];
            next.prefixCount--;
            if (next.prefixCount == 0) {
                deleteNode = true;
            }
            if (deleteNode) {
                temp.children[c-'a'] = null;
            }
            temp = next;
        }
        temp.endCount--;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
