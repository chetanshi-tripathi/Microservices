package com.example.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Employee {

	@Id
	private String employeeId;
	private String employeeName;
	private String contactNo;
	private String department;

}
