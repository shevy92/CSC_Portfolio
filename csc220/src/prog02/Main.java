/*
 * Main.java
 *
 * Created on August 29, 2005, 4:29 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package prog02;


/**
 *
 * @author vjm
 */
public class Main {
    
    /** Processes user's commands on a phone directory.
      @param fn The file containing the phone directory.
      @param ui The UserInterface object to use
             to talk to the user.
      @param pd The PhoneDirectory object to use
             to process the phone directory.
     */
    public static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) {
	pd.loadData(fn);

	String[] commands = {
	    "Add/Change Entry",
	    "Look Up Entry",
	    "Remove Entry",
	    "Save Directory",
	    "Exit"};

	String name, number, oldNumber;

	while (true) {
	    int c = ui.getCommand(commands);
	    switch (c) {
	    case 0:
	    	name = ui.getInfo("Enter Name");
	    	if (name != null) {
	    		oldNumber = pd.lookupEntry(name);
	    		pd.addOrChangeEntry(name, oldNumber);
	    		number = ui.getInfo("What is the new number?");
	    		ui.sendMessage(name + " has been changed");
	    		if (number != null) {
	    		pd.addOrChangeEntry(name, number);
	    		} else {
	    			ui.sendMessage("No number has been entered");
	    		}
	    	} else {
	    		number = ui.getInfo("Enter phone number for" + name);
	    		if (number != null) {
	    			pd.addOrChangeEntry(name, number);
		    		ui.sendMessage(name + " has been added");
	    		} else {
	    			ui.sendMessage("No number has been entered");
	    	    } 
	    	}
		break;
	    case 1:
		    name = ui.getInfo("Enter Name");    
		    number = pd.lookupEntry(name);
		      if (number == null) {
		    	  ui.sendMessage("The person is not in the directory.");
		      } else {
		          ui.sendMessage("The number is: " + number);
		      }
		break;
	    case 2:
	    	name = ui.getInfo("Enter Name");
	    	number = pd.lookupEntry(name);
	    	if (pd.lookupEntry(name) != null) {
	    		pd.removeEntry(name);
	    		ui.sendMessage("The entry has been removed");
	    	} else {
	    		ui.sendMessage("The person is not in the directory.");
	    	}
		break;
	    case 3:
	    	pd.save();
		break;
	    case 4:
		return;
	    }
	}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	String fn = "csc220.txt";
	PhoneDirectory pd = new prog04.SortedDLLPD();
	UserInterface ui = new GUI();
	processCommands(fn, ui, pd);
    }
}
