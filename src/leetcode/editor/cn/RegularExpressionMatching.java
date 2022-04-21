/**
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 
// 👍 2918 👎 0

**/

// 正则表达式匹配
// 做题时间：2022-04-19 19:36:23
package leetcode.editor.cn;
public class RegularExpressionMatching{
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //dp[i][j] 表示 s[0..i-1] 和 p[0..j-1] 是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        //初始化
        dp[0][0] = true;
        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1) == '*'){ //p的第j个字符为*
                    // 如果 p[j-1] 为 *，则可以匹配 0 次或多次
                    //相当于舍弃 字母+*
                    dp[i][j] = dp[i][j-2];
                    //匹配s的第i个字符和p的第j-1个字符
                    if(match(s,p,i,j-1)) {
                        // 字母+* 舍弃或  *+字母 抵消
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }else{//p的第j个字符不为*
                    //正常匹配
                    if(match(s,p,i,j)) { //匹配s的第i个字符和p的第j个字符
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                }
            }
        }
        return dp[m][n];
    }
    //判断s的第i个字符和p的第j个字符是否匹配
    public boolean match(String s, String p, int i, int j){
        //注意在字符串中的下标变换

        if(i==0){
            return false;
        }
        if(p.charAt(j-1) == '.'){
            return true;
        }
        return p.charAt(j-1) == s.charAt(i-1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}


