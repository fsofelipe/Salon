//package softeng;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Felipe
 */
public class Financial_Data extends Financial{
    private float financialBuildingTotalMonth, financialEmployeeTotalMonth, financialSupplierTotalMonth;
    private float monthBalance, monthExpense, monthIncome;
    private float monthPaymentType[] = new float[3];
    
    private float financialBuildingTotalPeriod, financialEmployeeTotalPeriod, financialSupplierTotalPeriod;
    private float periodExpense;

    
    Financial_Data(){
        Date a, b;
        a = getStartMonthDay();
        b = getLastMonthDay();
        /*
        minDateIncome = DatabaseManager.getMinDate("FINANCIAL_SALES", "DATE");
        maxDateIncome = DatabaseManager.getMaxDate("FINANCIAL_SALES", "DATE");
        */
        
        monthIncome = calculePeriodIncome(a,b);
        financialBuildingTotalMonth = calculePeriodExpense("FINANCIAL_BUILDING", a, b);
        financialEmployeeTotalMonth = calculePeriodExpense("FINANCIAL_EMPLOYEE", a, b);
        financialSupplierTotalMonth = calculePeriodExpense("FINANCIAL_SUPPLIER", a, b);
        monthExpense = financialBuildingTotalMonth + financialEmployeeTotalMonth + financialSupplierTotalMonth;
        monthBalance = monthIncome - monthExpense;
        
        monthPaymentType[0]= DatabaseManager.getValueCategory("FINANCIAL_SALES", "VALUE", "PAYMENT_TYPE", "CREDIT CARD", a, b);
        monthPaymentType[1]= DatabaseManager.getValueCategory("FINANCIAL_SALES", "VALUE", "PAYMENT_TYPE", "DEBIT CARD", a, b);
        monthPaymentType[2]= DatabaseManager.getValueCategory("FINANCIAL_SALES", "VALUE", "PAYMENT_TYPE", "CASH", a, b);
        
        this.financialBuildingTotalPeriod = 0;
        this.financialEmployeeTotalPeriod = 0;
        this.financialSupplierTotalPeriod = 0;

        
    }


    ///////////////////////////////////////////////////////////////////////////
    /////                             PUBLICS                         
    ///////////////////////////////////////////////////////////////////////////
    
    //SCREEN 1: PIE CHART EXPENSE
    //MONTH - EXPENSES - PERCENTAGE
    public float getPercentageMonthBuilding(){
        float aux = financialBuildingTotalMonth * 100;
        return (aux/monthExpense);
    }
    public float getPercentageMonthEmployee(){
        float aux = financialEmployeeTotalMonth * 100;
        return (aux/monthExpense);
    }
    public float getPercentageMonthSupplier(){
        float aux = financialSupplierTotalMonth * 100;
        return (aux/monthExpense);
    }
    
    // SCREEN 1: PIE CHART INCOME
    //MONTH - INCOME - VALUE
    public float getPercentageMonthCreditIncome(){
        float aux = monthPaymentType[0] * 100;
        return (aux/monthIncome);
    }
    public float getPercentageMonthDebitIncome(){
        float aux = monthPaymentType[1] * 100;
        return (aux/monthIncome);
    }
    public float getPercentageMonthCashIncome(){
        float aux = monthPaymentType[2] * 100;
        return (aux/monthIncome);
    }
    
    //SCREEN 1: BAR CHART
    public float getMonthIncome() {
        return monthIncome;
    }
    public float getMonthExpense() {
        return monthExpense;
    }
    public float getMonthBalance(){
        return monthBalance;
    }
  
    
    //SCREEN 2: OVERVIEW
    public float getPercentagePeriodBuilding(){
        float aux = financialBuildingTotalPeriod * 100;
        return (aux/periodExpense);
    } 

    public float getPercentagePeriodEmployee(){
        float aux = financialEmployeeTotalPeriod * 100;
        return (aux/periodExpense);
    }
    
    public float getPercentagePeriodSupplier(){
        float aux = financialSupplierTotalPeriod * 100;
        return (aux/periodExpense);
    }
    
    //SCREEN 2: BTN ADD BUILDING
    public void addBuilding(String category, Date dateDue, float value, int percentage){
        int size = DatabaseManager.getQuantLines("FINANCIAL_BUILDING");
        
        Financial_Building b = new Financial_Building(size+1, category, dateDue, value, percentage);
        DatabaseManager.AddFinancialBuilding(b);
    }
    //SCREEN 2: BTN REMOVE BUILDING
    public void removeBuilding(int ID){
        ArrayList<Financial_Building> list1 = DatabaseManager.LookupFinancialBuilding(ID, "ID");
        DatabaseManager.RemoveFinancialBuilding(list1.get(0));
    }
    
