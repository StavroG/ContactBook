package data_structures;

import java.util.Iterator;

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
	final int MAX_SIZE;
	int size, modCount;
	
	private LinearList<HashNode<K, V>>[] table;
	
	@SuppressWarnings("unchecked")
	public Hashtable(int MAX_SIZE) {
		this.MAX_SIZE = MAX_SIZE;
		size = modCount = 0; 
		table = (LinearList<HashNode<K, V>>[]) new LinearList[MAX_SIZE];
	}
	
	@Override
	public boolean contains(K key) {
		for (int i = 0; i < table.length; i++) {	//Loops through the hashtable array.
			if (table[i] != null) {		//If there is a linearlist in the hashtable at i.
				for(HashNode<K, V> node : table[i]) {	//Loops through the linear list.
					if (key.compareTo(node.key) == 0) {	//If match is found return true.
						return true;
					}
				}
			}
		}
		return false;	//If not match is found return false.
	}

	@Override
	public boolean add(K key, V value) {
		if (contains(key)) {	//Makes sure that key is not duplicate.
			return false;
		}
		
		int index = key.hashCode() % table.length;	//Gets the index.
		if (index < 0 ) {
			index += table.length;	//Makes sure index is positive.
		}
		
		if (table[index] == null) {
			table[index] = new LinearList<HashNode<K, V>>();	//Adds new linearlist if table at index is null.
		}
		table[index].addFirst(new HashNode<K, V>(key, value));	//Adds new value to linked list.
		
		size++;
		modCount++;
		return true;
	}

	@Override
	public boolean delete(K key) {
		if (!contains(key)) {	//Makes sure key is in the hashtable.
			return false;
		}
		
		for (int i = 0; i < table.length; i++) {	//Looks for a key match.
			if (table[i] != null) {		
				for(HashNode<K, V> node : table[i]) {
					if (key.compareTo(node.key) == 0) {	//If match is found delete the node.
						table[i].remove(node);
						size--;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public V getValue(K key) {
		for (int i = 0; i < table.length; i++) {	//Loops through the hashtable.
			if (table[i] != null) {		
				for(HashNode<K, V> node : table[i]) {
					if (key.compareTo(node.key) == 0) {	//If a match is found return the nodes value.
						return node.value;
					}
				}
			}
		}
		return null;	//If no match found return null.
	}

	@SuppressWarnings("unchecked")
	@Override
	public K getKey(V value) {
		for (int i = 0; i < table.length; i++) {	//Loops through the hashtable.
			if (table[i] != null) {		
				for(HashNode<K, V> node : table[i]) {
					if (((Comparable<V>)value).compareTo(node.value) == 0) {	//If a match is found return nodes key.
						return node.key;
					}
				}
			}
		}
		return null;	//If no match found return null.
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

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		table = (LinearList<HashNode<K, V>>[]) new LinearList[MAX_SIZE];	//Makes new hashtable.
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
	
	@SuppressWarnings("hiding")
	private class HashNode<K , V> implements Comparable<HashNode<K, V>>{
		K key;
		V value;
		
		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		public int compareTo(HashNode<K, V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	
	private class KeysIteratorHelper<E> implements Iterator<E> {
		E[] keys;
		int count;
		
		@SuppressWarnings("unchecked")
		public KeysIteratorHelper() {
			keys = (E[]) new Object[size];
			count = 0;
			
			int index = 0;
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null) {
					for(HashNode<K, V> node : table[i]) {	//Loops through the table and adds all the keys to the array.
						keys[index++] = (E) node.key;
					}
				}
			}
		}
		
		@Override
		public boolean hasNext() {
			return count < keys.length;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			return keys[count++];
		}
	}
	
	private class ValuesIteratorHelper<E> implements Iterator<E> {
		E[] values;
		int count;
		
		@SuppressWarnings("unchecked")
		public ValuesIteratorHelper() {
			values = (E[]) new Object[size];
			count = 0;
			
			int index = 0;
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null) {	
					for(HashNode<K, V> node : table[i]) {	//Loops through the table and adds all the values to the array.
						values[index++] = (E) node.value;	
					}
				}
			}
		}
		
		@Override
		public boolean hasNext() {
			return count < values.length;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			return values[count++];
		}
	}
}













