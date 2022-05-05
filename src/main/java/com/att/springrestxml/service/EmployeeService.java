package com.att.springrestxml.service;

import org.springframework.stereotype.Component;

import com.att.springrestxml.model.Employee;

@Component
public class EmployeeService {
	
	public String add(Employee e) {
		Convert.serializeToXML(e);
		return "Added Successfully";
	}
	
	public String delete(int eid) {
		Convert.deserializeFromXML(eid, "delete");
		return "Deleted Successfully";
	}
	
	public Employee show(int eid) {
		Employee emp = Convert.deserializeFromXML(eid, "show");
		return emp;
	}
	
	public String update(Employee e) {
		Convert.deserializeFromXML(e.getEid(), "update");
		add(e);
		return "Updated Successfully";
	}
}
