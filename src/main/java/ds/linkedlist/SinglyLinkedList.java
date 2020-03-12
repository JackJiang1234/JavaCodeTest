package ds.linkedlist;

import java.util.Arrays;

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

    public void clear() {
        this.head = null;
    }

    public void print() {
        this.printImpl(this.head);
    }

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
