package com.aimprosoft.department.services.impl;

import com.aimprosoft.department.DAO.EmployeeDAO;
import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.models.Employee;
import com.aimprosoft.department.services.EmployeeService;
import com.aimprosoft.department.services.ServiceException;
import com.aimprosoft.department.services.ValidationException;
import com.aimprosoft.department.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class EmployeeServiseImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> getDepartmentEmployees(Department department) throws Exception {


        return employeeDAO.getDepartmentEmployees(department);

    }


    @Override
    public void removeEmployee(Employee employee) throws Exception {


        employeeDAO.removeEmployee(employee);

    }


    @Override
    public void save(Employee employee) throws ServiceException {
        Map<String, String> errors = Validators.validateModel(employee);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        try {
            employeeDAO.editAndAddEmployee(employee);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmployeeById(Integer empId) throws Exception {

        return employeeDAO.getEmployeeById(empId);
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getEmployeeIdByEmail(String email) throws Exception {

        return employeeDAO.getEmployeeIdByEmail(email);

    }
}
