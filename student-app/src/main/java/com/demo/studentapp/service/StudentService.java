package com.demo.studentapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.studentapp.entity.Student;
import com.demo.studentapp.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Student> getStudentDetails(String studentId) {
		// TODO Auto-generated method stub
		Optional<Student> obj1 = studentRepo.findById(studentId);
		if (obj1.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(obj1.get());
		} else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<String> addStudentDetails(Student student) {
		Optional<Student> obj1 = studentRepo.findById(student.getStudentId());
		if (obj1.isPresent()) {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Student already exists with same id");
		} else {
			studentRepo.saveAndFlush(student);
			return ResponseEntity.status(HttpStatus.CREATED).body("Student details successfully added");
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> deleteStudentDetails(String studentId) {

		Optional<Student> obj1 = studentRepo.findById(studentId);
		if (obj1.isPresent()) {
			studentRepo.deleteById(studentId);
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
		} else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> updateStudentDetails(String studentId, Student student) {

		Student studentObj = studentRepo.findById(studentId).get();

		if (studentObj.getStudentId().equals(studentId)) {

			studentObj.setStudentName(student.getStudentName());
			studentObj.setEmailId(student.getEmailId());

			studentRepo.saveAndFlush(studentObj);
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully updated");

		}

		else {
			return new ResponseEntity("Please verify the employeeId", HttpStatus.NOT_FOUND);
		}
	}

	public List<Student> getAllStudentDetails() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

}
