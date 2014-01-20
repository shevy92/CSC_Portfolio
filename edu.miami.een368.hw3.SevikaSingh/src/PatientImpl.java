

import java.text.SimpleDateFormat;
import java.util.Date;


public class PatientImpl implements Patient {

	public PatientImpl (String fullName, String SSN2, Date DOB) {
		fullName.toString();
	    splitName = fullName.split(" ");
	    DOB2 = DOB;
	    SSN = SSN2;
	}
	
	String[] splitName;
	int patientID;
	String SSN;
	Date DOB2;
	
	public void setID(int ID) {
		patientID = ID;
	}
	
	public float getAge() {
		Date todaysDate = new Date();
		long patAge = todaysDate.getTime() - DOB2.getTime();
	    float printAge = (patAge / (1000 * 60 * 60 * 24)) /365;
		return printAge;
	}

	SimpleDateFormat DOBformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	
	
	public String getDOB() {
		String stringDOB;
	    stringDOB = DOBformat.format(DOB2);	
		return stringDOB;
	}

	public String getFirstName() {
		String firstName = splitName[0]; 
		return firstName;
	}

	public String getLastName() {
		String lastName = splitName[1]; 
		return lastName;
	}

	public int getPatientID() {
		return patientID;
	}

	public String getSSN() {
		return SSN;
	}
	
	public boolean equals (Patient patient2) {
		
		int compare1 = this.getPatientID() - patient2.getPatientID();
		int compare2 = this.getLastName().compareTo(patient2.getLastName());
		int compare3 = this.getFirstName().compareTo(patient2.getFirstName());
		int compare4 = this.getSSN().compareTo(patient2.getSSN());
		int compare5 = this.getDOB().compareTo(patient2.getDOB());	
		
		if (compare1 != 0) {
			return false;
		}
		if (compare2 != 0) {
			return false;
		}
		if (compare3 != 0) {
			return false;
		}
		if (compare4 != 0) {
			return false;
		}
		if (compare5 != 0) {
			return false;
		}
		hashCode(this.hashCode(), patient2.hashCode());
		return true;
	}
	
	public int hashCode (int HC1, int HC2){
		int total;
		total = HC1 + HC2;
		return total;
	}
	
	public int compareTo(Patient other) {
		//do for all things to be compared
		int compareInt = this.getLastName().compareTo(other.getLastName());
		if (compareInt != 0) {
			return compareInt;
		}
		compareInt = this.getFirstName().compareTo(other.getFirstName());
		if (compareInt != 0) {
			return compareInt;
		}
		compareInt = this.getDOB().compareTo(other.getDOB());
		if (compareInt != 0) {
			return compareInt;
		}
		compareInt = this.getSSN().compareTo(other.getSSN());
		if (compareInt != 0) {
			return compareInt;
		}
		
		return 0;
	}
	
}
