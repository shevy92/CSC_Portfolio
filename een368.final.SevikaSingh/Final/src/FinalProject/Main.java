
package FinalProject;


import javax.swing.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import java.awt.*;
import java.awt.event.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;

import java.awt.Frame; 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends javax.swing.JFrame {

	 String fName;
     String mInitial;
     String lName;
     String DOB, DOB1, DOB2, DOB3;
     String SSN;
     String specialty;
     String t1,t2,t3;
     String date, time;
     
     int PCount = 0;
     int DCount = 0;
     int VCount = 0;
     
     Patient [] patientList = new Patient [100];
     Doctor [] doctorList = new Doctor [100];
     Visit [] visitList = new Visit [100];
     
	
    public Main() {
    	
    	try {
    		fileOpen();
    	} catch (Exception e) {
    		System.out.println("File could not be opened");
    	}
        initComponents();
    }
                        
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setName(""); 
        jInternalFrame1.setVisible(true);

        jButton1.setLabel("Enter New Patient");
        jButton1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jButton1ComponentShown(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("List All Visits");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("All Visits for a Patient");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("All Upcoming  Visits");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("All Upcoming Visits for a Doctor");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setText("All Visits for a Doctor");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setLabel("Enter New Doctor");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Enter Visit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame1Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jInternalFrame1Layout.createSequentialGroup()
                        .add(240, 240, 240)
                        .add(jButton7))
                    .add(jInternalFrame1Layout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jInternalFrame1Layout.createSequentialGroup()
                                .add(jButton2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jButton3)
                                .add(18, 18, 18)
                                .add(jButton4))
                            .add(jInternalFrame1Layout.createSequentialGroup()
                                .add(jButton5)
                                .add(79, 79, 79)
                                .add(jButton6))
                            .add(jInternalFrame1Layout.createSequentialGroup()
                                .add(jButton1)
                                .add(45, 45, 45)
                                .add(jButton8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jButton9)))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton8)
                    .add(jButton9))
                .add(31, 31, 31)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton3)
                    .add(jButton4)
                    .add(jButton2))
                .add(34, 34, 34)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton6)
                    .add(jButton5))
                .add(32, 32, 32)
                .add(jButton7)
                .add(23, 23, 23))
        );

        jButton8.getAccessibleContext().setAccessibleName("Enter New Doctor");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        pack();
    }                       

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	fName = JOptionPane.showInputDialog(null, "First Name");
        mInitial = JOptionPane.showInputDialog(null, "Middle Initial");
        lName = JOptionPane.showInputDialog(null, "Last Name");
        DOB1 = JOptionPane.showInputDialog(null, "Date of Birth (Month in digits)");
        DOB2 = JOptionPane.showInputDialog(null, "Date of Birth (Day in digits)");
        DOB3 = JOptionPane.showInputDialog(null, "Date of Birth (Year)");
        SSN = JOptionPane.showInputDialog(null, "Social Security Number");
        
        int n = 5;
        n = JOptionPane.showConfirmDialog(null,
        	    "Are you sure you want to add this patient?",
        	    "Confirm",
        	    JOptionPane.YES_NO_OPTION);
        System.out.println(n);
        String fullName = fName + " " + mInitial + " " + lName;
        DOB = DOB1 +"/" +DOB2+"/"+DOB3;
        

        if (n == 0) {
        Patient newP = new PatientImpl (PCount, fullName, DOB, SSN);
        patientList[PCount] = newP;
        PCount++;
        
        XStream xstream = new XStream();
        xstream.alias("patient", Patient.class);
        String xml = xstream.toXML(newP);
     
        try {
        	File file = new File("XML Files/DataFile.xml");
        	
        	StringBuffer buffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new FileReader("./" + "XML Files/DataFile.xml"));
            String temp;
		    	while ((temp = in.readLine()) != null) {
		    		if (!temp.equals("</root>")) {
		                buffer.append(temp + "\n");
		    		}
		    	}
            in.close();
                   	
        	FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buffer +"\n" + xml + "\n" + "</root>" + "\n");
			bw.close();
        } catch (Exception e) {
			e.printStackTrace();
		}
        }
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	System.exit(0);
    }                                        

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	Date todaysDate = new Date();
    	Date vDate;
    	int compare = 0;
    	
    	JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Choose Doctor:"));
        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        
        for (int i = 0; i < doctorList.length; i++) {
        	if (doctorList[i] != null) {
        		model2.addElement(doctorList[i].getName());
        	}
        }
        
        JComboBox comboBox2 = new JComboBox(model2);
        panel2.add(comboBox2);
        
        int result2 = JOptionPane.showConfirmDialog(null, panel2, "Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String doc = comboBox2.getSelectedItem().toString();

    	for (int i = 0; i <visitList.length; i++) {
    		if (visitList[i] != null) {
    			if (visitList[i].getDoctor().equals(doc)){
    				try {
        				vDate = df.parse(visitList[i].getDate());
        				compare = todaysDate.compareTo(vDate);
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			if (compare < 0) {
        				javax.swing.JOptionPane.showMessageDialog(null, "Visit " + i + ": \n" +"\tPatient: " +visitList[i].getPatient() + "\n\tDoctor: " + visitList[i].getDoctor() + "\n\tDate: " + visitList[i].getDate() + "\n\tTime: " + visitList[i].getTime());
        			}
    			}
    		}
    	}
    }                                        

    private void jButton1ComponentShown(java.awt.event.ComponentEvent evt) {                                        

    }                                       

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Choose Patient:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for (int i = 0; i < patientList.length; i++) {
        	if (patientList[i] != null) {
        		model.addElement(patientList[i].getName());
        	}
        }
        
        
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String pat = comboBox.getSelectedItem().toString();
    	
    	for (int i = 0; i <visitList.length; i++) {
    		if (visitList[i] != null) {
    			if (visitList[i].getPatient().equals(pat)){
    				javax.swing.JOptionPane.showMessageDialog(null, "Visit " + i + ": \n" +"\tPatient: " +visitList[i].getPatient() + "\n\tDoctor: " + visitList[i].getDoctor() + "\n\tDate: " + visitList[i].getDate() + "\n\tTime: " + visitList[i].getTime());
    			}
    		}
    	}
    }                                        

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	
    	fName = JOptionPane.showInputDialog(null, "First Name");
        mInitial = JOptionPane.showInputDialog(null, "Middle Initial");
        lName = JOptionPane.showInputDialog(null, "Last Name");
        DOB1 = JOptionPane.showInputDialog(null, "Date of Birth (Month in digits)");
        DOB2 = JOptionPane.showInputDialog(null, "Date of Birth (Day in digits)");
        DOB3 = JOptionPane.showInputDialog(null, "Date of Birth (Year)");
        SSN = JOptionPane.showInputDialog(null, "Social Security Number");
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Choose Doctor Specialty:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("GENERAL_MEDICINE");
        model.addElement("PEDIATRICS");
        model.addElement("ONCOLOGY");
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Specialty", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        specialty = comboBox.getSelectedItem().toString();
        
        int n = 5;
        n = JOptionPane.showConfirmDialog(null,
        	    "Are you sure you want to add this doctor?",
        	    "Confirm",
        	    JOptionPane.YES_NO_OPTION);
        
        if (n ==0) {
        String fullName = fName + " " + mInitial + " " + lName;
        DOB = DOB1 +"/" +DOB2+"/"+DOB3;
        
        	if (DCount == 0) {
        		DCount = 1;
        	}
        
        Doctor newD = new DoctorImpl (DCount, fullName, DOB, SSN, specialty);
        doctorList[DCount] = newD;
        DCount++;
        
        XStream xstream = new XStream();
        xstream.alias("doctor", Doctor.class);
        String xml = xstream.toXML(newD);
     
        try {
        	File file = new File("XML Files/DataFile.xml");
        	
        	StringBuffer buffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new FileReader("./" + "XML Files/DataFile.xml"));
            String temp;
		    	while ((temp = in.readLine()) != null) {
		    		if (!temp.equals("</root>")) {
		                buffer.append(temp + "\n");
		    		}
		    	}
            in.close();
                   	
        	FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buffer +"\n" + xml + "\n" + "</root>" + "\n");
			bw.close();
        } catch (Exception e) {
			e.printStackTrace();
		}
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	/*
    	String [] headers = {"Patient", "Doctor", "Visit Date", "Time"};
    	
    	Object [][] rows = {};
    	for (int i = 0; i <visitList.length; i++) {
    		if (visitList[i] != null) {
    			rows[i-1][i] = visitList[i].getPatient(), visitList[i].getDoctor(), visitList[i].getDate(), visitList[i].getTime()};
    		}
    	}
    	
    	JTable table = new JTable(rows, headers);
    	
    	JFrame frame = new JFrame("All Visits");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        ActionListener printAction = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
              table.print();
            } catch (Exception pe) {
              System.err.println("Error printing: " + pe.getMessage());
            }
          }
        };
        //button.addActionListener(printAction);
        //frame.add(button, BorderLayout.SOUTH);
        frame.setSize(300, 150);
        frame.setVisible(true);
      }
    	*/
    	
    	
    	for (int i = 0; i <visitList.length; i++) {
    		if (visitList[i] != null) {
    			javax.swing.JOptionPane.showMessageDialog(null, "Visit " + i + ": \n" +"\tPatient: " +visitList[i].getPatient() + "\n\tDoctor: " + visitList[i].getDoctor() + "\n\tDate: " + visitList[i].getDate() + "\n\tTime: " + visitList[i].getTime());
    		}
    	}
    	
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	Date todaysDate = new Date();
    	Date vDate;
    	int compare = 0;
    	
    	for (int i = 0; i <visitList.length; i++) {
    		
    		if (visitList[i] != null) {
    			try {
    				vDate = df.parse(visitList[i].getDate());
    				compare = todaysDate.compareTo(vDate);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			if (compare < 0) {
    				javax.swing.JOptionPane.showMessageDialog(null, "Visit " + i + ": \n" +"\tPatient: " +visitList[i].getPatient() + "\n\tDoctor: " + visitList[i].getDoctor() + "\n\tDate: " + visitList[i].getDate() + "\n\tTime: " + visitList[i].getTime());
    			}
    		}
    	}	
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	 JPanel panel2 = new JPanel();
         panel2.add(new JLabel("Choose Doctor:"));
         DefaultComboBoxModel model2 = new DefaultComboBoxModel();
         
         for (int i = 0; i < doctorList.length; i++) {
         	if (doctorList[i] != null) {
         		model2.addElement(doctorList[i].getName());
         	}
         }
         
         JComboBox comboBox2 = new JComboBox(model2);
         panel2.add(comboBox2);
         
         int result2 = JOptionPane.showConfirmDialog(null, panel2, "Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         String doc = comboBox2.getSelectedItem().toString();

     	for (int i = 0; i <visitList.length; i++) {
     		if (visitList[i] != null) {
     			if (visitList[i].getDoctor().equals(doc)){
     				javax.swing.JOptionPane.showMessageDialog(null, "Visit " + i + ": \n" +"\tPatient: " +visitList[i].getPatient() + "\n\tDoctor: " + visitList[i].getDoctor() + "\n\tDate: " + visitList[i].getDate() + "\n\tTime: " + visitList[i].getTime());
     			}
     		}
     	}
    }                                        

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	String pat; 
    	String doc;
    	
    //Choose Patient
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Choose Patient:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for (int i = 0; i < patientList.length; i++) {
        	if (patientList[i] != null) {
        		model.addElement(patientList[i].getName());
        	}
        }
        
        
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        pat = comboBox.getSelectedItem().toString();
        
    //Choose Doctor
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Choose Doctor:"));
        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        
        for (int i = 0; i < doctorList.length; i++) {
        	if (doctorList[i] != null) {
        		model2.addElement(doctorList[i].getName());
        	}
        }
        
        JComboBox comboBox2 = new JComboBox(model2);
        panel2.add(comboBox2);
        
        int result2 = JOptionPane.showConfirmDialog(null, panel2, "Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        doc = comboBox2.getSelectedItem().toString();

        
        //Get Date
        DOB1 = JOptionPane.showInputDialog(null, "Appointment Date (Month in digits)");
        DOB2 = JOptionPane.showInputDialog(null, "Appointment Date (Day in digits)");
        DOB3 = JOptionPane.showInputDialog(null, "Appointment Date (Year)");
        
        date = DOB1 + "/" + DOB2 + "/" + DOB3;
        
        //Get Time
        t1 = JOptionPane.showInputDialog(null, "Appointment Time (Hour)");
        t2 = JOptionPane.showInputDialog(null, "Appointment Time (Minute)");
        t3 = JOptionPane.showInputDialog(null, "Appointment Time (AM or PM?)");
        
        
        int n = 5;
        n = JOptionPane.showConfirmDialog(null,
        	    "Are you sure you want to add this visit?",
        	    "Confirm",
        	    JOptionPane.YES_NO_OPTION);
        
        if (n ==0) {
        time = t1 + ":" + t2 + " " + t3;
       
        
    	if (VCount == 0) {
    		VCount = 1;
    	}
    
    	Visit newV = new VisitImpl (VCount, pat, doc, date, time);
    	visitList[VCount] = newV;
    	VCount++;
    	
    	XStream xstream = new XStream();
        xstream.alias("visit", Visit.class);
        String xml = xstream.toXML(newV);
     
        try {
        	File file = new File("XML Files/DataFile.xml");
        	
        	StringBuffer buffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new FileReader("./" + "XML Files/DataFile.xml"));
            String temp;
		    	while ((temp = in.readLine()) != null) {
		    		if (!temp.equals("</root>")) {
		                buffer.append(temp + "\n");
		    		}
		    	}
            in.close();
                   	
        	FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buffer +"\n" + xml + "\n" + "</root>" + "\n");
			bw.close();
        } catch (Exception e) {
			e.printStackTrace();
		}
        }
    }                                        
    
    
 
    public void fileOpen () throws Exception {
    	//Open XML File
    	BufferedReader br;
    	try {
			br = new BufferedReader(new FileReader("XML Files/DataFile.xml"));
		
			XMLInputFactory factory = XMLInputFactory.newInstance();
		    
		    XMLEventReader reader = factory.createXMLEventReader(br);
		    XMLEvent event;
		  //Read Patients  
		    while(reader.hasNext()) {
		    	event = reader.nextEvent();	
		    	
		    	if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
		    		StartElement startElement = event.asStartElement();
		    		
		    		
		    		if (startElement.getName().getLocalPart().equals("FinalProject.PatientImpl")) {
		    			
		    			
		    			int id = 0;
		    			String name = "unknown";
		    			String dob = "00/00/0000";
		    			String ssn = "unknown";
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("FinalProject.PatientImpl")) {
		    				event = reader.nextEvent();
		    				
		    				
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("name")) {
		    					event = reader.nextEvent();
		    					name = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("DOB")) {
		    					event = reader.nextEvent();
		    					dob = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("SSN")) {
		    					event = reader.nextEvent();
		    					ssn = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("ID")) {
		    					event = reader.nextEvent();
		    					id = Integer.parseInt(event.asCharacters().getData());
		    				}
		    			}
		    			
		    			if (id >= PCount) {
		    				PCount = id +1;
		    			}
		    			
		    			Patient newP = new PatientImpl (id, name, dob, ssn);
		    	        patientList[id] = newP;
		    		}
		    		
		    		if (startElement.getName().getLocalPart().equals("FinalProject.DoctorImpl")) {
		    			
		    			int id = 0;
		    			String name = "unknown";
		    			String dob = "00/00/0000";
		    			String ssn = "unknown";
		    			String spec = "unknown";
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("FinalProject.DoctorImpl")) {
		    				event = reader.nextEvent();
		    				
		    				
		    				
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("name")) {
		    					event = reader.nextEvent();
		    					name = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("DOB")) {
		    					event = reader.nextEvent();
		    					dob = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("SSN")) {
		    					event = reader.nextEvent();
		    					ssn = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("ID")) {
		    					event = reader.nextEvent();
		    					id = Integer.parseInt(event.asCharacters().getData());
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("spec")) {
		    					event = reader.nextEvent();
		    					spec = event.asCharacters().getData();
		    				}
		    			}
		    			
		    			if (id >= DCount) {
		    				DCount = id +1;
		    			}
		    			
		    			Doctor newD = new DoctorImpl (id, name, dob, ssn, spec);
		    	        doctorList[id] = newD;
		    		}
		    		
			//Read visits

		    		if (startElement.getName().getLocalPart().equals("FinalProject.VisitImpl")) {
		    			
		    			int id = 0;
		    			String patient = "unknown";
		    			String doctor = "unknown";
		    			String date = "unknown";
		    			String time = "unknown";
		    			
		    			while (event.getEventType() != XMLStreamConstants.END_ELEMENT
		    					|| !event.asEndElement().getName().getLocalPart().equals("FinalProject.VisitImpl")) {
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
		    						&& event.asStartElement().getName().getLocalPart().equals("date")) {
		    					event = reader.nextEvent();
		    					date = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("time")) {
		    					event = reader.nextEvent();
		    					time = event.asCharacters().getData();
		    				}
		    				if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
		    						&& event.asStartElement().getName().getLocalPart().equals("ID")) {
		    					event = reader.nextEvent();
		    					id = Integer.parseInt(event.asCharacters().getData());
		    				}
		    				
		    			}
		    			
		    			if (id >= VCount) {
		    				VCount = id +1;
		    			}
		    			
		    			Visit newV = new VisitImpl (id, patient, doctor, date, time);
		    	        visitList[id] = newV;
		    	        
		    		}
		    		
		    	}
		    	}
		    
		    
    	} catch (FileNotFoundException e) {
			System.out.println ("File not found!");
		}

    }
    
    public static void main(String args[]) throws Exception{
	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
                      
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
                      
}
