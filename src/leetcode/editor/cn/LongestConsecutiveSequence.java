/**
//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 哈希表 
// 👍 1227 👎 0

**/

// 最长连续序列
// 做题时间：2022-04-22 09:52:26
package leetcode.editor.cn;

import java.util.Arrays;

public class LongestConsecutiveSequence{
    public static void main(String[] args) {
        Solution solution = new LongestConsecutiveSequence().new Solution();
        int nums[] = {0,0,-1};
        int res = solution.longestConsecutive(nums);
        System.out.println(res);
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        //排序下
        Arrays.sort(nums);
        //max初始化为1，因为最小的连续序列长度为1，避免数组中只有一个元素的情况，也可以在上面写if提前过滤
        int max = 1;
        int[] dp = new int[nums.length];
        //先填充个初始长度，包括本身，也就是1
        Arrays.fill(dp,1);

        for(int i=1;i<nums.length;i++){
            //比前一个大，那么就是连续的
            if(nums[i]==nums[i-1]+1){
                dp[i] = dp[i-1] + 1;
            }
            //和前一个相等，连续长度保持就行
            if(nums[i]==nums[i-1]){
                dp[i] = dp[i-1];
            }
            max = Math.max(max,dp[i]);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}


