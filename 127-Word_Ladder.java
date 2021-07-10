//1. BFS, use set, and use a-z to replace each char in word
// Time: O(M^2*N), where M is the length of each word and N is the total number of words in the input word list.
// For each word in the word list, we iterate over its length to find all the intermediate words corresponding to it. 
//Since the length of each word is M and we have N words, the total number of iterations the algorithm takes to create all_combo_dict is M×N. 
//Additionally, forming each of the intermediate word takes O(M), so total O(M^2*N)
// Space O(M*N)  O(26*N) = O(N)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
          return 0;
        }
        words.remove(beginWord);
        q.offer(beginWord);
        int level = 0;
        while (!q.isEmpty()){
            level++;
            int size = q.size();
            while (size != 0) {
                size--;
                String currentWord = q.poll();
                if (currentWord.equals(endWord)) return level;
                List<String> neighbors = neighbors(currentWord);
                for (String neigh : neighbors){
                    if (words.contains(neigh)){
                        words.remove(neigh);
                        q.add(neigh);
                    }
                }
            }
        }
        return 0;
    }

    
    public List<String> neighbors(String string){
        char[] chars = string.toCharArray();
        List<String> result = new ArrayList<>();
      // use char array instead of substring, cheaper
      // O(M * M)
        for (int i = 0; i < chars.length; i++){
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++){
                chars[i] = c;
              //O (M)
                String neighbor = new String(chars);
                result.add(neighbor);
            }
            chars[i] = temp;
        }
      return result;
    }
}

//2. BFS with substring
// Time Complexity: O(M^2*N), Space O(M^2*N), more space
class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    // Since all words are of same length.
    int L = beginWord.length();

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    Map<String, List<String>> allComboDict = new HashMap<>();

    wordList.forEach(
        word -> {
          for (int i = 0; i < L; i++) {
            // Key is the generic word
            // Value is a list of words which have the same intermediate generic word.
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
            transformations.add(word);
            allComboDict.put(newWord, transformations);
          }
        });

    // Queue for BFS
    Queue<Pair<String, Integer>> Q = new LinkedList<>();
    Q.add(new Pair(beginWord, 1));

    // Visited to make sure we don't repeat processing same word.
    Map<String, Boolean> visited = new HashMap<>();
    visited.put(beginWord, true);

    while (!Q.isEmpty()) {
      Pair<String, Integer> node = Q.remove();
      String word = node.getKey();
      int level = node.getValue();
      for (int i = 0; i < L; i++) {

        // Intermediate words for current word
        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

        // Next states are all the words which share the same intermediate state.
        for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
          // If at any point if we find what we are looking for
          // i.e. the end word - we can return with the answer.
          if (adjacentWord.equals(endWord)) {
            return level + 1;
          }
          // Otherwise, add it to the BFS Queue. Also mark it visited
          if (!visited.containsKey(adjacentWord)) {
            visited.put(adjacentWord, true);
            Q.add(new Pair(adjacentWord, level + 1));
          }
        }
      }
    }

    return 0;
  }
}

//3. Bidirectional BFS, see again the video solution, this isn't it
// Time Complexity: O(M^2*N), Space O(M^2*N), more space
class Solution {

  private int L;
  private Map<String, List<String>> allComboDict;

  Solution() {
    this.L = 0;

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    this.allComboDict = new HashMap<>();
  }

  private int visitWordNode(
      Queue<Pair<String, Integer>> Q,
      Map<String, Integer> visited,
      Map<String, Integer> othersVisited) {

    Pair<String, Integer> node = Q.remove();
    String word = node.getKey();
    int level = node.getValue();

    for (int i = 0; i < this.L; i++) {

      // Intermediate words for current word
      String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

      // Next states are all the words which share the same intermediate state.
      for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
        // If at any point if we find what we are looking for
        // i.e. the end word - we can return with the answer.
        if (othersVisited.containsKey(adjacentWord)) {
          return level + othersVisited.get(adjacentWord);
        }

        if (!visited.containsKey(adjacentWord)) {

          // Save the level as the value of the dictionary, to save number of hops.
          visited.put(adjacentWord, level + 1);
          Q.add(new Pair(adjacentWord, level + 1));
        }
      }
    }
    return -1;
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    if (!wordList.contains(endWord)) {
      return 0;
    }

    // Since all words are of same length.
    this.L = beginWord.length();

    wordList.forEach(
        word -> {
          for (int i = 0; i < L; i++) {
            // Key is the generic word
            // Value is a list of words which have the same intermediate generic word.
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            List<String> transformations =
                this.allComboDict.getOrDefault(newWord, new ArrayList<>());
            transformations.add(word);
            this.allComboDict.put(newWord, transformations);
          }
        });

    // Queues for birdirectional BFS
    // BFS starting from beginWord
    Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
    // BFS starting from endWord
    Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
    Q_begin.add(new Pair(beginWord, 1));
    Q_end.add(new Pair(endWord, 1));

    // Visited to make sure we don't repeat processing same word.
    Map<String, Integer> visitedBegin = new HashMap<>();
    Map<String, Integer> visitedEnd = new HashMap<>();
    visitedBegin.put(beginWord, 1);
    visitedEnd.put(endWord, 1);

    while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

      // One hop from begin word
      int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
      if (ans > -1) {
        return ans;
      }

      // One hop from end word
      ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
      if (ans > -1) {
        return ans;
      }
    }

    return 0;
  }
}
