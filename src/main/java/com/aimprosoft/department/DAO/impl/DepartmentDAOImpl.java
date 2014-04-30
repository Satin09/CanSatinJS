package com.aimprosoft.department.DAO.impl;

import com.aimprosoft.department.DAO.DepartmentDAO;
import com.aimprosoft.department.models.Department;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public List<Department> getAll() throws Exception {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Department.class);
        return criteria.list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public Department getDepartment(Integer depId) throws Exception {

        return (Department) sessionFactory.getCurrentSession().get(Department.class, depId);

    }

    @Override
    public void removeDepartment(Department department) throws Exception {

        sessionFactory.getCurrentSession().delete(department);

    }

    @Override
    public void editAndAddDepartment(Department department) throws Exception {

        sessionFactory.getCurrentSession().merge(department);

    }


    @SuppressWarnings("unchecked")
    public Integer getDepartmentIdByName(String departmentName) throws Exception {

        List<Department> departmentList = sessionFactory.getCurrentSession().createQuery("from Department where departmentName=:departmentName").setParameter("departmentName", departmentName).list();
        if (!departmentList.isEmpty()) {
            return departmentList.get(0).getDepartmentId();
        }
        return null;


    }
}
