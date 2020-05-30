package com.zdq.middle;

import com.zdq.entity.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author ZDQ
 */
public class SwapPairs24 {
    public static void main(String[] args) {

    }

    public static ListNode swapPairs(ListNode head) {
        ListNode listNode = new ListNode(0);
        ListNode curr = head;
        ListNode dump = listNode;
        while (curr != null && curr.next != null) {
            dump.next = curr.next;
            ListNode temp = curr.next.next;
            dump.next.next = curr;
            dump = curr;
            curr = temp;
        }
        dump.next = curr;
        return listNode.next;
    }

    /**
     * 翻转链表
     * @param head 头结点
     * @return 翻转后的链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
