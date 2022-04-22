package com.sm.service;

import com.sm.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployee(int employeeId);

    List<Employee> getAllEmployees();

    boolean removeEmployee(int employeeId);
}
