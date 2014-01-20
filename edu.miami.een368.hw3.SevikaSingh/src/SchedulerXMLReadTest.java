import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Document;


public class SchedulerXMLReadTest {

	
	public static void main(String[] args) {
		try {
			SchedulerData fileToUse = SchedulerXMLReaderUtils.readSchedulingXML("XML Files/schedulerData.xml");	

			for (int i=0; i<fileToUse.visitCollection.size(); i++) {
				
				 SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				 Date todaysDate = new Date();
				 long diff =  (df.parse(fileToUse.visitCollection.get(i).getDate()).getTime()) - todaysDate.getTime();
			     float daysUntil = diff / (1000 * 60 * 60 * 24);
				
				 System.out.printf("Visit Date: \t\t%s\n", fileToUse.visitCollection.get(i).getDate());
				 System.out.printf("Doctor: \t\t%s %s\n", fileToUse.visitCollection.get(i).getHost().getFirstName(), fileToUse.visitCollection.get(i).getHost().getLastName());
				 System.out.printf("Specialty: \t\t%s \n", fileToUse.visitCollection.get(i).getHost().getSpecialty().toString());
				 System.out.printf("Days Until Visit: \t\t%1.0f \n", daysUntil);
				 System.out.printf("Patient: \n");
				 System.out.printf("\tFirst Name: \t\t\t%s \n", fileToUse.visitCollection.get(i).getVisitor().getFirstName());
				 System.out.printf("\tLast Name: \t\t\t%s\n", fileToUse.visitCollection.get(i).getVisitor().getLastName());
				 System.out.printf("\tSSN: \t\t\t%s\n", fileToUse.visitCollection.get(i).getVisitor().getSSN());
				 System.out.printf("\tAge: \t\t\t%s\n", fileToUse.visitCollection.get(i).getVisitor().getAge());
				 
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
