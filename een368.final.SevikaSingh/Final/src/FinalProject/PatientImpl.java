package FinalProject;


public class PatientImpl implements Patient{

	int ID;
	String name;
	String DOB;
	String SSN;
	
	public PatientImpl (int ID, String name, String DOB, String SSN) {
		this.ID = ID;
		this.name = name;
		this.DOB = DOB;
		this.SSN = SSN;
	}
	
	public int getID () {
		return ID;
	}
	public String getName () {
		return name;
	}
	public String getDOB () {
		return DOB;
	}
	public String getSSN () {
		return SSN;
	}
}
