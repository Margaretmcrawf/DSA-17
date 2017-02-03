package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
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

    public MyPriorityQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(int item) {
        if (size == 0) {
            Node n = new Node(item, null, null);
            head = n;
            tail = n;
        }
        else  {
            Node n = new Node(item, tail, null);
            tail.next = n;
            tail = tail.next;
        }
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        Node pointer = head;
        Node max = pointer;
        while (pointer != null) {
            if (pointer.val > max.val) {
                max = pointer;
            }
            pointer = pointer.next;
        }
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return max.val;
        } else if (max == head) {
            max.next.prev = null;
            head = head.next;
            size--;
            return max.val;
        } else if (max == tail) {
            max.prev.next = null;
            tail = tail.prev;
            size--;
            return max.val;
        } else {
            max.next.prev = max.prev;
            max.prev.next = max.next;
            size--;
            return max.val;
        }


    }

}