package prog05;

import java.util.EmptyStackException;

import prog02.DirectoryEntry;

/** Implementation of the interface StackInt<E> using
*   an array.
*   @author Koffman & Wolfgang
*/

public class ArrayStack < E >
    implements StackInt < E > {

  // Data Fields
  /** Storage for stack. */
  E[] theData;
  /** Index to top of stack. */
  int topOfStack = -1; // Initially empty stack.
  private static final int INITIAL_CAPACITY = 10;

  /** Construct an empty stack with the default
      initial capacity.
   */
  public ArrayStack() {
    theData = (E[])new Object[INITIAL_CAPACITY];
  }

  /** Insert a new item on top of the stack.
      post: The new item is the top item on the stack.
            All other items are one position lower.
      @param obj The item to be inserted
      @return The item that was inserted
   */
  public E push(E obj) {
    if (topOfStack == theData.length - 1) {
      reallocate();
    }
    topOfStack++;
    theData[topOfStack] = obj;
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
    }
    return theData[topOfStack--];
  }

  public E peek() {
	    if (empty()) {
	        throw new EmptyStackException();
	    } else {
	    	return theData[topOfStack];	
	    }
}
  public boolean empty() {
	  return theData.length == 0;
  }
  protected void reallocate() {
	  int capacity = theData.length * 2;
	  E[] newDirectory = (E[])new Object[capacity];
      System.arraycopy(theData, 0, newDirectory, 0,
              theData.length);
      theData = newDirectory;
  }
}
