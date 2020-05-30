package com.zdq.easy;

import com.zdq.entity.ListNode;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author ZDQ
 */
public class MergeTwoLists21 {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode listNode = new ListNode(0);
        ListNode dump = listNode;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val > temp2.val) {
                dump.next = temp2;
                temp2 = temp2.next;
            } else {
                dump.next = temp1;
                temp1 = temp1.next;
            }
            dump = dump.next;
        }
        if (temp1 == null) {
            dump.next = temp2;
        }
        if (temp2 == null) {
            dump.next = temp1;
        }
        return listNode.next;
    }
}