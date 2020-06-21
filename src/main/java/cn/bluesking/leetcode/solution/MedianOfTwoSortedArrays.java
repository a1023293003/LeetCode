package cn.bluesking.leetcode.solution;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;

/**
 * <h2>寻找两个正序数组的中位数</h2>
 *
 * <div class="notranslate"><p>给定两个大小为 m 和 n 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2
 * </code>。</p>
 *
 * <p>请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为&nbsp;O(log(m + n))。</p>
 *
 * <p>你可以假设&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;不会同时为空。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 * </pre>
 * </div>
 *
 * @author 随心
 * @date 2020/6/21
 */
@Problem(id = 4, title = "寻找两个正序数组的中位数",
        url = "https://leetcode-cn.com/problems/median-of-two-sorted-arrays/")
public class MedianOfTwoSortedArrays {

    /* ==========================================================================*/

    @Solution(submitDate = "2020-06-21",
            executeTime = 3.0, executionTimeBeatRate = 61.33,
            memoryConsumption = 40.7, memoryConsumptionBeatRate = 100.0)
    public double findMedianSortedArraysPrimary(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // 确保 nums1.length <= nums2.length
        if (n > m) {
            int[] tmp = nums1; nums1 = nums2; nums2 = tmp;
            n ^= m; m ^= n; n ^= m;
        }
        // 4 -> [1, 2]、5 -> [3]
        int halfLen = (n + m + 1) >> 1;
        boolean isOdd = ((n + m) & 0x1) == 1;
        // 处理包含空数组输入的情况
        if (nums1.length == 0) {
            return isOdd ? nums2[halfLen - 1] : (nums2[halfLen] + nums2[halfLen - 1]) / 2.0;
        } else if (nums1[n - 1] <= nums2[0]) {
            // nums1 全部在 nums2 左边的情况
            if (n < halfLen) {
                int tmp = halfLen - nums1.length;
                return isOdd ? nums2[tmp - 1] : (nums2[tmp] + nums2[tmp - 1]) / 2.0;
            } else if (n == halfLen) {
                return isOdd ? nums1[n - 1] : (nums1[n - 1] + nums2[0]) / 2.0;
            } else {
                return isOdd ? nums1[halfLen - 1] : (nums1[halfLen] + nums1[halfLen - 1]) / 2.0;
            }
        } else if (nums2[m - 1] <= nums1[0]) {
            // nums1 全部在 nums2 右边的情况
            if (m < halfLen) {
                int tmp = halfLen - nums2.length;
                return isOdd ? nums1[tmp - 1] : (nums1[tmp] + nums1[tmp - 1]) / 2.0;
            } else if (m == halfLen) {
                return isOdd ? nums2[m - 1] : (nums2[m - 1] + nums1[0]) / 2.0;
            } else {
                return isOdd ? nums2[halfLen - 1] : (nums2[halfLen] + nums2[halfLen - 1]) / 2.0;
            }
        } else {
            int s1 = 0;
            int e1 = n;
            while (s1 <= e1) {
                int i = s1 + ((e1 - s1) >> 1);
                int j = halfLen - i;
                if (i > 0 && j < m && nums1[i - 1] > nums2[j]) {
                    e1 = i;
                } else if (j > 0 && i < n && nums2[j - 1] > nums1[i]) {
                    s1 = i + 1;
                } else {
                    int leftMax = i <= 0 ? nums2[j - 1] : Math.max(nums1[i - 1], nums2[j - 1]);
                    if (isOdd) {
                        return leftMax;
                    } else {
                        int rightMin = i >= n ? nums2[j] : Math.min(nums1[i], nums2[j]);
                        return (leftMax + rightMin) / 2.0;
                    }
                }
            }
            return -1;
        }
    }

    /* ==========================================================================*/

    @Solution(submitDate = "2020-06-21",
            executeTime = 2.0, executionTimeBeatRate = 100.0,
            memoryConsumption = 41.1, memoryConsumptionBeatRate = 100.0)
    public double findMedianSortedArraysSecond(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // 确保 nums1.length <= nums2.length
        if (n > m) {
            int[] tmp = nums1; nums1 = nums2; nums2 = tmp;
            n ^= m; m ^= n; n ^= m;
        }
        // 4 -> [1, 2]、5 -> [3]
        int halfLen = (n + m + 1) >> 1;
        boolean isOdd = ((n + m) & 0x1) == 1;
        // 处理包含空数组输入的情况
        if (nums1.length == 0) {
            return isOdd ? nums2[halfLen - 1] : (nums2[halfLen] + nums2[halfLen - 1]) / 2.0;
        } else {
            int s1 = 0;
            int e1 = n;
            while (s1 <= e1) {
                int i = s1 + ((e1 - s1) >> 1);
                int j = halfLen - i;
                if (i > 0 && j < m && nums1[i - 1] > nums2[j]) {
                    e1 = i;
                } else if (j > 0 && i < n && nums2[j - 1] > nums1[i]) {
                    s1 = i + 1;
                } else {
                    int leftMax = i <= 0 ? nums2[j - 1] : j <= 0 ?
                            nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]);
                    if (isOdd) {
                        return leftMax;
                    } else {
                        int rightMin = i >= n ? nums2[j] : j >= m ?
                                nums1[i] : Math.min(nums1[i], nums2[j]);
                        return (leftMax + rightMin) / 2.0;
                    }
                }
            }
            return -1;
        }
    }

    /* ==========================================================================*/

    @Solution(submitDate = "2020-06-21",
            executeTime = 2.0, executionTimeBeatRate = 100.0,
            memoryConsumption = 41.0, memoryConsumptionBeatRate = 100.0)
    public double findMedianSortedArraysPretty(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        if (left == right) {
            return findKth(nums1, 0, nums2, 0, left);
        } else {
            return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
        }
    }


    public int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        // i: nums1的起始位置 j: nums2的起始位置
        // nums1 为空数组
        if( i >= nums1.length) return nums2[j + k - 1];
        // nums2 为空数组
        if( j >= nums2.length) return nums1[i + k - 1];
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }

}
