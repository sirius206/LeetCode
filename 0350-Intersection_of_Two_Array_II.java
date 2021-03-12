//1 sort then Two pointers 
// Time O(nlogn + m logm + m +n) Space O(m+n)

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> intersect = new ArrayList<Integer>();
        int i = 0, j = 0; 
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]){
                intersect.add(nums1[i]);
                i++;
                j++;
            } 
            else if (nums1[i] > nums2[j]) j++;
            else i++;
        }
        int[] res = new int[intersect.size()];
        int k = 0;
        for (int x : intersect) {
            res[k] = x;
            k++;
        }
        return res;
    }
}

//2 HashMap
//C++
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> m;
        vector<int> res;
        for (auto a : nums1) ++m[a];
        for (auto a : nums2) {
            if (m[a]-- > 0) res.push_back(a);
        }
        return res;
    }
};
