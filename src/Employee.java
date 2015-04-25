/**
 * @author Matthew Meyer
 */
package softeng;
public class Employee extends Person {

    private int salary;
    private boolean isManager;

    //// Constructor ////
    public Employee() {}
    public Employee(int uniqueID, String First, String Last, String Add, String Cit, String Sta,
                    String Pho, String Mail, int Sal, boolean Man) {
        super(uniqueID, First, Last, Add, Cit, Sta, Pho, Mail);
        salary = Sal;
        isManager = Man;
    }

    //// Accessors ////
    public int getSalary() {
        return salary;
    }

    public boolean isManager() {
        return isManager;
    }

    //// Mutators ////
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    //// Utility Functions ////

    @Override
    public String toString() {
        String str =  super.toString() + ", " +
                Integer.toString(salary) + ", '";
        str += (isManager) ? "true'" : "false'";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee) && toString().equals(obj.toString());
    }
}
