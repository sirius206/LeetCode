//1. Mine, Topological Sorting, Time O(C) C is the total length of all the words, Space O(1)

class Solution {
    public String alienOrder(String[] words) {
        String res = "";
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, ArrayList<Character>> adjList = new HashMap<>();
        int len = words.length;
        for (String word : words){
            for (Character c : word.toCharArray()){
                inDegree.put(c, 0);
                adjList.put(c, new ArrayList<Character>());
            }
        }
        if (len == 0) return "";
        if (len == 1) return words[0];
        for (int i = 0; i < len - 1; i++){
            String word1 = words[i];
            String word2 = words[i + 1];
            int len1 = word1.length();
            int len2 = word2.length();
            int lenmin = Math.min(len1, len2);
            int j = 0;
            for (j = 0; j < lenmin; j++){
                Character before = word1.charAt(j);
                Character after = word2.charAt(j);
                if (before == after) {
                    continue;
                }
                inDegree.put(after, inDegree.get(after) + 1);
                adjList.get(before).add(after);
                break;
            }
            if (len1 > len2) {   //if (word1.length() > word2.length() && word1.startsWith(word2))
                if (j == lenmin && word1.charAt(lenmin - 1) == word2.charAt(lenmin - 1)) {  
                    return "";
                }
            }            
        }
        
        Queue<Character> q = new LinkedList<>();
        int count = 0;
        for (Character c : inDegree.keySet()) {
            if (inDegree.get(c) == 0){
                q.offer(c);
            }
        }

        while (!q.isEmpty()){
            Character c = q.poll();
            res = res + c;
            count++;
            ArrayList<Character> neighbors = adjList.get(c);
            if (neighbors != null)  {
                for (Character neighbor : neighbors) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0){
                        q.offer(neighbor);
                    }
                }                
            }

        }
        if (count == inDegree.size()) return res;
        else return "";
    }
}

//2. BFS Answer
public String alienOrder(String[] words) {
    
    // Step 0: Create data structures and find all unique letters.
    Map<Character, List<Character>> adjList = new HashMap<>();
    Map<Character, Integer> counts = new HashMap<>();
    for (String word : words) {
        for (char c : word.toCharArray()) {
            counts.put(c, 0);
            adjList.put(c, new ArrayList<>());
        }
    }
    
    // Step 1: Find all edges.
    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];
        // Check that word2 is not a prefix of word1.
        if (word1.length() > word2.length() && word1.startsWith(word2)) {      ////////////////
            return "";
        }
        // Find the first non match and insert the corresponding relation.
        for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
            if (word1.charAt(j) != word2.charAt(j)) {
                adjList.get(word1.charAt(j)).add(word2.charAt(j));
                counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                break;
            }
        }
    }
    
    // Step 2: Breadth-first search.
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for (Character c : counts.keySet()) {
        if (counts.get(c).equals(0)) {
            queue.add(c);
        }
    }
    while (!queue.isEmpty()) {
        Character c = queue.remove();
        sb.append(c);
        for (Character next : adjList.get(c)) {
            counts.put(next, counts.get(next) - 1);
            if (counts.get(next).equals(0)) {
                queue.add(next);
            }
        }
    }
    
    if (sb.length() < counts.size()) {
        return "";
    }
    return sb.toString();
}
//3. DFS Answer
class Solution {
    
    private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
    private Map<Character, Boolean> seen = new HashMap<>();
    private StringBuilder output = new StringBuilder();
    
    public String alienOrder(String[] words) {
        
        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        
        // Step 2: DFS to build up the output list.
        for (Character c : reverseAdjList.keySet()) {
            boolean result = dfs(c);
            if (!result) return "";
        }
        
        
        if (output.length() < reverseAdjList.size()) {
            return "";
        }
        return output.toString();
    }
    
    // Return true iff no cycles detected.
    private boolean dfs(Character c) {
        if (seen.containsKey(c)) {
            return seen.get(c); // If this node was grey (false), a cycle was detected.
        }
        seen.put(c, false);
        for (Character next : reverseAdjList.get(c)) {
            boolean result = dfs(next);
            if (!result) return false;
        }
        seen.put(c, true);
        output.append(c);
        return true;
    }    
}
