package testjdbc;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 27.08.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class DepTest {
    private int depatmentId;
    private String departmentName;



    public int getDepatmentId() {
        return depatmentId;
    }

    public void setDepatmentId(int depatmentId) {
        this.depatmentId = depatmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    DepTest(int depatmentId,String departmentName){
        setDepatmentId(depatmentId);
        setDepartmentName(departmentName);
    }
}
