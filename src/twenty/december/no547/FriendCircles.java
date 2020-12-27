package twenty.december.no547;

public class FriendCircles {
    public static void main(String[] args) {
        int[][] M1 = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int[][] M2 = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        int[][] M3 = new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        System.out.println(findCircleNumByDFS(M1));
        System.out.println(findCircleNumByDFS(M2));
        System.out.println(findCircleNumByDFS(M3));
    }

    private static int findCircleNumByDFS(int[][] M) {
//        DFS, 将 M看为邻阶矩阵，则相当于在 M.length个节点中，找连通数
        int count = 0;
        boolean[] visited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (!visited[j] && M[i][j] == 1) {
                dfs(M, visited, j);
            }
        }
    }

    public static int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFind(int[][] M) {
        count = 0;
        parent = new int[M.length];
        rank = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            parent[i] = i;
            count++;
        }
    }

    public int find(int i) {
        return parent[i] == i ? i : find(parent[i]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        count--;
    }

    public int getCount() {
        return count;
    }
}

/*
* 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

 

示例 1：

输入：
[[1,1,0],
 [1,1,0],
 [0,0,1]]
输出：2 
解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回 2 。
示例 2：

输入：
[[1,1,0],
 [1,1,1],
 [0,1,1]]
输出：1
解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
 

提示：

1 <= N <= 200
M[i][i] == 1
M[i][j] == M[j][i]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/friend-circles
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
