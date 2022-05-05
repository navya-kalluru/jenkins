package com.att.springrestxml.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import com.att.springrestxml.model.Employee;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Convert {
	public static void serializeToXML(Employee e) {
	    try {
	        XmlMapper xmlMapper = new XmlMapper();
	        Employee emp = new Employee();

	        // serialize our Object into XML string
	        emp.setEid(e.getEid());
			emp.setEname(e.getEname());
			emp.setDept(e.getDept());
	        String xmlString = "\n"+xmlMapper.writeValueAsString(emp);

	        // write to the console
	        System.out.println(xmlString);

	        // write XML string to file
	        File xmlOutput = new File("Employee.xml");
	        FileWriter fileWriter = new FileWriter(xmlOutput,true);
	        BufferedWriter bw=new BufferedWriter(fileWriter);
	        bw.write(xmlString);
	        bw.close();
	    } catch (IOException ex) {
	        // handle exception
	    }catch (Exception ex) {
	        // handle exception
	    }
	}
	public static Employee deserializeFromXML(int eid,String mode) {
		Employee emp = new Employee();
	    try {
	        XmlMapper xmlMapper = new XmlMapper();

	        // read file and put contents into the string
	        String readContent = new String(Files.readAllBytes(Paths.get("Employee.xml")));
	        //System.out.println(readContent);
	        if(mode.equals("show")) {
	        	String empContent = getParticularEid(eid,readContent);
	        	emp = xmlMapper.readValue(empContent, Employee.class);
	        }
	        else if(mode.equals("delete")) {
	        	String remainingFile = deleteEid(eid,readContent);
	        	File xmlOutput = new File("Employee.xml");
		        FileWriter fileWriter = new FileWriter(xmlOutput);
		        fileWriter.write(remainingFile);
		        fileWriter.close();
	        }else {
	        	String remainingFile = deleteEid(eid,readContent);
	        	File xmlOutput = new File("Employee.xml");
		        FileWriter fileWriter = new FileWriter(xmlOutput);
		        fileWriter.write(remainingFile);
		        fileWriter.close();
	        }
	        // deserialize from the XML into a Phone object
	        
	        //xmlMapper.readV
	        // Print object details
//	        System.out.println("Deserialized data: ");
//	        System.out.println("\tName: " + emp.getEid());
//	        System.out.println("\tMemory: " + emp.getEname());
//	        System.out.println("\tDisplay Size: " + emp.getDept());
	    } catch (IOException e) {
	        // handle the exception
	    }
	    return emp;
	}
	
	public static String getParticularEid(int eid,String readContent) {
		StringTokenizer st = new StringTokenizer(readContent,"\n");
        while(st.hasMoreElements()) {
        	String temp = st.nextToken();
        	System.out.println(temp);
        	if(temp.contains(Integer.toString(eid))) {
        		return temp;
        	}
        }
		return "notfound";
	}
	
	public static String deleteEid(int eid,String readContent) {
		StringTokenizer st = new StringTokenizer(readContent,"\n");
		String ans = "";
        while(st.hasMoreElements()) {
        	String temp = st.nextToken();
        	System.out.println(temp);
        	if(temp.contains(Integer.toString(eid))) {
        		continue;
        	}
        	ans+=temp+"\n";
        }
		return ans;
	}
}
