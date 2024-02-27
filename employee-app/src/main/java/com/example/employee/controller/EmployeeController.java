package com.example.employee.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.feign.AddressProxy;
import com.example.employee.response.Address;
import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empserv;
	
	@Autowired
	AddressProxy addProxy;

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) {
		return empserv.getEmployee(employeeId);
	}

	@PostMapping("/add-employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return empserv.addEmployee(employee);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
		return empserv.updateEmployee(employeeId, employee);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
		return empserv.deleteEmployee(employeeId);
	}

	@GetMapping("/all-employees")
	public List<Employee> getAllEmployees() {
		return empserv.getAllEmployees();
	}

	/*------------------------------
	 * ENDPOINTS FOR ADDRESS SERVICE
	 * -----------------------------	
	 */

	@GetMapping("/address/{addressId}")
	public ResponseEntity<com.example.employee.response.Address> getAddressByAddressId(@PathVariable Long addressId) {

		return addProxy.getAddressByAddressId(addressId);
	}

	@GetMapping("/address/{personId}/{personName}")
	public ResponseEntity<Address> getAddressByPerson(@PathVariable("personId") String personId,
			@PathVariable("personName") String personName) {
		if (personId.startsWith("EMP")) {
			return addProxy.getAddressByPerson(personId, personName);
		}

		else {
			return null;
		}
	}
}
