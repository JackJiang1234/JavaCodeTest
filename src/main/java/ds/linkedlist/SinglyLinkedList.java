package ds.linkedlist;

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
        Node slow, fast;


        return false;
    }

    private void printImpl(Node head) {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.getValue() + ",");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
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
