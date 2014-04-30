package com.aimprosoft.department.models;

import com.aimprosoft.department.services.DepartmentService;
import com.aimprosoft.department.services.EmployeeService;
import com.aimprosoft.department.services.impl.DepartmentServiceImpl;
import com.aimprosoft.department.services.impl.EmployeeServiseImpl;
import net.sf.oval.constraint.*;
import net.sf.oval.guard.Guarded;
import org.springframework.web.context.ContextLoader;

import javax.persistence.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Length(min = 6, max = 30, message = "Length must be >6 and <30")
    @NotEmpty(message = "First name can't be empty")
    @NotNull(message = "First name can't be empty")
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 6, max = 30, message = "Length must be >6 and <30")
    @NotEmpty(message = "Last name can't be empty")
    @NotNull(message = "Last name can't be empty")
    @Column(name = "last_name")
    private String lastName;

    @Length(min = 6, max = 30, message = "Length must be >6 and <30")
    @NotNull(message = "Email can't be empty")
    @ValidateWithMethod(methodName = "isExists", parameterType = String.class, message = "This email is already exist")
    @Email(message = "Example: Malina@darina.com")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Salary can't be empty")
    @NotNull(message = "Enter numbers")
    @Column(name = "salary")
    private Float salary;

    @NotNull(message = "Date format(yyyy-MM-dd)")
    @Column(name = "day_of_birth")
    private Date dayOfBirth;


    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employerId) {
        this.employeeId = employerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public String getStringFromDate() {
        String date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = new String(simpleDateFormat.format(this.dayOfBirth));
        return date;

    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public String toString() {
        return getFirstName();

    }

    public boolean isExists(String email) throws Exception {
        Integer employeeID = null;
        if (!email.isEmpty()) {
            employeeID = ((EmployeeService) ContextLoader.getCurrentWebApplicationContext()
                    .getBean("employeeServiseImpl")).getEmployeeIdByEmail(email);
        }
        return employeeID == null || employeeID.equals(this.employeeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != null ? !employeeId.equals(employee.employeeId) : employee.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return employeeId != null ? employeeId.hashCode() : 0;
    }
}
