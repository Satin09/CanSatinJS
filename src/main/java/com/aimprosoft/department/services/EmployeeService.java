package com.aimprosoft.department.services;

import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.models.Employee;

import java.util.List;


public interface EmployeeService {

    public List<Employee> getDepartmentEmployees(Department department)throws Exception ;

    public void removeEmployee(Employee employee)throws Exception;

    public void save(Employee employee) throws ServiceException;

    public Employee getEmployeeById(Integer empId)throws Exception;

    public Integer getEmployeeIdByEmail(String email)throws Exception;

}
