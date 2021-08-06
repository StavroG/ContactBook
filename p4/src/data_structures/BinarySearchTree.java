package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class BinarySearchTree <K extends Comparable<K>, V> implements DictionaryADT<K, V> {
	Node<K, V> root;
	int size, modCounter;
	
	public BinarySearchTree() {
		root = null;
		size = modCounter = 0;
	}
	
	@Override
	public boolean contains(K key) {
		return searchTree(root, key);
	}
	private boolean searchTree(Node<K, V> node, K key) {
		if (node == null) {	
			return false;
		}
		if (node.key.compareTo(key) == 0) {	//If the node we are searching is equal to our key, we found what we are looking for.
			return true;
		}
		if (((Comparable<K>)key).compareTo(node.key) > 0) {	//If the key we are searching for is less than node, check the left child node.
			return searchTree(node.right, key);
		}
		return searchTree(node.left, key);	//If the key is not less than node, check right node.
	}
	@Override
	public boolean add(K key, V value) {
		if (contains(key)) {
			return false;
		}
		if (root == null) {
			root = new Node<K, V>(key, value);
			size++;
			modCounter++;
			return true;
		}
		return insertNode(root, key, value);
	}

	private boolean insertNode(Node<K, V> node, K key, V value) {
		if (node == null) {
			return false;
		}
		if (((Comparable<K>)key).compareTo(node.key) > 0) { //node value is greater than value.
			//Go right
			if (node.right == null) {
				Node<K, V> tmp = new Node<K,V>(key, value); //Adds a new node.
				node.right = tmp;
				tmp.parent = node;
				size++;
				modCounter++;
				return true;
			}
			else {
				return insertNode(node.right, key, value);
			}
		}
		else {	//node value is less than value.
			//Go left
			if (node.left == null) {
				Node<K, V> tmp = new Node<K,V>(key, value); //Adds a new node.
				node.left = tmp;
				tmp.parent = node;
				size++;
				modCounter++;
				return true;
			}
			else {
				return insertNode(node.left, key, value);
			}
		}
	}
	
	@Override
	public boolean delete(K key) {
		return deleteNode(root, key);
	}

	private boolean deleteNode(Node<K, V> node, K key) {
		if (node == null) {	//Will return null if value not found.
			return false;
		}
		if (node.key.compareTo(key) == 0) {	//If the node we are searching is equal to our key, we found what we are looking for.
			Node<K, V> tmpLeft = node.left;
			Node<K, V> tmpRight = node.right;
			node = null;
			size--;
			modCounter++;
			if (tmpLeft != null) {
				insertNode(root, tmpLeft.key, tmpLeft.value);
			}
			if (tmpRight != null) {
				insertNode(root, tmpRight.key, tmpRight.value);
			}
			return true;
		}
		if (node.key.compareTo(key) > 0) {	//If the key we are searching for is less than node, check the left child node.
			return deleteNode(node.left, key);
		}
		return deleteNode(node.right, key);	//If the key is not less than node, check right node.
	}
	
	@Override
	public V getValue(K key) {
		return findValue(root, key);
	}
	
	private V findValue(Node<K, V> node, K key) {
		if (node == null) {	//Will return null if value not found.
			return null;
		}
		if (node.key.compareTo(key) == 0) {	//If the node we are searching is equal to our key, we found what we are looking for.
			return node.value;
		}
		if (node.key.compareTo(key) > 0) {	//If the key we are searching for is less than node, check the left child node.
			return findValue(node.left, key);
		}
		return findValue(node.right, key);	//If the key is not less than node, check right node.
	}

	@Override
	public K getKey(V value) {
		return findKey(root, value);
	}

	@SuppressWarnings("unchecked")
	private K findKey(Node<K, V> node, V value) {	//Will return null if value not found.
		if (node == null) {
			return null;
		}
		if (((Comparable<V>) node.value).compareTo(value) == 0) {	//If the node we are searching is equal to our key, we found what we are looking for.
			return node.key;
		}
		if (((Comparable<V>) node.value).compareTo(value) > 0) {	//If the key we are searching for is less than node, check the left child node.
			return findKey(node.left, value);
		}
		return findKey(node.right, value);	//If the key is not less than node, check right node.
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator keys() {
		return new KeysIteratorHelper<K>();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator values() {
		return new ValuesIteratorHelper<V>();
	}
	
	@SuppressWarnings({ "unused", "hiding" })
	private class Node<K, V> implements Comparable<Node<K, V>>{
		K key;
		V value;
		Node<K, V> parent, left, right;
		
		public Node(K key, V value) {
			this.key = key;	
			this.value = value;
			parent = left = right = null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Node<K, V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	
	private class KeysIteratorHelper<E> implements Iterator<E> {
		E[] keys;
		int count;
		int index;
		int modCnt;
		@SuppressWarnings("unchecked")
		public KeysIteratorHelper() {
			keys = (E[]) new Object[size];
			count = index = 0;
			modCnt = modCounter;
			traverseTree(root);
		}
		
		@Override
		public boolean hasNext() {
			if (modCnt != modCounter) {	//Checks for concurrent modifier.
				throw new ConcurrentModificationException();
			}
			return count < size;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			return keys[count++];
		}
		
		@SuppressWarnings("unchecked")
		private void traverseTree(Node<K, V> node) {	//Searches through the tree.
			if (node != null) {	//If node is not null add it to the keys array and continue searching.
				traverseTree(node.left);
				keys[index++] = (E) node.key;
				traverseTree(node.right);
			}
		}
	}

	private class ValuesIteratorHelper<E> implements Iterator<E> {
		E[] values;
		int count;
		int index;
		int modCnt; 
		
		@SuppressWarnings("unchecked")
		public ValuesIteratorHelper() {
			values = (E[]) new Object[size];
			count = index = 0;
			modCnt = modCounter;
			traverseTree(root);
		}
		
		@Override
		public boolean hasNext() {
			if (modCnt != modCounter) {	//Checks for concurrent modifier.
				throw new ConcurrentModificationException();
			}
			return count < values.length;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			return values[count++];
		}
		
		@SuppressWarnings("unchecked")
		private void traverseTree(Node<K, V> node) {	//Searches through the tree.
			if (node != null) {	//If node is not null add it to the values array and continue searching.
				traverseTree(node.left);
				values[index++] = (E) node.value;
				traverseTree(node.right);
			}
		}
	}
}
