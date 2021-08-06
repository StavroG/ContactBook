package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class LinearList<E extends Comparable<E>> implements LinearListADT<E>{

	private Node<E> head, tail;
	private int size, modCounter;
	
	public LinearList() {
		size = modCounter = 0;
		head = tail = null;
	}
	
	public void printList() {	// A method that I used to print my list for testing.
		if (size > 0) {
			System.out.println("Head: " + head.data + " Tail: " + tail.data);
			System.out.println("Size: " + size);
			Node<E> tmp = head;
			while(tmp != null) {
				System.out.print(tmp.data + " ");
				tmp = tmp.next;
			}
			System.out.println();
		}
		else {
			System.out.println("List is empty");
		}
	}
	
	@Override
	public boolean addFirst(E obj) {	
		Node<E> newNode = new Node<E>(obj);
		
		if (size == 0) {	// If the list is empty the new node is the head and the tail.
			tail = head = newNode;
		}
		else {	// If the list is not empty then make the new node the head.
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		modCounter++;
		size++;
		return true;
	}

	@Override
	public boolean addLast(E obj) {
		Node<E> newNode = new Node<E>(obj);
		
		if (size == 0) {	// If the list is empty the new node is the head and the tail.
			tail = head = newNode;
		}
		else {	// If the list is not empty then make the new node the tail.
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		modCounter++;
		size++;
		return true;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E tmp = head.data;
		if (size == 1) {	// If there is only one element in the list then that element is head and tail so both must be made null;
			head = tail = null;
		}
		else {	// If there is more than one element in list then remove the head and make the head's next node the head.
		head = head.next;
		head.prev = null;
		}
		modCounter++;
		size--;
		return tmp;
	}

	@Override 
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		E tmp = tail.data;
		if (size == 1) {	// If there is only one element in the list then that element is head and tail so both must be made null;
			tail = head = null;
		}
		else {	// If there is more than one element in list then remove the tail and make the tail's previous node the tail.
			tail = tail.prev;
			tail.next = null;
		}
		modCounter++;
		size--;
		return tmp;
	}

	@Override
	public E remove(E obj) {
		Node<E> search = head;
		
		for (int i = 0; i < size; i++) {
			if (search.data.equals(obj)) {
				if (size > 1) {
					if (!(search.data.equals(head.data))) {	// If it is not the head then set the previous nodes next pointer to current nodes next.
						search.prev.next = search.next;
					}
					if (!(search.data.equals(tail.data))) {	// If it is not the tail then set the next nodes previous pointer to current nodes previous.
						search.next.prev = search.prev;
					}
				}
				if (search.equals(head)) {
					head = head.next;
				}
				if (search.equals(tail)) {
					tail = tail.prev;
				}
				search = null;
				size--;
				modCounter++;
				return obj;
			}
			search = search.next;
		}
		return null;
	}

	@Override
	public E peekFirst() {
		if (isEmpty()) {
			return null;
		}
		return head.data;
	}

	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		return tail.data;
	}

	@Override
	public boolean contains(E obj) {
		if (find(obj) != null) {
			return true;
		}
		return false;
	}

	@Override
	public E find(E obj) {
		if (size > 0) {
			Node<E> tmp = head;
			while(tmp != null) {
				if (tmp.data.equals(obj)) {	// Checks the searched value against the current node in the list.
					return obj;	
				}
				tmp = tmp.next;	// If not found then make the current node the next node and try again.
			}
		}
		return null;
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	@SuppressWarnings("hiding")
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		public Node(E data) {
			this.data = data;
		}
	}
	
	private class IteratorHelper implements Iterator<E> {
		private Node<E> node;
		private int changeCounter;
		
		public IteratorHelper() {
			node = head;
			changeCounter = modCounter;
		}
		
		@Override
		public boolean hasNext() {
			if (changeCounter != modCounter) {
				throw new ConcurrentModificationException();
			}
			if (node != null) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new ConcurrentModificationException();
			}
			E tmp = node.data;
			node = node.next;
			return tmp;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
}
