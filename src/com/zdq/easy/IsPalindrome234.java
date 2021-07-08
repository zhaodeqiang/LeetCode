package com.zdq.easy;

import com.zdq.entity.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @author ZDQ
 */
public class IsPalindrome234 {
    private static ListNode left;

    public static void main(String[] args) {

    }


    private static boolean isPalindrome(ListNode head) {
        left = head;
        //递归解法
        return traverse(head);
    }

    private static boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    private static boolean isPalindrome2(ListNode head) {
        //优化空间复杂度  双指针解法
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow 指针现在指向链表中点
        // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步：
        if (fast != null) {
            slow = slow.next;
        }
        // 1->2->3->4->3->2->1->NULL
        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
