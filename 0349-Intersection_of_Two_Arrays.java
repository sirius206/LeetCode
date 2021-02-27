//1 Hashmap Time O(m+n), Space O(m)
//C++
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> st(nums1.begin(), nums1.end()), res;
        for (auto a : nums2) {
            if (st.count(a)) res.insert(a);
        }
        return vector<int>(res.begin(), res.end());
    }
};

//2 Sort then two pointers, if already sorted Time O(n) Space O(1)
//C++
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        vector<int> res;
        int i = 0, j = 0;
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        while (i < nums1.size() && j < nums2.size()) {
            if (nums1[i] < nums2[j]) ++i;
            else if (nums1[i] > nums2[j]) ++j;
            else {
                if (res.empty() || res.back() != nums1[i]) {
                    res.push_back(nums1[i]);
                }
                ++i; ++j;
            }
        }
        return res;
    }
};

// 3 sort one array then Binary Search Time O(m log m + m log n ) Space O(1)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums2);
        Set<Integer> intersect = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            if (binarySearch(nums2, nums1[i]) != -1) {
                intersect.add(nums1[i]);
            }
        }
        int [] res = new int[intersect.size()];
        int i = 0;
        for (int x : intersect) {
            res[i] = x;
            i++;
        }
        return res;
    }
    
    public int binarySearch(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
       int mid = (low + high) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) {
            high = mid - 1;
        }
        else low = mid + 1;
    }
    return -1;
    }
}
