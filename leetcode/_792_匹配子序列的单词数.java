package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定字符串 s 和字符串数组 words, 返回 words[i] 中是s的子序列的单词个数 。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 * 示例 1:
 *
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 *
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 * 提示:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 *
 * Related Topics
 * 字典树
 * 哈希表
 * 字符串
 * 排序
 *
 */

public class _792_匹配子序列的单词数 {
    List<Integer>[] cnt = new List[26];
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length();
        int len = words.length;
        for (int i = 0; i < 26; i++) {
            cnt[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++){
            cnt[s.charAt(i) - 'a'].add(i);
        }
        int res = 0;
        for(String w : words){
            if(cal(w)){
                res++;
            }
        }
        return res;
    }

    boolean cal(String s){
        int now = -1;
        int n = s.length();
        for(int i = 0; i < n; i++){
            int c = s.charAt(i) - 'a';
            int pos = binsearch(cnt[c], now);
            if(pos == cnt[c].size()){
                return false;
            }
            now = cnt[c].get(pos);
        }
        return true;
    }

    int binsearch(List<Integer> nums, int target){
        int len = nums.size();
        int left = 0;
        int right = len;
        while(left < right){
            int mid = left + (right - left) / 2;
            // 寻找第一个大于target的数
            if(nums.get(mid) <= target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}

/**
 * 先用数组或哈希表 cnt 存放字符串 s 每个字符的下标，即 cnt[c] 为 s 中所有字符 c 的下标数组。
 *
 * 然后我们遍历 words中的每个单词 w，我们通过二分查找的方法，判断 w 是否为 s 的子序列，是则答案加 1。
 * 判断逻辑如下：
 * 定义指针 now 表示当前指向字符串 s 的第 now 个字符，初始化为 −1。
 * 遍历字符串 w 中的每个字符 c，在 cnt[c] 中二分查找第一个大于 now 的位置 pos，
 *      如果不存在，则说明 w 不是 s 的子序列，直接跳出循环；
 *      否则，将 now 更新为 cnt[c].get(pos)，继续遍历下一个字符。
 * 如果遍历完 w 中的所有字符，说明 w 是 s 的子序列。
 *
 * 二分查找法：找到第一个大于target的数
 * left = 0；
 * right = len;  // 数组长度
 * 因为当数组中不存在比target大的数时，left会一直右移，直到超出数组下标边界，此时left==right==len;
 * 由于是查找严格大于target的数，所以当 target == nums[mid] 时，left = mid + 1
 */
