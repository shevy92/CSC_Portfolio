/*
 * SortedPD.java
 *
 * Created on January 24, 2006, 10:26 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package prog02;

import java.io.*;
import java.util.*;

public class SortedPD extends ArrayBasedPD {
    
//----Remove method
	
	public String removeEntry(String name) {
       
    int i = find(name);
  //No entry with that name 
    if (i == -1)
        return null;
        DirectoryEntry entry = theDirectory[i];
  //Entry present, delete it, and rearrange array      
    for (;i + 1 < size; i++) 
        theDirectory[i] = theDirectory[i + 1];
        
    size--;
    modified = true;
    return entry.getNumber();
	}
	
//----Add Method
	
	protected void add(String name, String number) {
  //If array is not large enough      
	if (size >= capacity) 
        reallocate();
  //Test to find where it goes (compare names) and rearrange array      
    int i;
    for (i = size; i > 0 && name.compareTo(theDirectory[i-1].getName()) < 0 ; i--)   
	    
    theDirectory[i] = theDirectory[i -1];	
	theDirectory[i] = new DirectoryEntry(name, number);	
	size++;
    }
    	
//----Find Method
	
	protected int find(String name) {
  //Create range and compare	
    int low = 0;
	for (int high = size - 1; low <= high;) {
	    int middle = (low + high) / 2;
	    int cmp = name.compareTo(theDirectory[middle].getName());
  //If negative, move down the range, if positive move up, and if 0 then you found it!	            
	  if(cmp == 0)
	      return middle;
	  if(cmp < 0)
	      high = middle - 1;
	  else
	      low = middle + 1;
	  }
  // If not there
          return -1; 
	  }
		
}
