/**
 * @author Felipe
 */
//package softeng;
import java.text.*;
import java.util.*;


public class Financial_Supplier extends Financial {
    private int ID, supplierID, percentage;
    private Date dateDue, datePayment; 
    private float  value, valueOverdue, valueTotal; 
    //datePayment can be found : getDatePayment from Financial Class
    
    //CONSTRUCTOR
    public Financial_Supplier(){}
    public Financial_Supplier(int supplierID, Date dateDue, float value, int percentage) {
        this.ID = 1;
        this.value = value;
        this.valueOverdue = 0;
        this.valueTotal = this.value;
        this.dateDue = dateDue;
        this.datePayment = getTodaysDate();
        this.supplierID = supplierID;
        this.percentage = percentage;
        calculeOverdue();
        calculeValueTotal();
    }
    
    //GET's
    public float getValue(){
        return value;
    }
    
    public float getValueOverdue(){
        return valueOverdue;
    }
    
    public float getValueTotal(){
        return valueTotal;
    }
    
    public Date getDateDue(){
        return dateDue;
    }
    
    public Date getDatePayment(){
        return datePayment;

    }
    
    public int getID(){
        return ID;
    }
    
    public int getSupplierID(){
        return supplierID;
    }
    
//SET's
    public void setID (int ID){
        this.ID = ID;
    }
    
    public void setSupplierID(int supplierID){
        this.supplierID = supplierID;
    }
    
    public void setDateDue(Date dateDue){
        this.dateDue = dateDue;
    }
    
    public void setDatePayment(Date datePayment){
        this.datePayment = datePayment;
    }
 
    public void setValue (float value){
        this.value = value;
    }
    
    public void setValueOverdue (float valueOverdue){
        this.valueOverdue = valueOverdue;
    }
    
    public void setValueTotal (float valueTotal){
        this.valueTotal = valueTotal;
    }
    

//OTHER METHODS
    private void calculeValueTotal(){
        valueTotal = value + getValueOverdue();
    }
    
    private void calculeOverdue(){
        if ((getDatePayment().getTime() - dateDue.getTime())> 0){
            valueOverdue = getOverdue(percentage, dateDue, value);
        }
    }
    
    @Override
    public String toString() {
        return Integer.toString(ID) + "," + Integer.toString(supplierID) + "," + dateDue.toString() + "," + datePayment.toString()  + "," 
                + Float.toString(value) + "," + Float.toString(valueOverdue)  + "," + Float.toString(valueTotal);  
    }
    public String toInsert() {
        return Integer.toString(ID) + "," + Integer.toString(supplierID) + "," + dateDue.getTime() + "," + datePayment.getTime() + "," 
                + Float.toString(value) + "," + Float.toString(valueOverdue)  + "," + Float.toString(valueTotal);  
    }
}
