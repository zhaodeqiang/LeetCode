package com.zdq.middle;

import com.zdq.entity.ListNode;

import java.util.HashSet;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：
 * <p>
 * 你是否可以使用 O(1) 空间解决此题？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 */
public class DetectCycle142 {
    public static void main(String[] args) {

    }


    private ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            // fast 指针走过的距离都为slow 指针的 2 倍
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // 设链表中环外部分的长度为 a; slow 指针进入环后，又走了 b 的距离与 fast 相遇
            // a+n(b+c)+b = a+(n+1)b+nc = 2(a+b) => a=c+(n−1)(b+c)
            // 即：从相遇点到入环点的距离加上n−1 圈的环长，恰好等于从链表头部到入环点的距离。
            if (fast == slow) {
                // 当发现slow 与 fast 相遇时，我们再额外使用一个指针ptr。起始，它指向链表头部；
                // 随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }


    private ListNode detectCycleByHash(ListNode head) {
        ListNode pos = head;
        HashSet<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
}
