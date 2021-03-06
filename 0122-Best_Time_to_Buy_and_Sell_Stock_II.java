//1. 今天比昨天高 就累加profit 否则profit不变， 遍历一遍数组
//Time O(n) Space O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i + 1] > prices[i]) {
                    profit += prices[i + 1] - prices[i];
                }
            }
        return profit; 
    }
}


//2. 交易次数少 只要出现价格比之前高并且价格后面一天下降时就交易  交易次数少
//Time O(n) Space O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] >= prices[j]) {
                    break;
                }
                if (j == prices.length - 1 || prices[j] >= prices[j + 1]) {
                    int sell = prices[j];
                    profit += sell - buy;
                    i = j;
                }
                
            }
        }
        return profit; 
    }
}
