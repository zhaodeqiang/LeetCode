package com.zdq.hard;


import com.zdq.entity.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author ZDQ
 */
public class MergeKLists23 {
    public static void main(String[] args) {

    }

    private ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int interval = 1;
        int count = lists.length;
        while (interval < count) {
            for (int i = 0; i < count - interval; i += interval << 1) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval = interval << 1;
        }
        return lists[0];
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                curr.next = l2;
                l2 = l2.next;
            } else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        if (l1 == null) {
            curr.next = l2;
        }
        if (l2 == null) {
            curr.next = l1;
        }
        return dummy.next;
    }
}