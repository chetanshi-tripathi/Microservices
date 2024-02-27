package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Employee> getEmployee(String employeeId) {
		// TODO Auto-generated method stub

		Optional<Employee> obj1 = empRepo.findById(employeeId);

		if (obj1.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(obj1.get());
		}

		else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Employee> addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Optional<Employee> obj2 = empRepo.findById(employee.getEmployeeId());

		if (obj2.isPresent()) {
			return new ResponseEntity("Record already exists", HttpStatus.ALREADY_REPORTED);
		} else {
			empRepo.saveAndFlush(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> updateEmployee(String employeeId, Employee employee) {
		// TODO Auto-generated method stub

		Employee obj3 = empRepo.findById(employeeId).get();

		if (obj3.getEmployeeId().equals(employeeId)) {
			if (employee.getContactNo() != null)
				obj3.setContactNo(employee.getContactNo());
			if (employee.getDepartment() != null)
				obj3.setDepartment(employee.getDepartment());
			if (employee.getEmployeeName() != null)
				obj3.setEmployeeName(employee.getEmployeeName());
			empRepo.saveAndFlush(obj3);
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully updated");
		}

		else {
			return new ResponseEntity("Please verify the employeeId", HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> deleteEmployee(String employeeId) {
		// TODO Auto-generated method stub
		Optional<Employee> obj1 = empRepo.findById(employeeId);

		if (obj1.isPresent()) {
			empRepo.deleteById(employeeId);
			;
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
		} else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}
	}

	public List<Employee> getAllEmployees() {

		return empRepo.findAll();
	}

}
