/*
 * DirectoryEntry.java
 *
 * Created on August 29, 2005, 4:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package prog02;

/** The DirectoryEntry contains the name and number, both
 *  stored as strings. The name is not changable.
 *  @author Koffman & Wolfgang
 */

public class DirectoryEntry {
    /** The name of the indiviual represented in the directory. */
    private String name;
    
    /** The home number for this individual. */
    private String number;
    
    /** Creates a new instance of DirectoryEntry
     @param name Name of Person 
     @param number Phone number of Person */
    public DirectoryEntry (String name, String number) {
        this.name = name;
        this.number = number;
    }
    
    /** Retrieves the name
     @return the name of the individual
     */
    public String getName () {
        return name;
    }
    
    /** Retrieves the number
     @return the number of the individual
     */
    public String getNumber () {
        return number;
    }
    
    /** Sets the number to a different value.
     @param number the new number to set it to
     */
    public void setNumber (String number) {
        this.number = number;
    }
}

