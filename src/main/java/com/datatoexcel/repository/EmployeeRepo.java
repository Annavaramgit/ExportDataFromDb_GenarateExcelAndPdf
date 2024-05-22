package com.datatoexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datatoexcel.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
