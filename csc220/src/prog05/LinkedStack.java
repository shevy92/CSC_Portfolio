package prog05;

import java.util.EmptyStackException;

/** Class to implement interface StackInt<E> as a linked list.
*   @author Koffman & Wolfgang
* */

public class LinkedStack < E >
    implements StackInt < E > {

  /** A Node is the building block for a single-linked list. */
  private static class Node < E > {
    // Data Fields
    /** The reference to the data. */
    private E data;

    /** The reference to the next node. */
    private Node next;

    // Constructors
    /** Creates a new node with a null next field.
        @param dataItem The data stored
     */
    private Node(E dataItem) {
      data = dataItem;
      next = null;
    }

    /** Creates a new node that references another node.
        @param dataItem The data stored
        @param nodeRef The node referenced by new node
     */
    private Node(E dataItem, Node < E > nodeRef) {
      data = dataItem;
      next = nodeRef;
    }
  } //end class Node

  // Data Fields
  /** The reference to the first stack node. */
  private Node < E > topOfStackRef = null;

  /** Insert a new item on top of the stack.
      post: The new item is the top item on the stack.
            All other items are one position lower.
      @param obj The item to be inserted
      @return The item that was inserted
   */
  public E push(E obj) {
    topOfStackRef = new Node < E > (obj, topOfStackRef);
    return obj;
  }

  /** Remove and return the top item on the stack.
      pre: The stack is not empty.
      post: The top item on the stack has been
            removed and the stack is one item smaller.
      @return The top item on the stack
      @throws EmptyStackException if the stack is empty
   */
  public E pop() {
    if (empty()) {
      throw new EmptyStackException();
    } else {
	    E result = topOfStackRef.data;
	    topOfStackRef = topOfStackRef.next;
	return result;
    }
  }

  /** Return the top item on the stack.
      pre: The stack is not empty.
      post: The stack remains unchanged.
      @return The top item on the stack
      @throws EmptyStackException if the stack is empty
   */
  public E peek() {
    if (empty()) {
      throw new EmptyStackException();
    } else {
	return topOfStackRef.data;
    }
  }

  /** See whether the stack is empty.
      @return true if the stack is empty
   */
  public boolean empty() {
    return topOfStackRef == null;
  }
}
