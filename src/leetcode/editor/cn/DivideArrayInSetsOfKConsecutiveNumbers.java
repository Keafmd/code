/**
//给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。 
//如果可以，请返回 true；否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,3,4,4,5,6], k = 4
//输出：true
//解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
//输出：true
//解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3,2,2,1,1], k = 3
//输出：true
// 
//
// 示例 4： 
//
// 
//输入：nums = [1,2,3,4], k = 3
//输出：false
//解释：数组不能分成几个大小为 3 的子数组。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 105 
// 1 <= nums[i] <= 109 
// 
//
// 
//
// 注意：此题目与 846 重复：https://leetcode-cn.com/problems/hand-of-straights/ 
// Related Topics 贪心 数组 哈希表 排序 
// 👍 76 👎 0

**/

// 划分数组为连续数字的集合
// 做题时间：2022-03-24 11:41:07
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DivideArrayInSetsOfKConsecutiveNumbers{
    public static void main(String[] args) {
        Solution solution = new DivideArrayInSetsOfKConsecutiveNumbers().new Solution();
        int[] num = {1,2,3,3,4,4,5,6};
        solution.isPossibleDivide(num,4);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {

        if(nums.length%k!=0){
            return false;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        Arrays.sort(nums);

        for (int num : nums) {
            if(!map.containsKey(num)){
                continue;
            }
            for(int j=0;j<k;j++){
                int sum =num+j;
                if(!map.containsKey(sum)){
                    return false;
                }
                map.put(sum,map.get(sum)-1);
                if(map.get(sum)==0){
                    map.remove(sum);
                }
            }
        }



        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)


}


