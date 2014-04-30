package com.aimprosoft.department.controller.employee;

import com.aimprosoft.department.models.Department;
import com.aimprosoft.department.models.Employee;
import com.aimprosoft.department.services.DepartmentService;
import com.aimprosoft.department.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;


    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping(value = "showEmployee", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showEmployees(@RequestParam("departmentId") Integer departmentId) throws Exception {
        Department department = departmentService.getDepartment(departmentId);
        Map<String, Object> map = new HashMap<>();
        map.put("ListEmployee", employeeService.getDepartmentEmployees(department));
        map.put("department", department);
        return map;
    }


    @RequestMapping(value = "createEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Employee createEmployee(@RequestParam("departmentId") Integer departmentId) throws Exception {
        Department department = departmentService.getDepartment(departmentId);
        Employee employee = new Employee();
        employee.setDepartment(department);
        return employee;

    }

    @RequestMapping(value = "editEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Employee editEmployee(@RequestParam("employeeId") Integer employeeId) throws Exception {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employee;
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addEmployee(Employee employee, @RequestParam("departmentId") Integer departmentId) throws Exception {

        Department department = departmentService.getDepartment(departmentId);
        employee.setDepartment(department);
        employeeService.save(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("ListEmployee", employeeService.getDepartmentEmployees(department));
        map.put("department", department);
        return map;

    }

    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEmployee(@RequestParam("employeeId") Integer employeeId, @RequestParam("departmentId") Integer departmentId) throws Exception {
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.removeEmployee(employee);
        Department department = departmentService.getDepartment(departmentId);
        Map<String, Object> map = new HashMap<>();
        map.put("ListEmployee", employeeService.getDepartmentEmployees(department));
        map.put("department", department);
        return map;


    }


}