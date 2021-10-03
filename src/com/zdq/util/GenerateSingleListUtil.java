package com.zdq.util;

import com.zdq.entity.ListNode;

import java.util.Random;

public class GenerateSingleListUtil {

    private GenerateSingleListUtil() {}

    public static ListNode generate(int count, int bound) {
        if (count < 1 || bound < 1) {
            return null;
        }
        ListNode head = new ListNode(1);
        ListNode cur = head;
        while (count > 1) {
            int num = new Random().nextInt(bound) + 1;
            cur.next = new ListNode(num);
            cur = cur.next;
            count--;
        }
        System.out.println(head);
        return head;
    }
}
