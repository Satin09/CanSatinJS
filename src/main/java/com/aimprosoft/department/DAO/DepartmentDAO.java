package com.aimprosoft.department.DAO;

import com.aimprosoft.department.models.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 27.08.13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentDAO {

    public List<Department> getAll()throws Exception;

    public void removeDepartment(Department department)throws Exception ;

    public void editAndAddDepartment(Department department)throws Exception;

    public Department getDepartment(Integer depId)throws Exception;

    public Integer getDepartmentIdByName(String depName)throws Exception;
}
