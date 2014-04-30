package testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 27.08.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public interface DepDAO {

    public List<DepTest> getDepartments();
    public DepTest getDepartment(int departmentId);
    public void updateDepartment(DepTest depTest);
    public void deleteDepartment(DepTest depTest);



}
