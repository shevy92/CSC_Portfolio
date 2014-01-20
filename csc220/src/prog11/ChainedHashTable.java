package prog11;

import java.util.*;

public class ChainedHashTable<K, V> {
    private static class Node<K, V> {
	K key;
	V value;
	Node<K,V> next;

	Node (K key, V value, Node<K,V> next) {
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}
    }

    private final static int DEFAULT_CAPACITY = 5;
    private Node<K,V>[] table = new Node[DEFAULT_CAPACITY];
    private int size;

    private int hashIndex (Object key) {
	int index = key.hashCode() % table.length;
	if (index < 0)
	    index += table.length;
	return index;
    }

    private Node<K,V> find (Object key) {
	int index = hashIndex(key);
	for (Node<K,V> node = table[index]; node != null; node = node.next)
	    if (key.equals(node.key))
		return node;
	return null;
    }

    public boolean containsKey (Object key) {
	return find(key) != null;
    }

    public V get (Object key) {
	Node<K,V> node = find(key);
	if (node == null)
	    return null;
	return node.value;
    }

    public V put (K key, V value) {
	Node<K,V> node = find(key);
	if (node != null) {
	    V old = node.value;
	    node.value = value;
	    return old;
	}
	if (size == table.length)
	    rehash(2 * table.length);
	int index = hashIndex(key);
	table[index] = new Node<K,V>(key, value, table[index]);
	size++;
	return null;
    }
	
    public V remove (Object key) {
	// Get the index for the key.
    int index = hashIndex(key);
    	
	// Deal with the case that the linked list at that index is empty.
    if (table[index] == null){
    	return null;
    }
    
	// Deal with the case that the key belongs to the first
	// element in that list.
    if (key.equals(table[index].key)){
    	Node<K, V> returnVal = table[index];
    	table[index] = returnVal.next;
    	return returnVal.value;
    }    
	// Deal with the case that the key belongs to some other
	// element in that list.
    
    	Node<K, V> prevNode = table[index];
    	while (prevNode.next != null && key.equals(prevNode.next.key) != true){
    		prevNode = prevNode.next;
    	}
    	
    	if(prevNode.next == null){
    		return null;
    	} 
    	
    	if (key.equals(prevNode.next.key)){
    	    Node<K, V> nextNode = prevNode.next;
    	    prevNode.next = nextNode.next;
            return nextNode.value;
    	}
	// Return null otherwise.
	return null;
    }

    // Change the capacity (length) of the hash table to newCapacity.
    private void rehash (int newCapacity) {
	// Save the current table in a variable.
    	Node<K, V>[] currentTable = table;
    	
	// Allocate a new table and save it in the private table
	// variable.
    	table = new Node [newCapacity];
    	size = 0;

	// For each element in the old table, call put(), which will
	// put it into the new table.
    	for (int i = 0; i < currentTable.length; i++){   		  		
    		for (Node<K,V> node = currentTable[i]; node != null; node = node.next) {
    			put(node.key, node.value);
    		}
    		
    	}
    }

    public String toString () {
	String ret = "------------------------------\n";
	for (int i = 0; i < table.length; i++) {
	    ret = ret + i + ":";
	    for (Node<K,V> node = table[i]; node != null; node = node.next)
		ret = ret + " " + node.key + " " + node.value;
	    ret = ret + "\n";
	}
	return ret;
    }

    public static void main (String[] args) {
	ChainedHashTable<String, Integer> table =
	    new ChainedHashTable<String, Integer>();

	table.put("Brad", 46);
	System.out.println(table);
	table.put("Hal", 10);
	System.out.println(table);
	table.put("Kyle", 6);
	System.out.println(table);
	table.put("Lisa", 43);
	System.out.println(table);
	table.put("Lynne", 43);
	System.out.println(table);
	table.put("Victor", 46);
	System.out.println(table);
	table.put("Zoe", 6);
	System.out.println(table);
	table.put("Zoran", 76);
	System.out.println(table);

	table.remove("Zoe");
	System.out.println(table);
	table.remove("Kyle");
	System.out.println(table);
	table.remove("Brad");
	System.out.println(table);
	table.remove("Zoran");
	System.out.println(table);
	table.remove("Lisa");
	System.out.println(table);
    }
}
			     
