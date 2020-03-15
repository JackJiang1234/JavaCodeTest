package ds.linkedlist;

/*
 * 我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表
 * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部
 * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：如果此时缓存未满，则将此结点直接插入到链表的头部；如果此时缓存已满，
 * 则链表尾结点删除，将新的数据结点插入链表的头部
 * */
public class LRUBasedLinkedList<T> {
    private final static Integer DEFAULT_CAPACTITY = 10;
    private Node<T> head;
    private Integer length;
    private Integer capacity;

    public LRUBasedLinkedList() {
        this(DEFAULT_CAPACTITY);
    }

    public LRUBasedLinkedList(Integer capacity) {
        this.capacity = capacity;
        this.head = new Node<>();
        this.length = 0;
    }

    public void add(T data) {
        Node pre = this.findPreNode(data);

        //链表中存在，删除原数据，再插入头部
        if (pre != null) {
            deleteNextNode(pre);
        } else if (this.length >= this.capacity) {
            deleteNodeAtEnd();
        }
        insertAtHead(data);
    }

    public static void main(String[] args) {
        LRUBasedLinkedList<String> stringLRUBasedLinkedList = new LRUBasedLinkedList<>();
        stringLRUBasedLinkedList.add("jack");
        stringLRUBasedLinkedList.add("eric");
        stringLRUBasedLinkedList.add("jiang");
        stringLRUBasedLinkedList.add("jack");
        stringLRUBasedLinkedList.add("fhu");
        stringLRUBasedLinkedList.print();
    }

    private void print(){
        Node cur = this.head.getNext();
        while (cur != null){
            System.out.print(cur.getElement() + ",");
            cur = cur.getNext();
        }
        System.out.println();
    }

    private void deleteNodeAtEnd() {
        Node cur = this.head;
        if (cur.getNext() == null) {
            return;
        }

        while (cur.getNext().getNext() != null) {
            cur = cur.getNext();
        }

        Node temp = cur.getNext();
        temp.setNext(null);
        length--;
    }

    private void insertAtHead(T data) {
        Node newNode = new Node(data, this.head.getNext());
        this.head.setNext(newNode);
        this.length++;
    }

    private Node findPreNode(T data) {
        Node cur = this.head;
        while (cur.getNext() != null) {
            if (data.equals(cur.getNext().getElement())) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    private void deleteNextNode(Node pre) {
        Node temp = pre.getNext();
        pre.setNext(temp.getNext());
        temp = null;
        length--;
    }

    private class Node<T> {
        private T element;
        private Node next;

        public Node(T e) {
            this.element = e;
        }

        public Node(T element, Node next) {
            this(element);
            this.next = next;
        }

        public Node() {
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }
}
