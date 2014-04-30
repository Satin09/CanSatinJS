package testjdbc;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 27.08.13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class DepDAOImpl implements DepDAO{
    List<DepTest> departments;

    public DepDAOImpl(){
        departments = new LinkedList<DepTest>();
        DepTest dep1 = new DepTest(1,"firstDep");
        DepTest dep2 = new DepTest(2,"secondDep");
        departments.add(dep1);
        departments.add(dep2);
    }
    @Override
    public void deleteDepartment(DepTest depTest){
        departments.remove(depTest.getDepatmentId());
    }

    @Override
    public DepTest getDepartment(int departmentId) {
        return departments.get(departmentId);
    }

    @Override
    public void updateDepartment(DepTest depTest) {
        departments.get(depTest.getDepatmentId()).setDepartmentName(depTest.getDepartmentName());
    }

    @Override
    public List<DepTest> getDepartments() {
        return departments;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
