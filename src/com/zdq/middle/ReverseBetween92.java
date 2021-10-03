package com.zdq.middle;

import com.zdq.entity.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author ZDQ
 */
public class ReverseBetween92 {
    private ListNode processor;

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        int m = 2;
        int n = 4;
        ListNode node = reverseBetween2(listNode, m, n);
        System.out.println(node);
    }

    /**
     * 递归解法，反转部分链表
     *
     * @param head 头结点
     * @param m    起始序号
     * @param n    结束序号
     * @return 翻转后的链表
     */
    private ListNode reverseBetween(ListNode head, int m, int n) {
        if (head != null) {
            if (m == 1) {
                return reverseN(head, n);
            }
            head.next = reverseBetween(head.next, m - 1, n - 1);
        }
        return head;
    }

    /**
     * 反转链表的前n个节点
     *
     * @param head 头结点
     * @param n    前n个节点
     * @return 反转后的链表
     */
    private ListNode reverseN(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (n == 1) {
            processor = head.next;
            return head;
        }
        ListNode reverseN = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = processor;
        return reverseN;
    }

    /**
     * 头插法 迭代，反转部分链表
     *
     * @param head 头结点
     * @param m    起始序号
     * @param n    结束序号
     * @return 翻转后的链表
     */
    private static ListNode reverseBetween2(ListNode head, int m, int n) {

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode q = dummy.next = head;
        for (int i = 0; i < m - 1; i++) {
             p = p.next;
             q = q.next;
        }

        // 1->2->3->4->5->NULL
        // 1  2->4->5
        //    |
        //    3
        for (int i = 0; i < n - m; i++) {
            ListNode tmp = q.next;
            q.next = q.next.next;

            tmp.next = p.next;
            p.next = tmp;

        }

        return dummy.next;
    }
}
