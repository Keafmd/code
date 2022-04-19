/**
//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都 互不相同 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1903 👎 0

**/

// 组合总和
// 做题时间：2022-04-19 09:08:23
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum{
    public static void main(String[] args) {
        Solution solution = new CombinationSum().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res =  new ArrayList<>();
        //如果数组为空，直接返回空结果
        if(candidates==null||candidates.length==0){
            return res;
        }
        //先排序
        Arrays.sort(candidates);
        //dfs
        dfs(candidates,target,0,new ArrayList<>(),res);
        return res;
    }

    public void dfs(int[] candidates,int target ,int start,List<Integer> list,List<List<Integer>> res){
        //如果目标值为0，说明已经找到一个组合，添加到结果集中
        if(target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        //遍历数组
        for (int i = start; i < candidates.length; i++) {
            //如果当前值大于目标值，则不需要继续遍历
            if(candidates[i]>target){
                return;
            }
            list.add(candidates[i]);
            //更新目标值和起始位置
            dfs(candidates,target-candidates[i],i,list,res);
            //回溯,删除当前值
            list.remove(list.size()-1);
        }


    }
}

//class Solution {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (candidates == null || candidates.length == 0) {
//            return result;
//        }
//        Arrays.sort(candidates);
//        List<Integer> list = new ArrayList<>();
//        dfs(candidates, target, 0, list, result);
//        return result;
//    }
//
//    private void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> result) {
//        if (target == 0) {
//            result.add(new ArrayList<>(list));
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            if (target < candidates[i]) {
//                return;
//            }
//            list.add(candidates[i]);
//            dfs(candidates, target - candidates[i], i, list, result);
//            list.remove(list.size() - 1);
//        }
//
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)


}


