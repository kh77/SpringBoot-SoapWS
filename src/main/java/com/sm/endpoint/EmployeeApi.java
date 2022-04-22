package com.sm.endpoint;

import com.sm.constant.EmployeeApiConstant;
import com.sm.employeesystem.*;
import com.sm.model.Employee;
import com.sm.service.EmployeeService;
import com.sm.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;

import static com.sm.constant.EmployeeApiConstant.*;

@Endpoint
public class EmployeeApi {

    @Autowired
    EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE ,localPart = GET_EMPLOYEE_REQUEST)
    @ResponsePayload
    public JAXBElement<GetEmployeeResponse> getEmployee(@RequestPayload GetEmployeeRequest request){
        GetEmployeeResponse response = new GetEmployeeResponse();
        Employee employee = employeeService.getEmployee(request.getEmployeeId());
        response.setEmployeeDetails(mapEmployee(employee));
        return new ObjectFactory().createGetEmployeeResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE ,localPart = GET_ALL_EMPLOYEE_REQUEST)
    @ResponsePayload
    public JAXBElement<GetAllEmployeeResponse> findAllEmployees(@RequestPayload GetAllEmployeeRequest request){
        GetAllEmployeeResponse response = new GetAllEmployeeResponse();

        List<EmployeeDetails> allEmployeeDetail = employeeService.getAllEmployees()
                .stream().map(emp -> mapEmployee(emp))
                .collect(Collectors.toList());
        response.getEmployeeDetails().addAll(allEmployeeDetail);

        return new ObjectFactory().createGetAllEmployeeResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE ,localPart = REMOVE_EMPLOYEE_REQUEST)
    @ResponsePayload
    public JAXBElement<RemoveEmployeeResponse> removeEmployee(@RequestPayload RemoveEmployeeRequest request){
        RemoveEmployeeResponse response = new RemoveEmployeeResponse();
        boolean status = employeeService.removeEmployee(request.getEmployeeId());
        response.setStatus(status);
        return new ObjectFactory().createRemoveEmployeeResponse(response);
    }

    private EmployeeDetails mapEmployee(Employee employee) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeId(employee.getEmployeeId());        
        employeeDetails.setAge(employee.getAge());
        return employeeDetails;
    }

}
