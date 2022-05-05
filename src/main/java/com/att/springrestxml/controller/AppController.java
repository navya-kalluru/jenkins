package com.att.springrestxml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.springrestxml.model.Employee;
import com.att.springrestxml.service.Convert;
import com.att.springrestxml.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class AppController {
	Employee emp = new Employee();
	@Autowired
	EmployeeService empService;
	
	@GetMapping(value="/show/{eid}",produces= {"application/xml"})
	public Employee getEmployee(@PathVariable("eid") int eid) {
		return empService.show(eid);
	}
	
	@PostMapping(value="/add/emp",consumes= {"application/xml"},produces= {"application/xml"})
	public String addEmployee(@RequestBody Employee emp) {
		return empService.add(emp);
	}
	
	@DeleteMapping("/delete/{eid}")
	public String deleteEmployee(@PathVariable("eid") int eid) {
		return empService.delete(eid);
	}
	
	
	@PutMapping(value="update/emp",consumes={"application/xml"})
	public String updateOrSaveEmployee(@RequestBody Employee emp) {
		return empService.update(emp);
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello REST";
	}
}
