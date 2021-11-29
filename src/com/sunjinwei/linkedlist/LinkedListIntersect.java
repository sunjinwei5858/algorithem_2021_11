package com.sunjinwei.linkedlist;

import java.util.Stack;

/**
 * @program: com.sunjinwei.linkedlist
 * @author: sun jinwei
 * @create: 2021-11-29 11:20
 * @description: 返回两个链表相交的第一个节点
 * ps：
 * 1。边界问题：如果两个链表不相交 返回null，所以这里需要先判断会不会相交
 **/
public class LinkedListIntersect {

    public class Node {
        public int val;
        public Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // 方法1
    public Node linkedListIntersect01(Node p1, Node p2) {
        // 1 判断会不会相交
        int len1 = 0;
        int len2 = 0;
        Node node1 = p1;
        Node node2 = p2;
        while (node1 != null && node1.next != null) {
            node1 = node1.next;
            len1++;
        }
        while (node2 != null && node2.next != null) {
            node2 = node2.next;
            len2++;
        }
        if (node1 != node2) {
            return null;
        }
        // 2 寻找第一个相交的交点
        node1 = p1;
        node2 = p2;
        if (len1 > len2) {
            // p1 先走(len1-len2)步
            for (int i = 0; i < (len1 - len2); i++) {
                node1 = node1.next;
            }
        } else if (len1 < len2) {
            // p2 先走(len2-len1)步
            for (int i = 0; i < (len2 - len1); i++) {
                node2 = node2.next;
            }
        }
        // 此时让node1和node2一起走
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    // 方法2：使用栈 从尾巴节点开始判断
    public Node linkedListIntersect02(Node p1, Node p2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        while (p1 != null) {
            stack1.push(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2);
            p2 = p2.next;
        }
        Node pre = null;
        while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()) {
            pre = stack1.pop();
            stack2.pop();
        }
        return pre;
    }

    // 方法3：使用规律
    // 通过画图 链表a的长度为a,链表a走了x步到第一个交点；链表b的长度为b，走了y步到第一个交点
    // a-x = b-y ==> a+y = b+x
    // 所以可以让链表a走到尾巴 然后从链表b开始走；链表b走到尾巴，从链表a开始走，那么一定会相交
    public Node linkedListIntersect03(Node p1, Node p2) {

        while (p1 != p2) {
            p1 = p1 == null ? p2 : p1.next;
            p2 = p2 == null ? p1 : p2.next;
        }
        return p1;
    }


}