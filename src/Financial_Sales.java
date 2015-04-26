//package softeng;
import java.text.*;
import java.util.*;
/**
 *
 * @author Felipe
 */

public class Financial_Sales extends Financial {
    private int ID, employeeID, clientID, serviceID;
    private Date date;
    private float value;
    private String paymentType;
    public Financial_Sales(){}
    public Financial_Sales(int ID, int employeeID, int clientID, int serviceID, float value, String paymentType){
        this.employeeID = employeeID;
        this.clientID = clientID;
        this.serviceID = serviceID;
        this.value = value;
        this.paymentType = paymentType;
        this.ID = ID; 
        this.date = getTodaysDate();
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    @Override
    public String toString() {
        return Integer.toString(ID) + "," + Integer.toString(employeeID) + "," + Integer.toString(clientID) + "," + Integer.toString(clientID) + "," + date.toString() + "," 
                + Float.toString(value) + ",'" + paymentType + "'" ;  
    }
    public String toInsert() {
        return Integer.toString(ID) + "," + Integer.toString(employeeID) + "," + Integer.toString(clientID) + "," + Integer.toString(clientID) + "," + date.getTime() + "," 
                + Float.toString(value) + ",'" + paymentType + "'" ; 
    }    
 
}
