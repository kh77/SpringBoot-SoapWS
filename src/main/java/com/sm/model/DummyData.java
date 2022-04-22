package com.sm.model;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<Employee> getEmployees() {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(70, "Ali",  12));
        empList.add(new Employee(80, "Hunain", 22));
        empList.add(new Employee(90, "Awais",  17));
        empList.add(new Employee(100, "Atiq", 19));
        empList.add(new Employee(200, "Mubashir",  23));
        return empList;
    }
}
