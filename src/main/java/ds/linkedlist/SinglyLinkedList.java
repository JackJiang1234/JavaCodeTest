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

    private ListNode head;

    public SinglyLinkedList() {
    }

    public void insertTail(int... vals) {
        for (int v : vals) {
            this.insertTail(v);
        }
    }

    public void insertTail(int val) {
        ListNode newListNode = new ListNode(val);
        if (head == null) {
            this.head = newListNode;
        } else {
            ListNode cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(val);
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow, fast;
        slow = head;
        fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        while (n != 0) {
            first = first.next;
            n--;
        }

        //delete head
        if (first == null) {
            return head.next;
        }

        ListNode second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }

        second.next = second.next.next;
        return head;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow, fast;
        slow = head;
        fast = head.next;
        while (slow != fast && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow == fast;
    }

    public ListNode mergeTwoOrderedLists(ListNode left, ListNode right) {
        ListNode leftCur = left;
        ListNode rightCur = right;
        ListNode sentinel = new ListNode(0);
        ListNode cur = sentinel;

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
    public ListNode inverse(ListNode listNode) {
        ListNode pre = null;
        ListNode cur = listNode;
        ListNode next;
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

        ListNode slow, fast;
        slow = this.head;
        fast = this.head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode left = this.head;
        ListNode right = this.inverse(slow.next);

        return matchOrInclude(left, right);
    }

    private boolean matchOrInclude(ListNode left, ListNode right) {
        ListNode p = left;
        ListNode q = right;
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

    private void printImpl(ListNode head) {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.getValue() + ",");
            cur = cur.next;
        }
    }

    public ListNode swapPairsUseRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairsUseRecursion(second.next);
        second.next = first;

        return second;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while ((head != null) && (head.next != null)) {

            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; // jump
        }

        // Return the new head node.
        return dummy.next;
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

    public static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int val) {
            this(val, null);
        }

        public ListNode(int val, ListNode next) {
            this.value = val;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public ListNode getNext() {
            return this.next;
        }
    }
}
