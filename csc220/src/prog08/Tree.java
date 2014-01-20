package prog08;
import java.util.*;

public class Tree<E extends Comparable<E>> {
  private class Node {
    E data;
    Node left, right;
    
    Node (E data) { this.data = data; }
  }
  
  private Node root;
  private int size;

  public int size () { return size; }

  /**
   * Add data to set if it is not already there.
   * Return false if it was already there.
   */
  public boolean add (E data) {
    if (contains(data))
      return false;
    root = add(data, root);
    size++;
    return true;
  }
  
  /**
   * Add data to tree rooted at root.
   * Return root of modified tree.
   */
  private Node add (E data, Node root) {
    if (root == null)
      return new Node(data);
    int cmp = data.compareTo(root.data);
    if (cmp < 0)
      root.left = add(data, root.left);
    else
      root.right = add(data, root.right);
    return root;
  }
  
  /**
   * Return true if set contains obj.
   */
  public boolean contains (Object obj) {
    return find((E) obj, root) != null;
  }
  
  /**
   * Return node with data or null if it is not there.
   */
  private Node find (E data, Node root) {
	  if (root == null)
			return null;
	        if (data.compareTo(root.data) == 0) {
			    return root;
	        }
	        if (data.compareTo(root.data) < 0) {
			    return find(data, root.left);
	        } else
			return find(data, root.right);
      }

  /**
   * Remove obj from set.  Return false if it was not there.
   */
  public boolean remove (Object obj) {
    if (!contains(obj))
      return false;
    root = remove((E) obj, root);
    size--;
    return true;
  }

  /**
   * Remove node with data from tree rooted at root.
   * Return root of modified tree.
   * You can assume the data is there somewhere.
   */
  private Node remove (E data, Node root) {
	  int cmp = root.data.compareTo(data);
	  if (cmp == 0) {
    	  return (removeRoot(root));
      } 
      if (cmp < 0) {
    	  root.left = remove(data, root.left);
      } 
      if (cmp > 0) {
    	  root.right = remove(data, root.right);
      }    
    return root;
  }

  /**
   * Remove root of tree rooted at root.
   * Return root of BST of remaining nodes.
   */
  private Node removeRoot (Node root) {
    if (root.left == null) {
    	return root.right;
    }    
    Node newRoot = getRightmostNode(root.left);
    root.left = removeRightmost(root.left);
    newRoot.left = root.left;
    newRoot.right = root.right;
    return newRoot;
  }

  /**
   * Return rightmost node in tree rooted at root.
   */
  private Node getRightmostNode (Node root) {
    if (root.right == null) {
    	return root;
    }
    getRightmostNode(root.right);
    return root;
  }
  
  /**
   * Remove rightmost node from tree rooted at root.
   * Return root of modified tree.
   */
  private Node removeRightmost (Node root) {
    if (root.right == null){
    	return root.left;
    }
    root.right = removeRightmost(root.right);
    return root;
  }
  
  public String toString () {
    return toString(root, 0);
  }
  
  private String toString (Node root, int indent) {
    if (root == null)
      return "";
    String ret = toString(root.right, indent + 2);
    for (int i = 0; i < indent; i++)
      ret = ret + "  ";
    ret = ret + root.data + "\n";
    ret = ret + toString(root.left, indent + 2);
    return ret;
  }

  public static void main (String[] args) {
    Tree<String> tree = new Tree<String>();
    
    tree.add("Victor");
    tree.add("Lisa");
    tree.add("Zoe");
    tree.add("Hal");
    System.out.println(tree);
    
    tree.remove("Victor");
    System.out.println(tree);
    
    tree.add("Victor");
    System.out.println(tree);
  }
}
