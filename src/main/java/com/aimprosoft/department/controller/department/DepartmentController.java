package com.aimprosoft.department.controller.department;

import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String startPage() {
        return "testPage";
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    @ResponseBody
    public List<Department> showDepartments() throws Exception {
        return departmentService.getAll();
    }

    @RequestMapping(value = "addDepartment", params = "departmentId")
    @ResponseBody
    public Department getDepartmentById(@RequestParam("departmentId") Integer departmentId) throws Exception {
        return departmentService.getDepartment(departmentId);
    }

    @RequestMapping(value = "deleteDepartment", method = RequestMethod.POST)
    @ResponseBody
    public void deleteDepartment(@RequestParam("departmentId") Integer departmentId) throws Exception {
        Department department = departmentService.getDepartment(departmentId);
        departmentService.removeDepartment(department);

    }

    @RequestMapping(value = "createDepartment", method = RequestMethod.POST)
    @ResponseBody
    public void createDepartment() {
    }

    @RequestMapping(value = "editDepartment", method = RequestMethod.POST)
    @ResponseBody
    public Department editDepartment(@RequestParam("departmentId") Integer departmentId) throws Exception {
        Department department = departmentService.getDepartment(departmentId);
        return department;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public void addDepartment(Department department) throws Exception {
        departmentService.save(department);


    }

    @RequestMapping("error")
    @ResponseBody
    public String errorPage() {
        return "error";
    }

}

