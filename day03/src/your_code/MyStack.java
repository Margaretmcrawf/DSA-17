package your_code;

import ADTs.StackADT;

import java.util.EmptyStackException;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {
    private Node head;
    private Node tail;
    private int size;
    private Node max;

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

    public MyStack() {
        head = null;
        tail=null;
        size=0;
    }


    @Override
    public void push(Integer e) {
        if (isEmpty()) {
            Node newn = new Node(e, null, null);
            tail = newn;
            head = newn;
            max = newn;
        } else {
            Node newn = new Node(e, tail, null);
            tail.next = newn;
            tail = newn;
            if (e >= max.val) {
                Node newmax = new Node(e, max, null);
                max.next = newmax;
                max = max.next;
            }
        }
        size++;
    }

    @Override
    public Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else if (size == 1) {
            Integer popped = tail.val;
            tail = null;
            head = null;
            size--;
            return popped;
        }
        else {
            Integer popped = tail.val;
            tail = tail.prev;
            tail.next = null;
            size--;
            if (popped == max.val) {
                max = max.prev;
                max.next = null;
            }
            return popped;
        }

    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Integer peek() {
        return tail.val;
    }

    public Integer maxElement() {
        return max.val;
    }
}
