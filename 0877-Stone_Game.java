//1. since number of piles is even, Alice always wins
//2. 2D DP
// Time O(n^2) Space O(n^2)
/*
dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
You can first pick piles[i] or piles[j].

If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
dp[i][j] 的值可以被 piles[i] - dp[i+1][j] 更新，因为 Alex 拿了 piles[i]，减去 Lee 多出的 dp[i+1][j]，就是区间 [i, j] 中 Alex 多拿的石子数。
同理，假如 Alex 先拿 piles[j]，那么就用 piles[j] - dp[i][j-1] 来更新 dp[i][j]，则我们用二者的较大值来更新即可。

So we get:
dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
We start from smaller subarray and then we use that to calculate bigger subarray.
注意开始的时候要把 dp[i][i] 都初始化为 piles[i]，还需要注意的是，这里的更新顺序很重要，是从小区间开始更新

Note that take evens or take odds, it's just an easy strategy to win when the number of stones is even.
It's not the best solution!
I didn't find a tricky solution when the number of stones is odd (maybe there is).
*/
class Solution {
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[][] dp  = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = p[i];
        for (int d = 1; d < n; d++)
            for (int i = 0; i < n - d; i++){
                int j = i + d;
                dp[i][j] = Math.max(p[i] - dp[i + 1][j], p[j] - dp[i][j - 1]);                
            }

        return dp[0][n - 1] > 0;
    }
}

//2b 2D DP, take turns
class Solution {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                // Alice's turn, max
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                // Lee's turn, min
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }
}

// 3. 3D DP
class Solution {
    int [][][] memo;
    public boolean stoneGame(int[] piles) {
        memo = new int[piles.length + 1][piles.length + 1][2];
        for(int [][] arr : memo)
            for(int [] subArr : arr)
                Arrays.fill(subArr, -1);
        
        return (helper(0, piles.length - 1, piles, 1) >= 0);
    }
    
    public int helper(int l, int r, int [] piles, int ID){
        if(r < l)
            return 0;
        if(memo[l][r][ID] != -1)
            return memo[l][r][ID];
        
        int next = Math.abs(ID - 1);
        if(ID == 1)
            memo[l][r][ID] = Math.max(piles[l] + helper(l + 1, r, piles, next), piles[r] + helper(l, r - 1, piles, next));
        else
            memo[l][r][ID] = Math.min(-piles[l] + helper(l + 1, r, piles, next), -piles[r] + helper(l, r - 1, piles, next));
        
        return memo[l][r][ID];
    }
}

//4. recursion, C++
class Solution {
public:
    bool stoneGame(vector<int>& piles) {
		return helper(piles, 0, 0, 0, (int)piles.size() - 1, 0);     
    }
	bool helper(vector<int>& piles, int cur0, int cur1, int left, int right, int player) {
		if (left > right) return cur0 > cur1;
		if (player == 0) {
			return helper(piles, cur0 + piles[left], cur1, left + 1, right, 1) || helper(piles, cur0 + piles[right], cur1, left + 1, right, 1);
		} else {
			return helper(piles, cur0, cur1 + piles[left], left, right - 1, 0) || helper(piles, cur0, cur1 + piles[right], left, right - 1, 0);
		}
	}
};
