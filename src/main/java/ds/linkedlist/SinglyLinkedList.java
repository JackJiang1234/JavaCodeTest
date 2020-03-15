package ds.linkedlist;

import java.util.Arrays;

/*
单链表反转
链表中环的检测
两个有序的链表合并
删除链表倒数第 n 个结点
求链表的中间结点
LeetCode对应编号 206，141，21，19，876
*/
public class SinglyLinkedList {

    private Node head;

    public SinglyLinkedList() {
    }

    public void insertTail(int... vals) {
        for (int v : vals) {
            this.insertTail(v);
        }
    }

    public void insertTail(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            this.head = newNode;
        } else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(val);
        }
    }

    public Node middleNode(Node head) {
        Node slow, fast;
        slow = head;
        fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public Node removeNthFromEnd(Node head, int n) {
        Node first = head;
        while (n != 0) {
            first = first.next;
            n--;
        }

        //delete head
        if (first == null) {
            return head.next;
        }

        Node second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }

        second.next = second.next.next;
        return head;
    }

    public boolean hasCycle(Node head) {
        if (head == null) {
            return false;
        }

        Node slow, fast;
        slow = head;
        fast = head.next;
        while (slow != fast && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow == fast;
    }

    public Node mergeTwoOrderedLists(Node left, Node right) {
        Node leftCur = left;
        Node rightCur = right;
        Node sentinel = new Node(0);
        Node cur = sentinel;

        while (leftCur != null && rightCur != null) {
            if (leftCur.value <= rightCur.value) {
                cur.next = leftCur;
                leftCur = leftCur.next;
            } else {
                cur.next = rightCur;
                rightCur = rightCur.next;
            }
            cur = cur.next;
        }

        if (leftCur != null) {
            cur.next = leftCur;
        }
        if (rightCur != null) {
            cur.next = rightCur;
        }

        return sentinel.next;
    }

    public void clear() {
        this.head = null;
    }

    public void print() {
        this.printImpl(this.head);
    }

    /*使用三个指针翻转链表*/
    public Node inverse(Node node) {
        Node pre = null;
        Node cur = node;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    /*判断回文
     * 使用快慢指针
     * */
    public boolean palindrome() {
        if (this.head == null) {
            return false;
        }
        if (this.head.next == null) {
            return true;
        }

        Node slow, fast;
        slow = this.head;
        fast = this.head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Node left = this.head;
        Node right = this.inverse(slow.next);

        return matchOrInclude(left, right);
    }

    private boolean matchOrInclude(Node left, Node right) {
        Node p = left;
        Node q = right;
        boolean flag = false;
        while (p != null && q != null) {
            if (p.value == q.value) {
                p = p.next;
                q = q.next;
                flag = true;
            } else {
                flag = false;
                break;
            }
        }

        return flag;
    }

    private void printImpl(Node head) {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.getValue() + ",");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        testPalindrom(1);
        testPalindrom(1, 2, 1);
        testPalindrom(1, 2, 1, 2);
        testPalindrom(1, 2, 3, 4);
        testPalindrom(1, 2, 2, 1);
        testPalindrom(1, 2, 5, 2, 1);
    }

    private static void testPalindrom(int... arr) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.insertTail(arr);
        System.out.println(Arrays.toString(arr) + " is palindrom:" + linkedList.palindrome());
    }

    private void test() {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.insertTail(1, 2, 3, 4, 5);
        linkedList.print();

        linkedList.head = linkedList.inverse(linkedList.head);
        linkedList.print();
    }

    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            this(val, null);
        }

        public Node(int val, Node next) {
            this.value = val;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return this.next;
        }
    }
}
