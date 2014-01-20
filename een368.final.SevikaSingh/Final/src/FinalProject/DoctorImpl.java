package FinalProject;

public class DoctorImpl implements Doctor {

	int ID;
	String name;
	String DOB;
	String SSN;
	String spec;
	
	public DoctorImpl (int ID, String name, String DOB, String SSN, String spec) {
		this.ID = ID;
		this.name = name;
		this.DOB = DOB;
		this.SSN = SSN;
		this.spec = spec;
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
	public String getSpec () {
		return spec;
	}
	
}