    //SCREEN 2: BTN UPDATE BUILDING
    public void upDateBuilding(int ID, Financial_Building a){
        ArrayList<Financial_Building> list1 = DatabaseManager.LookupFinancialBuilding(ID, "ID");
        a.setID(ID);
        DatabaseManager.UpdateFinancialBuilding(list1.get(0), a);
    }
    
    //SCREEN 2: BTN ADD EMPLOYEE
    public void addEmployee(String category, int employeeID, float salary, int hourOverdue){
        int size = DatabaseManager.getQuantLines("FINANCIAL_EMPLOYEE");
         Financial_Employee b = new Financial_Employee(size+1, employeeID, salary, hourOverdue);
        DatabaseManager.AddFinancialEmployee(b);
    }
    //SCREEN 2: BTN REMOVE EMPLOYEE
    public void removeEmployee(int ID){
        ArrayList<Financial_Employee> list1 = DatabaseManager.LookupFinancialEmployee(ID, "ID");
        DatabaseManager.RemoveFinancialEmployee(list1.get(0));
    }
    
    //SCREEN 2: BTN UPDATE EMPLOYEE
    public void upDateEmployee(int ID, Financial_Employee a){
        ArrayList<Financial_Employee> list1 = DatabaseManager.LookupFinancialEmployee(ID, "ID");
        a.setID(ID);
        DatabaseManager.UpdateFinancialEmployee(list1.get(0), a);
    }
    
    //SCREEN 2: BTN ADD SUPPLIER
    public void addSupplier(String category, int supplierID, Date dueDate, float value, int percentage){
        int size = DatabaseManager.getQuantLines("FINANCIAL_SUPPLIER");
        Financial_Supplier b = new Financial_Supplier(size+1, supplierID, dueDate, value, percentage);
        DatabaseManager.AddFinancialSupplier(b);
    }
    //SCREEN 2: BTN REMOVE SUPPLIER
    public void removeSupplier(int ID){
        ArrayList<Financial_Supplier> list1 = DatabaseManager.LookupFinancialSupplier(ID, "ID");
        DatabaseManager.RemoveFinancialSupplier(list1.get(0));
    }
    
    //SCREEN 2: BTN UPDATE SUPPLIER
    public void upDateSupplier(int ID, Financial_Supplier a){
        ArrayList<Financial_Supplier> list1 = DatabaseManager.LookupFinancialSupplier(ID, "ID");
        a.setID(ID);
        DatabaseManager.UpdateFinancialSupplier(list1.get(0), a);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    /////                             AUX
    ///////////////////////////////////////////////////////////////////////////
    
    public void calculePeriod(Date startPeriod, Date endPeriod){
        financialBuildingTotalPeriod = calculePeriodExpense("FINANCIAL_BUILDING", startPeriod, endPeriod);
        financialEmployeeTotalPeriod = calculePeriodExpense("FINANCIAL_EMPLOYEE", startPeriod, endPeriod);
        financialSupplierTotalPeriod = calculePeriodExpense("FINANCIAL_SUPPLIER", startPeriod, endPeriod);
        periodExpense = financialBuildingTotalPeriod + financialEmployeeTotalPeriod + financialSupplierTotalPeriod;
    }
    
    private Date getStartMonthDay(){
        Date a = getTodaysDate();
        Date start = makeDate(1, a.getMonth()+1, a.getYear()+1900);
        return start;
    }
    
    private Date getLastMonthDay(){
        Date a = getTodaysDate();
        Date aux;
        long aux2;
        Date end=getTodaysDate(); 
        if (a.getMonth()<10){
            aux = makeDate(1, a.getMonth()+2, a.getYear()+1900);
        }else{
            aux = makeDate(1, 1, a.getYear()+1901);
        }
        aux2 = aux.getTime() - 86400;
        end.setTime(aux2);
        return end;
    }
   
    private float calculePeriodIncome(Date start, Date end){
        //System.out.println("Primeiro dia do mes: "+start.toString()+ " || Ultimo dia do mes: " + end.toString());
        float result = DatabaseManager.ValuePeriod("FINANCIAL_SALES", "DATE", "VALUE" , start, end);
        System.out.println(result);
        return result;
    }
    
    private float calculePeriodExpense(String table, Date start, Date end){
        //System.out.println("Primeiro dia do mes: "+start.toString()+ " || Ultimo dia do mes: " + end.toString());
        float result = DatabaseManager.ValuePeriod(table, "DATE_PAYMENT", "VALUE_TOTAL" , start, end);
        System.out.println(result);
        return result;
    }

    
}
