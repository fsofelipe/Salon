package softeng;

import java.text.*;
import java.util.*;

/**
 * @author Felipe
 */
public class Financial_Building extends Financial{
    private int ID, percentage;
    private String category; 
    private Date dateDue, datePayment;
    private float value, valueOverdue, valueTotal; 
    //datePayment can be found : getDatePayment from Financial Class
 
    //CONTRUCTOR
    public Financial_Building (){}
    public Financial_Building (String category, Date dateDue, float value, int percentage){
        this.value = value;
        valueOverdue = 1; 
        this.category = category;
        this.dateDue = dateDue;
        this.datePayment = getTodaysDate();
        ID = 1;
        this.percentage = percentage;
        calculeOverdue();
        calculeValueTotal();
    }

    //GET's
    public int getID(){
        return ID;
    }
    
    public Date getDateDue(){
        return dateDue;
    }
    
    public Date getDatePayment(){
        return datePayment;

    }
    
    public String getCategory(){
        return category;
    }
    
    public float getValue(){
        return value;
    }
    
    public float getValueOverdue(){
        return valueOverdue;
    }
    
    public float getValueTotal(){
        return valueTotal;
    }
    
    
    
    
    
    //SET's
    
    public void setID (int ID){
        this.ID = ID;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDateDue(Date dateDue){
        this.dateDue = dateDue;
    }
    public void setDatePayment (Date datePayment){
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
        return Integer.toString(ID) + "," + category + "," + dateDue.toString() + "," + datePayment.toString()  + "," 
                + Float.toString(value) + "," + Float.toString(valueOverdue) + "," + Float.toString(valueTotal); 

    }
    
    public String toInsert() {
        return Integer.toString(ID) + ",'" + category + "'," + dateDue.getTime() + "," + datePayment.getTime()  + "," 
                + Float.toString(value) + "," + Float.toString(valueOverdue) + "," + Float.toString(valueTotal); 

    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Financial_Building && toString().equals(obj.toString()));
    }
    
    
}
