package com.datatoexcel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	private int empId;
	private String empName;
	private long empSalary;
	private String empAdress;
	
	

}
