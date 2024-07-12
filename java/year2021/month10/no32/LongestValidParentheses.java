package year2021.month10.no32;

public class LongestValidParentheses {

    public static void main(String[] args) {
        String s1 = "(()";
        String s2 = ")()())";
        String s3 = "";
        System.out.println(longestValidParentheses(s1));
        System.out.println(longestValidParentheses(s2));
        System.out.println(longestValidParentheses(s3));
    }

    public static int longestValidParentheses(String s) {
        /*
         * DP, time is O(n), space is O(n)
         * 状态定义：
         * dp[i] 表示以当前 i 结尾的字符串中最长有效括号子串的长度
         * 状态转义方程：
         * if s[i] = '('
         *       dp[i] = 0
         * else s[i] = ')' && i - dp[i-1] - 1 >= 0 && s[i - dp[i-1] - 1] == '('
         *       dp[i] = dp[i-1] + 2
         * 注意：
         *   若当前已配对成功，当前范围的前一个元素也能配对成功（dp[i - dp[i-1] - 2]存在且大于0），则还需加上前面的长度
         *   因i - dp[i-1] - 2配对不成功时，dp[i - dp[i-1] - 2] = 0，则可统一处理
         *   if i - dp[i-1] -2 >= 0
         *       dp[i] += dp[i - dp[i-1] -2]
         * 例子： ()(())
         *        ^   ^
         *        2 + 4 = 6
         * */
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2;
                if (i - dp[i - 1] - 2 >= 0) {
                    dp[i] += dp[i - dp[i - 1] - 2];
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}

/*
* 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

 

示例 1：

输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"
示例 2：

输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"
示例 3：

输入：s = ""
输出：0
 

提示：

0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
