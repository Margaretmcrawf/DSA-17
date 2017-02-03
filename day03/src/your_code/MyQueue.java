package your_code;

import ADTs.QueueADT;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Integer val;
        Node prev;
        Node next;

        private Node (Integer d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyQueue() {
        head = null;
        tail=null;
        size=0;
    }

    @Override
    public void enqueue(Integer item) {
        if (isEmpty()) {
            Node newn = new Node(item, null, null);
            head = newn;
            tail = newn;
        } else {
            Node newn = new Node(item, tail, null);
            tail.next = newn;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public Integer dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty queue");
        } else if (size == 1) {
            Integer de = head.val;
            head = null;
            tail = null;
            size--;
            return de;
        } else {
            Integer de = head.val;
            head = head.next;
            head.prev = null;
            size--;
            return de;
        }
    }



    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Integer front() {
        return head.val;
    }
}