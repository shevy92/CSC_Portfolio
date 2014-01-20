

public interface Doctor extends Patient{

	 public enum MedicalSpecialty {
		  GENERAL_MEDICINE, 
		  PEDIATRICS,
		  ONCOLOGY,
		  ERROR;
		  
		  public static Doctor.MedicalSpecialty getFromString (String name) {
			  String useName = name;
			  MedicalSpecialty specToReturn = MedicalSpecialty.valueOf(useName);
			  if (specToReturn.toString().equals(useName)) {
				  return specToReturn;
			  }
			  return ERROR;
		  }
		  
	  }
		
	
	public int getDoctorID();
	public String getLastName();
	public String getFirstName();
	public MedicalSpecialty getSpecialty();
	public String getSSN();
	public String getDOB();
	
}
