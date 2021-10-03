package com.zdq.middle;

import com.zdq.entity.ListNode;
import com.zdq.util.GenerateSingleListUtil;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 */
public class ReorderList143 {

    public static void main(String[] args) {
        ListNode head = GenerateSingleListUtil.generate(5, 10);
        reOrderList(head);
        System.out.println(head);
    }
    private static void reOrderList(ListNode head) {
        //也可以使用数组随机访问去做
        //找到链表的中点：快慢指针
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow为中间节点,从这里开始反转后面部分的链表
        ListNode ln = slow.next;
        //分割链表
        slow.next = null;
        ListNode pre = null;
        while (ln != null) {
            ListNode tmp = ln.next;
            ln.next = pre;
            pre = ln;
            ln = tmp;
        }

        //接下来就是前后两部分交叉连接
        ListNode q = head;
        ListNode t1;
        ListNode t2;
        // 1->2->3
        // 6->5->4
        while (q != null && pre != null) {

            t1 = q.next;
            t2 = pre.next;

            q.next = pre;
            q = t1;

            pre.next = q;
            pre = t2;

        }

    }
}
