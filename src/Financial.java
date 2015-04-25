package softeng;
import java.text.*;
import java.util.*;

/**
 *
 * @author Felipe
 */
public class Financial {
    public Financial(){
    }

    //GETS
    

    //AUX METHODS
    public Date getTodaysDate(){
        Date date = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return date;
    }
    /*
    public Date makeDate (int day, int month, int year){ //dd-mm-yyyy
        int auxYear;
        auxYear = year - 2000 + 100;
        Date a = new Date(auxYear, month - 1, day);
        return a;
        
    }
    */
    public float getOverdue(int percentage, Date dueDate, float billValue){ //10% per day
        Date todayDate = getTodaysDate();
        long diff, days;
        float result;
        diff = todayDate.getTime() - dueDate.getTime();
        days = diff / (24 * 60 * 60 * 1000);
        //System.out.println(((float)percentage/100));
        result = (days*(((float)percentage/100)))*billValue;//+billValue;
       //System.out.println(result);
        
        return result;
    }
    
    public Date makeDate (int day, int month, int year){ //dd-mm-yyyy
        int auxYear;
        auxYear = year - 2000 + 100;
        Date a = new Date(auxYear, month - 1, day);
        return a;
    }
}
