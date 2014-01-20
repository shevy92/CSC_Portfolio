import java.util.ArrayList;
import java.util.List;


public class SchedulerData {

	 List <Patient> patientCollection = new ArrayList <Patient>();
	 List <Doctor> doctorCollection = new ArrayList <Doctor>();
	 List <Visit<Patient, Doctor>> visitCollection = new ArrayList <Visit<Patient,Doctor>>();
	
	 
	 
	public SchedulerData () {
		patientCollection = this.patientCollection;
		doctorCollection = this.doctorCollection;
		visitCollection = this.visitCollection;
	}
	
	public List getPList(){
		return patientCollection;
	}	
	public List getDList(){
		return doctorCollection;
	}
	public List getVList(){
		return visitCollection;
	}
	
	public void addPatient(Patient p){
		patientCollection.add(p);
		return;
	}
	public void addDoctor(Doctor d){
		doctorCollection.add(d);
		return;
	}
	public void addVisit(Visit<Patient, Doctor> v){
		visitCollection.add(v);
		return;
	}


}
