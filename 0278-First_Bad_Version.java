// my method
//binary search Time O(log n)
//avoid overflow: set mid = low + (high - low) / 2, not mid = (low + high) / 2

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (!isBadVersion(mid)){
                low = mid + 1;
            }
            else if (isBadVersion(mid) && isBadVersion(mid - 1)) {
                high = mid - 1;
            }
            else return mid;
        }
        return -1;
    }
}

//answer
//when left and right meet, it is the first bad version

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) right = mid;     //if mid is bad, mid could be the first, so not mid + 1
            else left = mid + 1;
        }
        return left;
    }
}
