/**
 * @author Felipe
 */
//package softeng;
import java.text.*;
import java.util.*;

public class Financial_Employee extends Financial{
    private int ID, employeeID, month, year, hourPerWeek, hourOvertime; //how many hours this employee works per week
    private Date datePayment;
    private float valueTotal, salary;
    //datePayment can be found : getDatePayment from Financial Class
    
    //CONSTRUCTOR
    public Financial_Employee(){}
    public Financial_Employee(int employeeID, float salary, int hourOverdue){ 
        this.ID = 1;
        this.employeeID = employeeID;
        this.month = getTodaysDate().getMonth() + 1;
        this.year = getTodaysDate().getYear() + 1900;
        this.salary = salary;
        this.datePayment = getTodaysDate();
        this.hourOvertime = hourOverdue;
        hourPerWeek = 40;
        valueTotal = salary;
    }
    
    
     
    public int getID(){
        return ID;
    }
    
    public int getEmployeeID(){
        return employeeID;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }
    
    public float getValueTotal(){
        return valueTotal;
    }
    
    public float getSalary(){
        calculateSalary();
        return salary;
    }
    
    public float getHourOvertime(){
        return hourOvertime;
    }
    
    //SET's
    public void setID (int ID){
        this.ID = ID;
    }
    
    public void setEmployeeID(int employeeID){
        this.employeeID = employeeID;
    }
    
    public void setMonth (int month){
        this.month = month;
    }
    
    public void setYear (int year){
        this.year = year;
    }
    
    public void setDatePayment (Date datePayment){
        this.datePayment = datePayment;
    }
    
    public void setHourOvertime(int hourOvertime){
        this.hourOvertime = hourOvertime;
    }
    
    public void setValueTotal (float valueTotal){
        this.valueTotal = valueTotal;
    }
    
    public void setHours(int hour){
        this.hourPerWeek = hour;
    }
    
    //OTHER METHODS
    private float calculateSalary(){
        float salPerHour = (salary / hourPerWeek)*(1/2);
        
        valueTotal = salary + (salPerHour * hourOvertime);
        return valueTotal;
    }
    

    @Override
    public String toString() {
        return Integer.toString(ID) + "," + Integer.toString(employeeID) + "," + Integer.toString(month) + "," + Integer.toString(year) + "," + datePayment.toString()  + "," 
                + Integer.toString(hourOvertime)  + "," + Float.toString(valueTotal);
    }
    
    public String toInsert() {
        return Integer.toString(ID) + "," + Integer.toString(employeeID) + "," + Integer.toString(month) + "," + Integer.toString(year) + "," + datePayment.getTime()  + "," 
                 + Integer.toString(hourOvertime)  + "," + Float.toString(valueTotal);
        
    }
}
