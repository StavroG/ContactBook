package data_structures;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BalancedTree <K extends Comparable<K>, V> implements DictionaryADT<K, V> {
	TreeMap<K, V> map;
	
	public BalancedTree() {
		map = new TreeMap<K, V>();
	}
	
	@Override
	public boolean contains(K key) {
		return map.containsKey(key);
	}

	@Override
	public boolean add(K key, V value) {
		if (map.containsKey(key)) {	//Checks if value is already in map.
			return false;
		}
		map.put(key, value);	//If value not in map add it to the map.
		return true;
	}

	@Override
	public boolean delete(K key) {
		if (!map.containsKey(key)) {	//Makes sure the key is in the map.
			return false;
		}
		map.remove(key);	//If key is in map, remove from map.
		return true;
	}

	@Override
	public V getValue(K key) {
		return map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public K getKey(V value) {
		for (Map.Entry<K, V> node : map.entrySet()) {	//Loops through every value in the map.
			if (((Comparable<V>) value).compareTo(node.getValue()) == 0) {	//If a match is found return that nodes key.
				return node.getKey();
			}
		}
		return null;	//If no match, return null.
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return map.size() == 0;
	}

	@Override
	public void clear() {
		map = new TreeMap<K, V>();	//Makes a new TreeMap.
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator keys() {
		return map.keySet().iterator();	//Returns maps iterator for keys.
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator values() {
		return map.values().iterator();	//Returns maps iterator for values.
	}

}
