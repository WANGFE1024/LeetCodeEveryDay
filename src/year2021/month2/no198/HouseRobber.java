package year2021.month2.no198;

public class HouseRobber {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 1};
        int[] nums2 = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob(nums1));
        System.out.println(rob(nums2));

    }

    public static int rob1(int[] nums) {
        /*DP,
         * 状态定义：
         * 定义p为不偷当前房间,q为偷当前房间
         * 状态转移方程：
         * p = max{lastP, lastQ}
         * q = lastP + nums[i]
         * */
        if (nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int lastP = p;
            int lastQ = q;
            p = Math.max(lastP, lastQ);
            q = lastP + nums[i];
        }
        return Math.max(p, q);
    }

    public static int rob(int[] nums) {
        /*DP,
         * 状态定义：
         * 定义dp[i][0]为不偷第i-1个房间,dp[i][1]为偷第i-1个房间
         * 状态转移方程：
         * dp[i][0] = max{dp[i-1][0], dp[i-1][1]}
         * dp[i][1] = dp[i-1][0] + nums[i]
         * */
        if (nums.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}

/*
* 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
* 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
* 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你
* 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 

示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2：

输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 

提示：

0 <= nums.length <= 100
0 <= nums[i] <= 400

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
