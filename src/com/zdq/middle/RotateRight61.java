package com.zdq.middle;

import com.zdq.entity.ListNode;
import com.zdq.util.GenerateSingleListUtil;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class RotateRight61 {

    public static void main(String[] args) {
        ListNode head = GenerateSingleListUtil.generate(5, 10);
        ListNode listNode = rotateRight(head, 2);
        System.out.println(listNode);
    }

    private static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        //求出链表长度
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }

        //如果k是len的整数倍，直接返回头结点
        int remainder = len - k % len;
        if (remainder == len) {
            return head;
        }
        //将链表头尾节点相连，构成环形
        cur.next = head;
        while(remainder-- > 0) {
            cur = cur.next;
        }
        ListNode tmp = cur.next;
        cur.next = null;
        return tmp;
    }
}
