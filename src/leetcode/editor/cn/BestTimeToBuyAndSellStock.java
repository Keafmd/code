/**
//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 104 
// 
// Related Topics 数组 动态规划 
// 👍 2304 👎 0

**/

// 买卖股票的最佳时机
// 做题时间：2022-04-24 09:33:27
package leetcode.editor.cn;
public class BestTimeToBuyAndSellStock{
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        solution.maxProfit(new int[]{7,1,5,3,6,4});
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        //dp dp[i]表示该天的最大利益
        int[] dp = new int[prices.length];
        //最大利益
        int max = 0;
        //从第二天开始，每天的最大利益取决于前一天的最大利益和当天的价格
        for (int i = 1; i < prices.length; i++) {
            //当前天的最大利益 = 前一天的最大利益 + 当天的价格 - 前一天的价格
            //如果当天的利益小于0，则当天的利益为0
            dp[i] = Math.max(dp[i-1] + prices[i] - prices[i-1],0);
            //更新最大利益
            max = Math.max(max,dp[i]);
        }
        //返回最大利益
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}


