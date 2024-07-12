package year2021.month1.no49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        disp(groupAnagrams4(strs1));
    }

    private static List<List<String>> groupAnagrams4(String[] strs) {
        // str -> split -> stream -> sort -> join
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> Arrays.stream(str.split("")).sorted().collect(Collectors.joining()))).values());
    }

    private static List<List<String>> groupAnagrams3(String[] strs) {
        // str -> intstream -> sort -> collect by StringBuilder
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> str.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
    }

    private static List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    int[] set = new int[26];
                    for (int i = 0; i < str.length(); i++) {
                        set[str.charAt(i) - 'a']++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 26; i++) {
                        // 这里的 if 是可省略的，但是加上 if 以后，生成的 sb 更短，后续 groupingBy 会更快。
                        if (set[i] != 0) {
                            sb.append((char) ('a' + i));
                            sb.append(set[i]);
                        }
                    }
                    return sb.toString();
                })).values());
    }

    private static List<List<String>> groupAnagrams1(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                })).values());
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        int[][] alphaSet = new int[strs.length][26];
        int[] sizes = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            boolean isNewGroup = true;
            for (int j = 0; j < result.size(); j++) {
                if (str.length() != sizes[j]) {
                    continue;
                }
                if (isAnagrams(alphaSet, str, j)) {
                    result.get(j).add(str);
                    isNewGroup = false;
                    break;
                }
            }
            if (isNewGroup) {
                int index = result.size();
                List<String> list = new LinkedList<>();
                list.add(str);
                result.add(list);
                sizes[index] = str.length();
                for (char c : str.toCharArray()) {
                    alphaSet[index][c - 'a']++;
                }
            }
        }
        return result;
    }

    private static boolean isAnagrams(int[][] alphaSet, String str, int index) {
        int[] set = Arrays.copyOf(alphaSet[index], alphaSet[index].length);
        for (char c : str.toCharArray()) {
            set[c - 'a']--;
        }
        return IntStream.of(set).allMatch(num -> num == 0);
    }

    private static void disp(List<List<String>> result) {
        if (result == null || result.size() == 0) {
            System.out.println("EMPTY");
            return;
        }
        System.out.println("[");
        result.forEach(System.out::println);
        System.out.println("]");
    }
}

/*
* 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/group-anagrams
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
