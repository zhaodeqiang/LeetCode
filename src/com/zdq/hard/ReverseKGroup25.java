package com.zdq.hard;

import com.zdq.entity.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author ZDQ
 */
public class ReverseKGroup25 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
        int k = 3;
        System.out.println(reverseKGroup(listNode, k));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = dummy;

        while (right.next != null) {
            for (int i = 0; i < k && right != null; i++) {
                right = right.next;
            }
            if (right == null) {
                break;
            }
            ListNode start = left.next;
            ListNode next = right.next;
            right.next = null;
            left.next = reverse(start);
            // sonar提示start可能空指针
            start.next = next;
            left = start;
            right = left;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode left = head;
        ListNode right = head;
        while (right != null) {
            int n = k;
            while (n > 1 && right != null) {
                right = right.next;
                n--;
            }
            if (right == null) {
                break;
            }
            ListNode next = right.next;
            right.next = null;
            curr.next = reverse(left);
            left.next = next;
            right = next;
            curr = left;
            left = right;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        int count = 0;
        ListNode cur = head;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            cur = reverseKGroup1(cur, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
            }
            head = cur;
        }
        return head;
    }
}
