package FinalProject;

public class VisitImpl implements Visit{

	String patient;
	String doctor;
	String date;
	String time;
	int ID;
	
	public VisitImpl (int ID, String patient, String doctor, String date, String time) {
		this.patient = patient;
		this.doctor = doctor;
		this.date = date;
		this.time = time;
		this.ID = ID;
	}
	
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	@Override
	public String getDoctor() {
		// TODO Auto-generated method stub
		return doctor;
	}
	@Override
	public String getPatient() {
		// TODO Auto-generated method stub
		return patient;
	}
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return time;
	}
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	
	
	
}
