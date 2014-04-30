package com.aimprosoft.department.models;

import com.aimprosoft.department.services.DepartmentService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.ValidateWithMethod;
import org.springframework.web.context.ContextLoader;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department",uniqueConstraints = @UniqueConstraint(columnNames = {"department_id","department_name"}))
public class Department {




    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="department_id")
    private Integer departmentId;

    @NotNull (message = "Please enter new department name")
    @NotEmpty(message ="The field can not be empty" )
    @Length(min = 6,max = 30,message = "Length must be >6 and <30")
    @ValidateWithMethod(methodName = "isExist",parameterType = String.class,message = "Department with this name is already exist")
    @Column(name = "department_name")
    private String departmentName;


    @JsonIgnore
    @OneToMany( targetEntity = Employee.class,cascade = CascadeType.ALL,mappedBy="department")
    private List<Employee> employeeList;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public boolean isExist(String departmentName)throws Exception{
        Integer departmentID = null;
        if (!departmentName.isEmpty()) {
             departmentID = ((DepartmentService) ContextLoader.getCurrentWebApplicationContext()
                    .getBean("departmentServiceImpl")).getDepartmentIdByName(departmentName);
        }
        return departmentID == null || departmentID.equals(this.departmentId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }
}
