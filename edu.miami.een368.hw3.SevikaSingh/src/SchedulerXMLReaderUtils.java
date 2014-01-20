
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.*;
import java.io.Reader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.namespace.QName;

import java.util.Date;

public class SchedulerXMLReaderUtils {

	
	public static SchedulerData readSchedulingXML (String receiveFile) throws Exception{
		
		BufferedReader br; 
		SchedulerData SchedulerData1= new SchedulerData();
		HashMap <Integer,Patient> patientIdMap = new HashMap <Integer,Patient>();
		HashMap <Integer,Doctor> doctorIdMap = new HashMap <Integer,Doctor>();
		
		try {
			br = new BufferedReader(new FileReader(receiveFile));
		
			XMLInputFactory factory = XMLInputFactory.newInstance();
		    
		    XMLEventReader reader = factory.createXMLEventReader(br);
		    XMLEvent event;
		    
		    while(reader.hasNext()) {
		    	event = reader.nextEvent();	
		    	
		    	if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
		    		StartElement startElement = event.asStartElement();
		    		
		    		
		    		if (startElement.getName().getLocalPart().equals("patient")) {
		    			//read in patient
		    			String firstName = "unknown";
		    			String lastName = "unknown";
		    			String dob = "00/00/0000";
		    			String ssn = "unknown";
		    			String id = "0";
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("patient")) {
		    				event = reader.nextEvent();
		    				
		    				
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("firstName")) {
		    					event = reader.nextEvent();
		    					firstName = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("lastName")) {
		    					event = reader.nextEvent();
		    					lastName = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("dob")) {
		    					event = reader.nextEvent();
		    					dob = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("ssn")) {
		    					event = reader.nextEvent();
		    					ssn = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("id")) {
		    					event = reader.nextEvent();
		    					id = event.asCharacters().getData();
		    				}
		    			}
		    			
		    			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		    			PatientImpl NP = new PatientImpl(firstName + " " + lastName, ssn, df.parse(dob));
		    			NP.setID(Integer.parseInt(id));
		    			SchedulerData1.patientCollection.add(NP);
		    			patientIdMap.put(NP.getPatientID(), NP);
		    		}
		    		
		    		else if (startElement.getName().getLocalPart().equals("doctor")){
		    			//event = reader.nextEvent();
		    			
		    			String firstName = "unknown";
		    			String lastName = "unknown";
		    			String dob = "00/00/0000";
		    			String ssn = "unknown";
		    			String id = "0";
		    			String medicalSpecialty = "unknown";
		    			
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("doctor")) {
		    				
		    				event = reader.nextEvent();
		    				
		    			
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("firstName")) {
		    					event = reader.nextEvent();
		    					firstName = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("lastName")) {
		    					event = reader.nextEvent();
		    					lastName = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("dob")) {
		    					event = reader.nextEvent();
		    					dob = event.asCharacters().getData().trim();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("ssn")) {
		    					event = reader.nextEvent();
		    					ssn = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("id")) {
		    					event = reader.nextEvent();
		    					id = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("medicalSpecialty")) {
		    					event = reader.nextEvent();
		    					medicalSpecialty = event.asCharacters().getData();
		    				}
		    			}
		    			
		    			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		    			DoctorImpl ND = new DoctorImpl(firstName + " " + lastName, ssn, df.parse(dob), medicalSpecialty);
		    			ND.setID(Integer.parseInt(id));
		    			SchedulerData1.doctorCollection.add(ND);
		    			doctorIdMap.put(ND.getDoctorID(), ND);
		    		}
		    		else if (startElement.getName().getLocalPart().equals("visit")) {
		    			
		    			String patient = "unknown";
	    				String doctor = "unknown";
	    				String visitDate = "00000000";
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("visit")) {
		    				event = reader.nextEvent();
		    				
		    				
		    				
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("patient")) {
		    					event = reader.nextEvent();
		    					patient = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("doctor")) {
		    					event = reader.nextEvent();
		    					doctor = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("visitDate")) {
		    					event = reader.nextEvent();
		    					visitDate = event.asCharacters().getData();
		    				}
		    				
		    			}
		    			int PS = Integer.parseInt(patient);
	    				int DS = Integer.parseInt(doctor);
	    			
	    			
	    			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    			Patient pToSend = patientIdMap.get(PS);
	    			Doctor dToSend = (Doctor) doctorIdMap.get(DS);
	    			Visit<Patient, Doctor> NV = new VisitImpl<Patient, Doctor> (pToSend, dToSend, df.parse(visitDate));
	    			SchedulerData1.visitCollection.add(NV);
		    		}
		    	}
		    	
		    }
		    
		    if (reader.hasNext()) {
		    	
		    }
		    
		   
		} catch (FileNotFoundException e) {
			System.out.println ("File not found!");
		}
			
		return SchedulerData1;
	}
	
}
