/**
//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 
// 👍 496 👎 0

**/

// 机器人的运动范围
// 做题时间：2022-04-28 10:57:03
package leetcode.editor.cn;
public class JiQiRenDeYunDongFanWeiLcof{
    public static void main(String[] args) {
        Solution solution = new JiQiRenDeYunDongFanWeiLcof().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //结果
    int sum =0;
    public int movingCount(int m, int n, int k) {
        //初始化一个二维数组表示是否访问过
        boolean[][] visited = new boolean[m][n];
        dfs(0,0,m,n,k,visited);
        return sum;
    }
    private void dfs(int i,int j,int m,int n,int k,boolean[][] visited){
        //判断是否符合条件
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || getSum(i)+getSum(j) > k){
            return;
        }
        //标记访问过了
        visited[i][j] = true;
        //结果加1
        sum++;
        //深度优先搜索 四个方向
        dfs(i-1,j,m,n,k,visited);
        dfs(i+1,j,m,n,k,visited);
        dfs(i,j-1,m,n,k,visited);
        dfs(i,j+1,m,n,k,visited);
    }
    //计算数位和
    private int getSum(int num){
        int sum =0;
        while(num>0){
            sum+=num%10;
            num/=10;
        }
        return sum;
    }


}

//leetcode submit region end(Prohibit modification and deletion)


}


