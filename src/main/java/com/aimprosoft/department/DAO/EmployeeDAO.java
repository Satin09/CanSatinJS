package com.aimprosoft.department.DAO;

import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.models.Employee;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 29.08.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeDAO {
    public List<Employee> getDepartmentEmployees(Department department)throws Exception;

    public void removeEmployee(Employee employee)throws Exception;

    public void editAndAddEmployee(Employee employee)throws Exception;

    public Employee getEmployeeById(Integer empId)throws Exception;

    public Integer getEmployeeIdByEmail(String email)throws Exception;

}
