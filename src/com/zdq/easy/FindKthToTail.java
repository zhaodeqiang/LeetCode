package com.zdq.easy;

import com.zdq.entity.ListNode;

/**
 * 输入一个链表，输出该链表中倒数第k个结点
 */
public class FindKthToTail {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println(findKthToTail(head, 0));
        System.out.println(findKthToTail(head, 2));
    }

    private static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode p = head;
        ListNode q = head;
        int count = 0;
        int tmp = k;
        while (p != null) {
            p = p.next;
            count++;
            //p指针先跑，并且记录节点数，当p指针跑了k-1个节点后，q指针开始跑，
            if (tmp < 1) {
                q = q.next;
            }
            tmp--;
        }
        //如果节点个数小于k，则返回空
        if (count < k) {
            return null;
        }
        return q;
    }
}
