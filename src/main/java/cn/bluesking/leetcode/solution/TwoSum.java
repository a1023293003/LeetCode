package cn.bluesking.leetcode.solution;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>两数之和</h2>
 *
 * <p>给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。你可
 * 以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * <h3>示例：</h3>
 *
 * <pre>
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * </pre>
 *
 * @author 随心
 * @date 2020/6/20
 */
@Problem(id = 1, title = "两数之和", url = "https://leetcode-cn.com/problems/two-sum/")
public class TwoSum {

    @Solution(executeTime = 0, executionTimeBeatRate = 100.0,
            memoryConsumption = 39.3, memoryConsumptionBeatRate = 5.06)
    public int[] twoSumByArrayRef(int[] nums, int target) {
        // 必须是 2^n - 1
        int mod = 2047;
        int[] ref = new int[mod + 1];
        for (int i = 0, length = nums.length; i < length; i++) {
            int num = nums[i];
            int index = ref[(target - num) & mod];
            if (index != 0) {
                return new int[] { index - 1, i };
            }
            ref[num & mod] = i + 1;
        }
        return new int[0];
    }

    @Solution(executeTime = 2, executionTimeBeatRate = 99.59,
            memoryConsumption = 39.9, memoryConsumptionBeatRate = 5.06)
    public int[] twoSumByMap(int[] nums, int target) {
        Map<Integer, Integer> indexRef = new HashMap<>(nums.length);
        indexRef.put(nums[0], 0);
        for (int i = 1, length = nums.length; i < length; i ++) {
            Integer num = nums[i];
            Integer index = indexRef.get(target - nums[i]);
            if (index != null) {
                return new int[] { index, i };
            }
            indexRef.put(num, i);
        }
        return new int[0];
    }

}