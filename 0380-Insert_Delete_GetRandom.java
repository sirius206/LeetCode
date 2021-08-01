/* If use hashmap, getRandom is linear time to get the keys, insert and delete is O(1)
 if use ArrayList, delete is linear time, although insert and getRandom is O(1) 
 the idea is to use Hashmap + ArrayList, 
 Hashmap element -> its index.
Array List of elements. 
To delete: Swap the element to delete with the last one, use list.set(id, num).  Pop the last element out.
*/

import java.util.Random;
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (this.map.containsKey(val)) return false;
        else {
            this.map.put(val, map.size());
            this.list.add(val);
        }
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (this.map.containsKey(val)){
            int id = map.get(val);
            int last = list.get(list.size() - 1);
            list.set(id, last);
            map.put(last, id);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int size = this.list.size();
        Random rand = new Random();
        int rand_id = rand.nextInt(size);
        return this.list.get(rand_id);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
