public class MyLinkedList <T>{

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node (T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail=null;
		size=0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
		if (isEmpty()) {
			Node cNode = new Node(c, null, null);
			head = cNode;
			tail = cNode;
		} else {
			Node cNode = new Node(c, tail, null);
			tail.next = cNode;
			tail = cNode;
		}
		size++;
	}

	public void addFirst(T c) {
		if (isEmpty()) {
			Node cNode = new Node(c, null, null);
			head = cNode;
			tail = cNode;
		} else {
			Node cNode = new Node(c, null, head);
			head.prev = cNode;
			head = cNode;
		}
		size++;
	}

	public T get(int index) {
		if (index == size-1) {
			return tail.val;
		}
		if (index > size/2) {
			Node returnNode = tail;
			for (int i = 0; i < size - index; i++) {
				returnNode = returnNode.prev;
			}
			return returnNode.val;
		} else {
			Node returnNode = head;
			for (int i = 0; i < index; i++) {
				returnNode = returnNode.next;
			}
			return returnNode.val;
		}
	}

	public T remove(int index) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("your list doesn't have any elements.");
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size-1) {
			return removeLast();
		} else {
			Node removeNode = head;
			for (int i = 0; i < index; i++) {
				removeNode = removeNode.next;
			}
			removeNode.prev.next = removeNode.next;
			removeNode.next.prev = removeNode.prev;
			size--;
			return removeNode.val;
		}
	}

	public T removeFirst() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("your list doesn't have any elements.");
		} else if (size == 1) {
			T returnC = head.val;
			head = null;
			tail = null;
			size--;
			return returnC;
		} else {
			T returnC = head.val;
			head = head.next;
			head.prev = null;
			size--;
			return returnC;
		}
	}

	public T removeLast() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("your list doesn't have any elements.");
		} else if (size == 1) {
			T returnC = tail.val;
			head = null;
			tail = null;
			size--;
			return returnC;
		} else {
			T returnC = tail.val;
			tail = tail.prev;
			tail.next = null;
			size--;
			return returnC;
		}
	}
}