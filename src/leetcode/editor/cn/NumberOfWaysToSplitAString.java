/**
//给你一个二进制串 s （一个只包含 0 和 1 的字符串），我们可以将 s 分割成 3 个 非空 字符串 s1, s2, s3 （s1 + s2 + s3 
//= s）。 
//
// 请你返回分割 s 的方案数，满足 s1，s2 和 s3 中字符 '1' 的数目相同。 
//
// 由于答案可能很大，请将它对 10^9 + 7 取余后返回。 
//
// 
//
// 示例 1： 
//
// 输入：s = "10101"
//输出：4
//解释：总共有 4 种方法将 s 分割成含有 '1' 数目相同的三个子字符串。
//"1|010|1"
//"1|01|01"
//"10|10|1"
//"10|1|01"
// 
//
// 示例 2： 
//
// 输入：s = "1001"
//输出：0
// 
//
// 示例 3： 
//
// 输入：s = "0000"
//输出：3
//解释：总共有 3 种分割 s 的方法。
//"0|0|00"
//"0|00|0"
//"00|0|0"
// 
//
// 示例 4： 
//
// 输入：s = "100100010100110"
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// s[i] == '0' 或者 s[i] == '1' 
// 3 <= s.length <= 10^5 
// 
// Related Topics 数学 字符串 
// 👍 9 👎 0

**/

// 分割字符串的方案数
// 做题时间：2022-03-31 10:42:07
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToSplitAString{
    public static void main(String[] args) {
        Solution solution = new NumberOfWaysToSplitAString().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numWays(String s) {
        List<Integer> ones = new ArrayList<>();

        int mod = 1000000007;
        int n = s.length();
        // 将字符串中的 1 所在的位置存入数组
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        //1的个数
        int cout = ones.size();
        //如果字符串中1的个数为0，则可以可以随意分割
        if (cout == 0) {
            long ways = (long) (n - 1) *(n-2)/2;
            return (int)(ways%mod);
        }else if(cout%3 != 0){
            return 0;
        }else {
            int index1 = cout/3;
            int index2 = cout/3*2;
            int cout1 = ones.get(index1)-ones.get(index1-1);
            int cout2 = ones.get(index2)-ones.get(index2-1);
            long ways = (long) cout1*cout2;
            return (int)(ways%mod);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)


}


