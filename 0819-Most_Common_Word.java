
//1. Time O(m+ n), space O(m+ n), set.contains() takes O(1)
//Mine HashMap + priorityqueue + set
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.split("\\W+");
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String ban : banned){
            set.add(ban.toLowerCase());
        }
        for (String str : words){
            map.put(str.toLowerCase(), map.getOrDefault(str.toLowerCase(), 0) + 1);
        }
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        for (String str: map.keySet()){
            pq.add(new Pair(str, map.get(str)));
        }
        while (pq.size() != 0){
            Pair<String, Integer> frequentWord = pq.poll();
            if (!set.contains(frequentWord.getKey())){
                return frequentWord.getKey();
            }
        }
        return "";
    }
}

//2. ans
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {

        // 1). replace the punctuations with spaces,
        // and put all letters in lower case
        String normalizedStr = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();   //

        // 2). split the string into words
        String[] words = normalizedStr.split("\\s+");

        Set<String> bannedWords = new HashSet();
        for (String word : banned)
            bannedWords.add(word);

        Map<String, Integer> wordCount = new HashMap();
        // 3). count the appearance of each word, excluding the banned words
        for (String word : words) {
            if (!bannedWords.contains(word))
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 4). return the word with the highest frequency
        return Collections.max(wordCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
