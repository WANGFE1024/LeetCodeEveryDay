package year2021.month10.no1109;

import java.util.Arrays;

public class CorporateFlightBookings {

    public static void main(String[] args) {
        int[][] bookings1 = {
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        };
        int n1 = 5;
        int[][] bookings2 = {
                {1, 2, 10},
                {2, 2, 15}
        };
        int n2 = 2;
        System.out.println(Arrays.toString(corpFlightBookings(bookings1, n1)));
        System.out.println(Arrays.toString(corpFlightBookings(bookings2, n2)));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        /*
         * 差分数组
         * diff[i] = nums[i] - nums[i-1]
         * */
        int[] diff = new int[n];
        for (int[] booking : bookings) {
            // 转换为索引
            int firstAir = booking[0] - 1;
            int lastAir = booking[1] - 1;
            int seatCount = booking[2];
            diff[firstAir] += seatCount;
            if (lastAir + 1 < n) {
                diff[lastAir + 1] -= seatCount;
            }
        }
        int[] ans = new int[n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += diff[i];
            ans[i] = cur;
        }
        return ans;
    }

}

/*
* 这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。

 

示例 1：

输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
输出：[10,55,45,25,25]
解释：
航班编号        1   2   3   4   5
预订记录 1 ：   10  10
预订记录 2 ：       20  20
预订记录 3 ：       25  25  25  25
总座位数：      10  55  45  25  25
因此，answer = [10,55,45,25,25]
示例 2：

输入：bookings = [[1,2,10],[2,2,15]], n = 2
输出：[10,25]
解释：
航班编号        1   2
预订记录 1 ：   10  10
预订记录 2 ：       15
总座位数：      10  25
因此，answer = [10,25]
 

提示：

1 <= n <= 2 * 104
1 <= bookings.length <= 2 * 104
bookings[i].length == 3
1 <= firsti <= lasti <= n
1 <= seatsi <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/corporate-flight-bookings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
