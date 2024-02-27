package com.demo.studentapp.controller;

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

import com.demo.studentapp.entity.Student;
import com.demo.studentapp.feign.AddressProxy;
import com.demo.studentapp.response.Address;
import com.demo.studentapp.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService stuserv;

	@Autowired
	AddressProxy addProxy;

	@GetMapping("/greet")
	public String healthCheck() {
		return "Hello World";
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentDetails(@PathVariable String studentId) {
		return stuserv.getStudentDetails(studentId);
	}

	@PostMapping("/add-student")
	public ResponseEntity<String> addStudentDetails(@RequestBody Student student) {
		return stuserv.addStudentDetails(student);

	}

	@PutMapping("/update-student/{studentId}")
	public ResponseEntity<String> updateStudentDetails(@PathVariable String studentId, @RequestBody Student student) {
		return stuserv.updateStudentDetails(studentId, student);
	}

	@DeleteMapping("/delete-student/{studentId}")
	public ResponseEntity<String> deleteStudentDetails(@PathVariable String studentId) {
		return stuserv.deleteStudentDetails(studentId);
	}

	@GetMapping("/get-all")
	public List<Student> getAllStudentDetails() {

		return stuserv.getAllStudentDetails();
	}

	/*------------------------------
	 * ENDPOINTS FOR ADDRESS SERVICE
	 * -----------------------------	
	 */

	@GetMapping("/address/{addressId}")
	public ResponseEntity<Address> getAddressByAddressId(@PathVariable Long addressId) {

		return addProxy.getAddressByAddressId(addressId);
	}

	@GetMapping("/address/{personId}/{personName}")
	public ResponseEntity<Address> getAddressByPerson(@PathVariable("personId") String personId,
			@PathVariable("personName") String personName) {
		if (personId.startsWith("STU")) {
			return addProxy.getAddressByPerson(personId, personName);
		}

		else {
			return null;
		}
	}

}
