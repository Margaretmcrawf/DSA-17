public class MyArrayList<T> {
    private T[] elems;
	private int size;

	public MyArrayList() {
		size = 0;
		elems = (T[]) new Object[10];
	}

	public MyArrayList(int capacity) {
		size = 0;
		elems = (T[]) new Object[capacity];

	}

	public void add(T c) {
		if (size+1 > elems.length) {
			doubleSize();
		}
		elems[size] = c;
		size++;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return elems[index];
	}

	public T remove(int index) {
		T removedCow = elems[index];
		for (int i = index; i<size-1; i++) {
			elems[i] = elems[i+1];
		}
		elems[size-1] = null;
		size -= 1;
		if (size < elems.length/4 && size > 20) {
			downSize();
		}
		return removedCow;
	}

	public void add(int index, T c) {
		if (size + 1 > elems.length) {
			doubleSize();
		}
		try {
			for (int i = size; i > index; i--) {
				elems[i] = elems[i - 1];
			}
			elems[index] = c;
			size += 1;
		}
		catch (IndexOutOfBoundsException ex) {
			// I don't know why I would do this. An if statement is more appropriate for the case that adding one
			//overflows the array, and if not the default exception seems pretty appropriate.
		}
	}

	public void doubleSize() {
		T[] tempCow = (T[]) new Object[2*size];
		System.arraycopy(elems, 0, tempCow, 0, size);
		elems = tempCow;

	}

	public void downSize() {
		T[] tempCow = (T[]) new Object[elems.length/2];
		System.arraycopy(elems, 0, tempCow, 0, size);
		elems = tempCow;

	}
}
