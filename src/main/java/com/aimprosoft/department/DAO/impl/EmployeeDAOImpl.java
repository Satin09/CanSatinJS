package com.aimprosoft.department.DAO.impl;

import com.aimprosoft.department.DAO.EmployeeDAO;
import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.models.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> getDepartmentEmployees(Department department) throws Exception {

       return sessionFactory.getCurrentSession().createQuery("from Employee where department=:department").setParameter("department", department).list();

    }

    @Override
    public void removeEmployee(Employee employee) throws Exception {

        sessionFactory.getCurrentSession().delete(employee);

    }

    @Override
    public void editAndAddEmployee(Employee employee) throws Exception {

        sessionFactory.getCurrentSession().merge(employee);

    }


    @SuppressWarnings("unchecked")
    @Override
    public Employee getEmployeeById(Integer empId) throws Exception {

        return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empId);

    }


    @SuppressWarnings("unchecked")
    @Override
    public Integer getEmployeeIdByEmail(String email) throws Exception {

        List<Employee> employeeList = sessionFactory.getCurrentSession().createQuery("from Employee where email=:email").setParameter("email", email).list();
        if (!employeeList.isEmpty()) {
            return employeeList.get(0).getEmployeeId();
        }
        return null;

    }


}
