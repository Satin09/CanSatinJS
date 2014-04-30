package com.aimprosoft.department.services;

import com.aimprosoft.department.models.Department;

import java.util.List;


public interface DepartmentService {

    public List<Department> getAll() throws Exception;

    public Department getDepartment(Integer depId) throws Exception;

    public void removeDepartment(Department department) throws Exception;

    public void save(Department department) throws ServiceException;

    public Integer getDepartmentIdByName(String depName) throws Exception;
}
