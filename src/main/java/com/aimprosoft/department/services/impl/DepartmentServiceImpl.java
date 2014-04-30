package com.aimprosoft.department.services.impl;

import com.aimprosoft.department.DAO.DepartmentDAO;
import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.services.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Department> getAll() throws Exception {

        return departmentDAO.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Department getDepartment(Integer depId) throws Exception {

        return departmentDAO.getDepartment(depId);
    }

    @Override
    public void removeDepartment(Department department) throws Exception {
        departmentDAO.removeDepartment(department);
    }


    @Override
    public void save(Department department) throws ServiceException {

        Map<String, String> errors = Validators.validateModel(department);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        try {
            departmentDAO.editAndAddDepartment(department);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getDepartmentIdByName(String depName) throws Exception {

        return departmentDAO.getDepartmentIdByName(depName);
    }


}
