package prog11;

import java.util.*;


public class OpenHashTable<K, V> {
    private static class Node<K, V> {
	K key;
	V value;
	
	Node (K key, V value) {
	    this.key = key;
	    this.value = value;
	}
    }

    private final static int DEFAULT_CAPACITY = 5;
    private Node<K,V>[] table = new Node[DEFAULT_CAPACITY];
    private Node<K,V> DELETED = new Node<K,V>(null, null);
    private int size;
    int nonNull = 0;
    
    
    private int hashIndex (Object key) {
	int index = key.hashCode() % table.length;
	if (index < 0)
	    index += table.length;
	return index;
    }

    // Linear probe sequence: start at hashIndex(key) and increment,
    // but roll around to zero at the end of the table.
       
    
    
    // Return the index of the Node with key if it is in the probe
    // sequence.

    // If it is not there, return the index where the Node with key
    // should be inserted.  If there is a deleted Node in the probe
    // sequence, return the index of the *first* deleted Node in the
    // sequence.

    // Otherwise return the index of the first null in the probe
    // sequence.
    private int find (Object key) {
    	
    	int hashIndex = hashIndex(key);
    	boolean deletedHash = false;
    	int deletedHashIndex = 0;
    	int end = table.length-1;
    	
    	while (table[hashIndex] != null) {
    	
    		//if found deleted, store tht in a variable. if found a 2nd one, make sure to not overwrite it. 
    		if (table[hashIndex].equals(DELETED)) {
    			if (deletedHash != true) {
    			    deletedHash = true;
    			    deletedHashIndex = hashIndex;
    			}
    		}
    		if (table[hashIndex].key != null && table[hashIndex].key.equals(key)) {
    			return hashIndex;
    		}
    		if (hashIndex == end) {
    			hashIndex = 0;
    		}   		
    		hashIndex++;
    	}
    	//if stored deleted, return that
    //if not, return index of first null
    	//if NOTHING then set hashindex to 0
    	
    	if (deletedHash == true) {
    		return deletedHashIndex;
    	}
    	
    	return hashIndex;
    }

    public boolean containsKey (Object key) {
	Node<K,V> node = table[find(key)];
	return node != null && node != DELETED;
    }

    public V get (Object key) {
	Node<K,V> node = table[find(key)];
	if (node == null || node == DELETED)
	    return null;
	return node.value;
    }

    public V put (K key, V value) {
	System.out.println("put " + key + " " + value);
	int index = find(key);
	Node<K,V> node = table[index];
	if (node == null || node == DELETED) {
	    
		if (node == null) {
			nonNull = nonNull + 1;
		}
		
		table[index] = new Node<K,V>(key, value);
	    size++;
	    if (nonNull > table.length / 2)
		rehash(4 * size);
	    return null;
	}
	V old = node.value;
	node.value = value;
	return old;
    }

    public V remove (Object key) {
	System.out.println("remove " + key);
	int index = find(key);
	Node<K,V> node = table[index];
	if (node == null || node == DELETED)
	    return null;
	table[index] = DELETED;
	size--;
	return node.value;
    }

    private void rehash (int newCapacity) {
    	Node<K, V>[] currentTable = table;
    	table = new Node [newCapacity];
    	size = 0;
    	nonNull = 0;
    	
    	for (int i = 0; i < currentTable.length; i++) {
    		if (currentTable[i] != null && currentTable[i] != DELETED) {
    		    put(currentTable[i].key, currentTable[i].value);  
    		}
    	}
    	
    }

    public String toString () {
	String ret = "------------------------------\n";
	for (int i = 0; i < table.length; i++) {
	    ret = ret + i + ": ";
	    Node<K,V> node = table[i];
	    if (node == null)
		ret = ret + "null\n";
	    else if (node == DELETED)
		ret = ret + "DELETED\n";
	    else
		ret = ret + node.key + " " + node.value + "\n";
	}
	return ret;
    }

    public static void main (String[] args) {
	OpenHashTable<String, Integer> table =
	    new OpenHashTable<String, Integer>();

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
	table.remove("Hal");
	System.out.println(table);
	table.remove("Lynne");
	System.out.println(table);
	table.put("Lynne", 44);
	System.out.println(table);
    }
}
			     
