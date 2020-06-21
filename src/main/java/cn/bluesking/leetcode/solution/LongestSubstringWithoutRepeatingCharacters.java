package cn.bluesking.leetcode.solution;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;
import lombok.extern.slf4j.Slf4j;

/**
 * <h2>无重复字符的最长子串</h2>
 *
 * <div class="notranslate"><p>给定一个字符串，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。
 * </p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>"abcabcbb"
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>"bbbbb"
 * <strong>输出: </strong>1
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>"pwwkew"
 * <strong>输出: </strong>3
 * <strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>"wke"</code>，所以其长度为 3。
 * &nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code>&nbsp;是一个<em>子序列，</em>不是子串。
 * </pre>
 * </div>
 *
 * @author 随心
 * @date 2020/6/21
 */
@Problem(id = 3, title = "无重复字符的最长子串",
        url = "https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/")
public class LongestSubstringWithoutRepeatingCharacters {

    private int max(int left, int right) {
        return left > right ? left : right;
    }

    @Solution(submitDate = "2020-06-21",
            executeTime = 10.0, executionTimeBeatRate = 43.18,
            memoryConsumption = 40.2, memoryConsumptionBeatRate = 5.20)
    public int lengthOfLongestSubstringPrimary(String s) {
        int count = 0;
        int maxCount = 0;
        int[] ref = new int[256];
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i ++) {
            char c = chars[i];
            if (ref[c] == 0) {
                ref[c] = i + 1;
                count ++;
            } else if (ref[c] != 0) {
                maxCount = max(count, maxCount);
                count = 0;
                i = ref[c] - 1;
                ref = new int[256];
            }
        }
        return max(count, maxCount);
    }

    @Solution(submitDate = "2020-06-21",
            executeTime = 2.0, executionTimeBeatRate = 100.0,
            memoryConsumption = 39.7, memoryConsumptionBeatRate = 5.54)
    public int lengthOfLongestSubstring(String s) {
        int count = 0;
        int maxCount = 0;
        int[] ref = new int[256];
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int tail = 0, head = 0; tail < length; tail ++) {
            char c = chars[tail];
            if (ref[c] == 0) {
                ref[c] = tail + 1;
                count ++;
            } else if (ref[c] != 0) {
                maxCount = max(count, maxCount);
                count = tail - ref[c] + 1;
                while (head < ref[c]) {
                    ref[chars[head ++]] = 0;
                }
                ref[c] = tail + 1;
            }
        }
        return max(count, maxCount);
    }

}
