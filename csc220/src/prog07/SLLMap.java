package prog07;

import java.util.*;

public class SLLMap <K extends Comparable<K>, V>
  extends AbstractMap<K, V>
  implements Map<K, V> {
  private static class Node <K extends Comparable<K>, V>
    implements Map.Entry<K, V> {
    K key;
    V value;
    Node<K, V> next;
    
    Node (K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    public K getKey () { return key; }
    public V getValue () { return value; }
    public V setValue (V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }
  }
  
  private Node<K, V> head = new Node(null, null);
  private int size;
  
  /**
   * Return the Node previous to the Node with key if it is there OR
   * the Node that would be previous if it were added.
   */
  private Node<K, V> find (K key, Node<K, V> node) {

	  
	while (node.next != null) {
	    if (node.next.getKey().compareTo(key)>= 0){
	    	return node;
	    } 
		node = node.next;
	    
	}
    return node;
  }
  
  public boolean containsKey (Object key) {
    // Call find to get the previous node.
    // Casting is necessary to convert Object to K, the key type.
    Node<K, V> prev = find((K) key, head);

    // If the next one is the one you want, return true.

    if (prev.next.getKey() == key){
    	return true;
    } else {
    return false;
    }
  }
  
  public V get (Object key) {
    // Call find to get the previous node.
	  Node<K, V> prev = find((K) key, head);
    // If the next one is the one you want, return its value.
	  if (prev.next.getKey() == key){
	    	return prev.next.getValue();
	    } else {
	    return null;
	    }
  }
  
  public V put (K key, V value) {
    // Call find to get the previous node.
	  Node<K, V> prev = find((K) key, head);
 
    // If the next one is the one you want, change its value.
	  if (prev.next != null && prev.next.getKey() == key){
		  V oldVal = prev.next.value;
		  prev.next.setValue(value);
		  return oldVal;
	  } else {
    // Otherwise, create a new node and insert it after the previous node.
    // Don't forget about size!
		  Node<K, V> newNode = new Node <K,V>(key, value);	
		  Node<K, V> prevNode = prev.next;
		  prev.next = newNode;
		  newNode.next = prevNode;		  
	      size++;
		  }
	  
    return null;
  }
  
  public boolean isEmpty () { return size == 0; }
  
  public V remove (Object key) {
    // Get the previous node.
	  Node<K, V> previousNode = find((K) key, head);
    // If the next one is the one you want, remove it from the list.
    // Don't forget about size.
	  if (previousNode != null && previousNode.next.getKey() == key){
		  Node <K, V> first = previousNode.next;
	      Node <K, V> second = first.next;
	      previousNode.next = second;
	      size--;
	      return second.getValue(); 
	  }
    return null;
  }
  
  private static class Iter<K extends Comparable<K>, V>
    implements Iterator<Map.Entry<K, V>> {
    Node<K, V> next;
    
    Iter (Node<K, V> head) {
      next = head.next;
    }
    
    public boolean hasNext () { return next != null; }
    
    public Map.Entry<K, V> next () {
      Map.Entry<K, V> ret = next;
      next = next.next;
      return ret;
    }
    
    public void remove () {
      throw new UnsupportedOperationException();
    }
  }
  
  private class Setter extends AbstractSet<Map.Entry<K, V>> {
    public Iterator<Map.Entry<K, V>> iterator () {
      return new Iter(head);
    }
    
    public int size () { return size; }
  }
  
  public Set<Map.Entry<K, V>> entrySet () { return new Setter(); }
  
  public static void main (String[] args) {
    Map<String, Integer> map = new SLLMap<String, Integer>();
    
    map.put("Victor", 50);
    map.put("Irina", 45);
    map.put("Lisa", 47);
    
    for (Map.Entry<String, Integer> pair : map.entrySet())
      System.out.println(pair.getKey() + " " + pair.getValue());
    
    System.out.println(map.get("Irina"));
    map.remove("Irina");
    
    for (Map.Entry<String, Integer> pair : map.entrySet())
      System.out.println(pair.getKey() + " " + pair.getValue());
    
    System.out.println(map.get("Irina"));
  }
}
