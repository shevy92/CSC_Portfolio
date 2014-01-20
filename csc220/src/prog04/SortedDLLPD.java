package prog04;

import java.io.*;
import java.util.*;

public class SortedDLLPD extends DLLBasedPD {

  protected void add (String name, String number) {
	  DLLNode entry = new DLLNode(name, number);	  
	  DLLNode next = head;	
	  
	  if (head == null && tail == null) {
		  head = entry;
	      tail = entry;
	      
	  } else {
	  
	  while (name.compareTo(next.getKey()) < 0 ) { 
		  next = next.getNext();
	  }	  
	  
	  if (next == null) {
	      entry.setPrevious(next);
	      entry.setNext(null);
	      tail = entry;
	      return;
	  } else if (next.getPrevious() == null) {
		  head.setPrevious(entry);
          entry.setNext(head);
	      head = entry; 
	      return;
	  } else {
		 DLLNode prev = next.getPrevious();
		 prev.setNext(entry); 		  
		 entry.setPrevious(prev);
		 entry.setNext(next);
		 next.setPrevious(entry);
	  }
	  modified = true; 
	  }
  }

  protected DLLNode find(String name) {
	  for (DLLNode n = head; n != null ; n = n.getNext()) {
			if (name != null) {
			  if (n.getKey().equals(name)) {
		      return n; 
			  } else if (name.compareTo(n.getKey()) > 0) {
				  return null;
			  }
			}
		  }
	    
	    return null; // Name not found.
	  }
		
}
