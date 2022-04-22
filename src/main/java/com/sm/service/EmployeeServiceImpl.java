package com.sm.service;

import com.sm.model.Employee;
import com.sm.model.DummyData;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> listOfEmployees = DummyData.getEmployees();

    public Employee getEmployee(int employeeId) {
        Optional<Employee> employee = listOfEmployees.stream()
                .filter(emp -> emp.getEmployeeId() == employeeId)
                .findFirst();
        return employee.get();
    }

    public List<Employee> getAllEmployees() {
        return listOfEmployees;
    }

    public boolean removeEmployee(int employeeId) {
        Iterator<Employee> iterator = listOfEmployees.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            if(employeeId == employee.getEmployeeId()){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
