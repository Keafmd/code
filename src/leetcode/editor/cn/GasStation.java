/**
//在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。 
//
// 
//
// 示例 1: 
//
// 
//输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//输出: 3
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 
//输入: gas = [2,3,4], cost = [3,4,3]
//输出: -1
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
//
// 
//
// 提示: 
//
// 
// gas.length == n 
// cost.length == n 
// 1 <= n <= 105 
// 0 <= gas[i], cost[i] <= 104 
// 
// Related Topics 贪心 数组 
// 👍 911 👎 0

**/

// 加油站
// 做题时间：2022-04-29 10:54:38
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GasStation{
    public static void main(String[] args) {
        Solution solution = new GasStation().new Solution();
        solution.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, sum = 0, tank = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                sum += tank;
                tank = 0;
            }
        }
        return sum + tank >= 0 ? start : -1;

    }
}
/*class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null || gas.length == 0 || cost.length == 0){
            return -1;
        }
        if(gas.length == 1 && cost[0]== gas[0]){
            return 0;
        }

        int[] last = new int[gas.length];

        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            last[i] = gas[i] - cost[i];
            sum+=last[i];
            if(last[i]>0){
                list.add(i);
            }
        }
        if(sum<0){
            return -1;
        }
        int res = -1;
        for (Integer i : list) {
            boolean[] flag = new boolean[gas.length];
            flag[i] = true;
            int curr = i;
            int j=0;
            sum = last[curr];
            for(j=0;j<gas.length;j++){
                int next = curr+1==gas.length?0:curr+1;
                sum +=last[next];
                if(sum < 0){
                    break;
                }
                curr = curr+1==gas.length?0:curr+1;
            }
            if(j==gas.length){
                return i;
            }

        }
        return res;

    }
}*/
//leetcode submit region end(Prohibit modification and deletion)


}


