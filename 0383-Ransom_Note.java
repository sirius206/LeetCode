
// 1. use ArrayList
// Time O(m * n), Space O(m) m-magazine 
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        ArrayList<Character> magArray = new ArrayList<Character>();
        for (int i = 0; i < magazine.length(); i++) {
            magArray.add(magazine.charAt(i));
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (magArray.size() <= 0) {
                return false;
            }
            if (magArray.contains(ransomNote.charAt(i))) {
                magArray.remove(magArray.indexOf(ransomNote.charAt(i)));
            } else return false;
        }
        return true;
    }
}

//2. use substring, better, no extra space needed
//Time O(m * n), Space O(m) m-magazine 

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        for (int i = 0; i < ransomNote.length(); i++) {
            if (magazine.length() == 0) {
                return false;
            }
            int index = magazine.indexOf(ransomNote.charAt(i));
            if (index != -1) {
                magazine = magazine.substring(0, index) + magazine.substring(index + 1, magazine.length());
            }
            else return false;
        }
        return true;
    }
}

//3 Two HashMaps Time O(m), Space O(1)
class Solution {
    
    // Takes a String, and returns a HashMap with counts of
    // each character.
    private Map<Character, Integer> makeCountsMap(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            int currentCount = counts.getOrDefault(c, 0);
            counts.put(c, currentCount + 1);
        }
        return counts;
    }
    
    
    public boolean canConstruct(String ransomNote, String magazine) {
        
        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Make the count maps.
        Map<Character, Integer> ransomNoteCounts = makeCountsMap(ransomNote);
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);
        
        // For each unique character, c, in the ransom note:
        for (char c : ransomNoteCounts.keySet()) {
            // Check that the count of char in the magazine is equal
            // or higher than the count in the ransom note.
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            int countInRansomNote = ransomNoteCounts.get(c);
            if (countInMagazine < countInRansomNote) {
                return false;
            }
        }
        
        // If we got this far, we can successfully build the note.
        return true;
    }
}


//4 One HashMap  Time O(m), Space O(1)
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) return false;
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        for (char c : magazine.toCharArray()) {
            if (!count.containsKey(c)) {
                count.put(c, 1);
            }
            else {
                count.put(c, 1 + count.get(c));
            }
        }
        
        for (char c : ransomNote.toCharArray()) {
            if (!count.containsKey(c) || count.get(c) == 0) return false;
            count.put(c, count.get(c) - 1); 
        }
        return true;
    }
}
