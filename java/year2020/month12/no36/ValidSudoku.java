package year2020.month12.no36;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
//        correct
        char[][] board1 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
//        first nine borad have two 9
        char[][] board2 = new char[][]{
                {'9', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
//        first column have two 5
        char[][] board3 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'5', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
//        second row have two 1
        char[][] board4 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '1', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'5', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board1));
        System.out.println(isValidSudoku(board2));
        System.out.println(isValidSudoku(board3));
        System.out.println(isValidSudoku(board4));
    }

    private static boolean isValidSudoku(char[][] board) {
        int size = board.length;
        int[][] rows = new int[size][size];
        int[][] cols = new int[size][size];
        int[][] boxes = new int[size][size];
//        行表示第i个row、col、boxes集合
//        列表示第i个row、col、boxes集合中 1到9的元素是否出现过，0为未出现，1为出现过，用此数组代替Set/Map
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int value = board[i][j] - '1';
                int boxesIndex = (i / 3) * 3 + j / 3;
                if (rows[i][value] == 1 || cols[j][value] == 1 || boxes[boxesIndex][value] == 1) {
                    return false;
                }
                rows[i][value] = 1;
                cols[j][value] = 1;
                boxes[boxesIndex][value] = 1;
            }
        }
        return true;
    }

    public static boolean isValidSudoku2(char[][] board) {
        int size = board.length;
        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            rowSet.clear();
            colSet.clear();
            for (int j = 0; j < size; j++) {
                char rowElement = board[i][j];
                char colElement = board[j][i];
                if (rowElement != '.') {
                    if (rowSet.contains(rowElement)) {
                        return false;
                    }
                    rowSet.add(rowElement);
                }
                if (colElement != '.') {
                    if (colSet.contains(colElement)) {
                        return false;
                    }
                    colSet.add(colElement);
                }
            }
        }
        Set<Character> nineBoardSet = new HashSet<>();
        for (int i = 0; i < size; i += 3) {
            for (int j = 0; j < size; j += 3) {
//                (i,j) is the first element index of every nine board
                nineBoardSet.clear();
                for (int row_incre = 0; row_incre < 3; row_incre++) {
                    for (int col_incre = 0; col_incre < 3; col_incre++) {
                        char element = board[i + row_incre][j + col_incre];
                        if (element == '.') {
                            continue;
                        }
                        if (nineBoardSet.contains(element)) {
                            return false;
                        }
                        nineBoardSet.add(element);
                    }
                }
            }
        }
        return true;
    }
}

/*
* 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字1-9在每一行只能出现一次。
数字1-9在每一列只能出现一次。
数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。


上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用'.'表示。

示例1:

输入:
[
  ['5','3','.','.','7','.','.','.','.'],
  ['6','.','.','1','9','5','.','.','.'],
  ['.','9','8','.','.','.','.','6','.'],
  ['8','.','.','.','6','.','.','.','3'],
  ['4','.','.','8','.','3','.','.','1'],
  ['7','.','.','.','2','.','.','.','6'],
  ['.','6','.','.','.','.','2','8','.'],
  ['.','.','.','4','1','9','.','.','5'],
  ['.','.','.','.','8','.','.','7','9']
]
输出: true
示例2:

输入:
[
 ['8','3','.','.','7','.','.','.','.'],
 ['6','.','.','1','9','5','.','.','.'],
 ['.','9','8','.','.','.','.','6','.'],
 ['8','.','.','.','6','.','.','.','3'],
 ['4','.','.','8','.','3','.','.','1'],
 ['7','.','.','.','2','.','.','.','6'],
 ['.','6','.','.','.','.','2','8','.'],
 ['.','.','.','4','1','9','.','.','5'],
 ['.','.','.','.','8','.','.','7','9']
]
输出: false
解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
说明:

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
给定数独序列只包含数字1-9和字符'.'。
给定数独永远是9x9形式的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-sudoku
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
