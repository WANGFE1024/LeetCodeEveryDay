package year2021.month1.no10;

public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";
        String s2 = "aa";
        String p2 = "a*";
        String s3 = "ab";
        String p3 = ".*";
        String s4 = "aab";
        String p4 = "c*a*b";
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        System.out.println(isMatch(s1, p1));
        System.out.println(isMatch(s2, p2));
        System.out.println(isMatch(s3, p3));
        System.out.println(isMatch(s4, p4));
        System.out.println(isMatch(s5, p5));
    }

    public static boolean isMatch(String s, String p) {
        /* DP, time is O(nm), space is O(nm)
         * 状态定义：
         * dp[i][j]为s的前i个字符与p的前j个字符是否匹配
         * 状态转移方程：
         * if p[j]是小写字母，则
         *   dp[i][j] = s[i] == p[j] ? dp[i-1][j-1] : false
         * else if p[j]是 "."，则
         *   dp[i][j] == dp[i-1][j-1]
         * else p[j]是 "*"，则
         *   if s[i] != p[j-1]，则 *无用
         *       dp[i][j] = dp[i][j-2]
         *   else s[i] == p[j-1]，则 *有用
         *       dp[i][j] = dp[i][j-2] || dp[i-1][j]
         *                   匹配零个        匹配多个
         * 初始值：
         * 空串与空串相匹配
         * dp[0][0] = true
         * s串不为空，p串为空时必不匹配
         * dp[i][0] = false,
         * 首先，题目有 - 保证每次出现字符 * 时，前面都匹配到有效的字符， 即*不会打头，也不会连续出现，则
         * s串为空，若p[j] = '*'，则dp[0][j] = dp[0][j-2]
         * dp[0][j] = p[j] == '*' && dp[0][j-2]
         * */
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            int pIndex = j - 1;
            dp[0][j] = p.charAt(pIndex) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                int sIndex = i - 1;
                int pIndex = j - 1;
                if (p.charAt(pIndex) == '.' || s.charAt(sIndex) == p.charAt(pIndex)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(pIndex) == '*') {
                    if (s.charAt(sIndex) == p.charAt(pIndex - 1) || p.charAt(pIndex - 1) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}

/*
* 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

 
示例 1：

输入：s = "aa" p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
示例 2:

输入：s = "aa" p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3：

输入：s = "ab" p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4：

输入：s = "aab" p = "c*a*b"
输出：true
解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5：

输入：s = "mississippi" p = "mis*is*p*."
输出：false
 

提示：

0 <= s.length <= 20
0 <= p.length <= 30
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
保证每次出现字符 * 时，前面都匹配到有效的字符

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
