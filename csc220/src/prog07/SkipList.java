package prog07;
import java.util.*;


public class SkipList <K extends Comparable<K>, V>
  extends AbstractMap<K, V>
  implements Map<K, V> {
  public static class Node <K extends Comparable<K>, V>
    implements Map.Entry<K, V> {
    K key;
    V value;
    Node<K, V>[] next;
    
    Node () {
      next = (Node<K,V>[]) new Node[1];
    }

    Node (K key, V value) {
      this.key = key;
      this.value = value;
      int levels = 1;

      // Start flipping a coin and increment levels as many times as
      // you get ``heads'' in a row.  Stop when you get ``tails''.

      long coin = 0;
      coin = (long) (Math.random()*2);
      if (coin == 0) {
          levels++;
      }
      
      next = (Node<K,V>[]) new Node[levels];
    }
    
    public K getKey () { return key; }
    public V getValue () { return value; }
    public V setValue (V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }

    /**
     * Allocate a new next array with new length.  Copy old one into
     * it.  Update next.
     */
    private void reallocate (int length) {
        

        Node<K, V>[] longerNext = new Node[length];
        System.arraycopy (next, 0, longerNext, 0, next.length);

        next = longerNext;
    }
  }
  
  private Node<K, V> head = new Node();
  private static int size;
  
  /**
   * On the given level, starting a search from the given node, return
   * the Node previous to the Node with key if it is there OR the Node
   * that would be previous if it were added.
   */
  private Node<K, V> find (K key, Node<K, V> node, int level) {
    // Move node forward until it is the node before the key
    // or where the key should be (because it might not be in this list).

		  while (node.next[level] != null && node.next[level].key.compareTo(key) < 0) {
			  node = node.next[level];	  
	  }	  
    return node;
  }
  
  /**
   * Starting from Node head on the given level, get the Node with the
   * given key or return null.
   */
  private Node<K, V> getNode (K key, Node<K, V> head, int level) {
    // Use find to get the previous Node on this level
	  Node<K, V> prev = find((K) key, head, level);
    // If the Next node is the one we want, return it.
	  if (prev.next[level] != null && prev.next[level].getKey() == prev){
	      return prev.next[level];
	  } 
    // If level is zero, it must not be there.
	  if (level == 0) {  
	      return null; 
	  } 
    // Recursively search the next level, starting from the output of find.
    // This takes ONE LINE!
	  Node <K, V> returnNode = getNode(key, prev, level-1);
	  return returnNode;  
  }
  
  public boolean containsKey (Object key) {
    // One LINE!
    // return getNode(/* you figure it out */) != null;
      return getNode((K)key, head, head.next.length) != null;
  }
  
  public V get (Object key) {
    // Call getNode.  The rest is easy.
    Node<K, V> prev = getNode ((K) key, head, head.next.length-1);
    if (prev != null){
    	return (V)prev.getValue();
    } else {
    return null;
    }
  }
  
  public boolean isEmpty () { return size == 0; }
  
  /**
   * Put the given Node into the SkipList starting from the given head
   * on the given level.
   */
  public void putNode (Node<K, V> node, Node<K, V> head, int level) {
    // Find the previous node.
	    Node<K, V> previousNode = find(node.key, head, level);
    // If node should be inserted on this level, insert it.
        if(node.next.length > level){
          Node<K, V> first = previousNode.next[level];
  		  previousNode.next[level] = node;
  		  node.next[level] = first;
        } 
    // If level is zero, you are done.
        if (level == 0){
          return;
        }
    // Otherwise, recursively put the node into the next level down,
    // starting from the output of find.
          putNode(node, previousNode, level-1);        
  }      

  public V put (K key, V value) {
    // First try calling getNode.
	   Node<K, V> prev = getNode (key, head, head.next.length-1);
    // If it is not null, do the right thing.
       if(prev != null){
    	  return (V)prev.getValue(); 
       } 
    // If it is null, create a new node.
       
       Node<K, V> newNode = new Node <K, V>(key, value);	
              
    // Reallocate the head node if necessary.
           if (head.next.length < newNode.next.length){
    	       head.reallocate(newNode.next.length);
           }
       
    // Use putNode to put the new node into the SkipList.
       putNode(newNode, head, head.next.length-1);
    // Don't forget about size!
	  size++;
    return null;
  }
  
  /**
   * Starting from Node head on the given level, remove the Node with
   * the given key, if it is there.
   */
  private Node<K, V> removeNode (K key, Node<K, V> head, int level) {
    // Use find to get the previous Node on this level.
       Node<K, V> previous = find(key, head, level);
    // If the next node is the one we want, remove it.  Also save it
    // in a variable.
       if (previous.next[level] != null && previous.next[level].getKey() == key){
    	   Node<K, V> wantedNode = previous.next[level];
    	   Node<K, V> second = wantedNode.next[level];
    	   previous.next[level] = second;
    	   return wantedNode;
       }
    // If level is zero, just return the node we found (or null).
       if (level == 0) {
    	   if(previous.next != null && previous.next[level].key == key){
    		   return previous;
    	   }
    // Call removeNode recursively to remove the Node from the next
    // level down, starting from the output of find.
       removeNode (key, previous, level-1);
       }
       return null;
  }

  public V remove (Object key) {
    // Call removeNode.
	  Node<K, V> removedNode = removeNode ((K) key, head, head.next.length-1);
    // Do the right things if it is null or non-null.  Don't forget to
	  if (removedNode != null){
		  V value = removedNode.getValue();
		  size--;
		  return value; 
	  }
    // decrement size if you have to.
    return null;
  }
  
  private static class Iter<K extends Comparable<K>, V> implements Iterator<Map.Entry<K, V>> {
    Node<K, V> next;
    
    Iter (Node<K, V> head) {
      next = head.next[0];
    }
    
    public boolean hasNext () { return next != null; }
    
    public Map.Entry<K, V> next () {
      Map.Entry<K, V> ret = next;
      next = next.next[0];
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
    Map<String, Integer> map = new SkipList<String, Integer>();
    
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
