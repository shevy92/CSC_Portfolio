

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorImpl implements Doctor {

	
	public DoctorImpl (String fullName, String SSN, Date DOB, String medSpecialty2) {
		fullName.toString();
		medSpecialty = medSpecialty2.toString();
		splitName = fullName.split(" ");
		dateDOB = DOB;
		SSN2 = SSN;

	}
	
	//String medSpecialty2 = medSpecialty;
	String[] splitName;
	String medSpecialty;
	int doctorID;
	Date dateDOB;
	String SSN2;
	
	public void setID(int ID) {
		doctorID = ID;
	}
	
	@Override
	public int getDoctorID() {
		return doctorID;
	}

	@Override
	public String getFirstName() {
		String firstName = splitName[0]; 
		return firstName;
	}

	@Override
	public String getLastName() {
		String lastName = splitName[1]; 
		return lastName;
	}

	@Override
	public MedicalSpecialty getSpecialty () {
		return MedicalSpecialty.getFromString(medSpecialty);
	}
	
	public float getAge() {
		Date todaysDate = new Date();
		long patAge = todaysDate.getTime() - dateDOB.getTime();
	    float printAge = (patAge / (1000 * 60 * 60 * 24)) /365;
		return printAge;
	}
	
	public int getPatientID() {
		return doctorID;
	}
	
public boolean equals (Doctor doc2) {
		
	int compare1 = this.getDoctorID() - (doc2.getDoctorID());
	int compare2 = this.getLastName().compareTo(doc2.getLastName());
	int compare3 = this.getFirstName().compareTo(doc2.getFirstName());
	int compare4 = this.getSSN().compareTo(doc2.getSSN());
	int compare5 = this.getDOB().compareTo(doc2.getDOB());
	int compare6 = this.getSpecialty().compareTo(doc2.getSpecialty());
		
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
		if (compare6 != 0) {
			return false;
		}
		
		hashCode(this.hashCode(), doc2.hashCode());
		return true;
	}

public int hashCode (int HC1, int HC2){
	int total;
	total = HC1 + HC2;
	return total;
}

SimpleDateFormat DOBformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

@Override
public String getDOB() {
	String stringDOB;
    stringDOB = DOBformat.format(dateDOB);	
	return stringDOB;
}

@Override
public String getSSN() {
	return SSN2;
}


public int compareTo(Doctor other) {
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


public DoctorImpl(String[] splitName, Date dateDOB, String ssn2) {
	super();
	this.splitName = splitName;
	this.dateDOB = dateDOB;
	SSN2 = ssn2;
}
}
