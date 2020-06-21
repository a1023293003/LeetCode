package cn.bluesking.leetcode.solution;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;

/**
 * <h2>两数相加</h2>
 *
 * <div class="notranslate"><p>给出两个&nbsp;<strong>非空</strong> 的链表用来表示两个非负的整数。其中，它们各自的位数是
 * 按照&nbsp;<strong>逆序</strong>&nbsp;的方式存储的，并且它们的每个节点只能存储&nbsp;<strong>一位</strong>&nbsp;数字。
 * </p>
 *
 * <p>如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。</p>
 *
 * <p>您可以假设除了数字 0 之外，这两个数都不会以 0&nbsp;开头。</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>(2 -&gt; 4 -&gt; 3) + (5 -&gt; 6 -&gt; 4)
 * <strong>输出：</strong>7 -&gt; 0 -&gt; 8
 * <strong>原因：</strong>342 + 465 = 807
 * </pre>
 * </div>
 *
 * @author 随心
 * @date 2020/6/21
 */
@Problem(id = 2, title = "两数相加", url = "https://leetcode-cn.com/problems/add-two-numbers/")
public class AddTwoNumbers {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Solution(submitDate = "2020-06-21",
            executeTime = 2.0, executionTimeBeatRate = 99.89,
            memoryConsumption = 40, memoryConsumptionBeatRate = 94.26)
    public ListNode addTwoNumbersPrimary(ListNode l1, ListNode l2) {
        int addCarry = 0;
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + addCarry;
            node.next = new ListNode(sum % 10);
            addCarry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            node = node.next;
        }
        while (l1 != null) {
            int sum = l1.val + addCarry;
            node.next = new ListNode(sum % 10);
            addCarry = sum / 10;
            l1 = l1.next;
            node = node.next;
        }
        while (l2 != null) {
            int sum = l2.val + addCarry;
            node.next = new ListNode(sum % 10);
            addCarry = sum / 10;
            l2 = l2.next;
            node = node.next;
        }
        if (addCarry != 0) {
            node.next = new ListNode(addCarry);
        }
        return head.next;
    }

    @Solution(submitDate = "2020-06-21",
            executeTime = 2.0, executionTimeBeatRate = 99.89,
            memoryConsumption = 39.9, memoryConsumptionBeatRate = 94.26)
    public ListNode addTwoNumbersPretty(ListNode l1, ListNode l2) {
        int addCarry = 0;
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + addCarry;
            node.next = new ListNode(sum % 10);
            addCarry = sum / 10;
            if (l1 != null) { l1 = l1.next; }
            if (l2 != null) { l2 = l2.next; }
            node = node.next;
        }
        if (addCarry != 0) {
            node.next = new ListNode(addCarry);
        }
        return head.next;
    }

}
