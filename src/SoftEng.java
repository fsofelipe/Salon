
//package softeng;
import java.io.File;
import java.util.*;
import javax.swing.JFrame;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author Felipe
 */
public class SoftEng {

    /**
     * @param args the command line arguments
     */
    public static void main ( String [ ] args ) {

       
        File f = new File("Salon.db");
        if (f.exists()) {
            f.delete();
        }    
        DatabaseManager.Create();
        //(int uniqueID, String First, String Last, String Add, String Cit, String Sta, String Pho, String Mail)
        Person client = new Person (1, "Felipe", "Oliveira", "St. Grand Blvd 300", "St. Louis", "MO", "3145377219", "dasilvaoliveira@slu.edu");
        DatabaseManager.AddClient(client);
        
        //public Service(int ID, String name, String description, float value)
        Service serv = new Service(1, "test1", "this a description", 50);
        DatabaseManager.AddService(serv);
        
        Financial_Building testBuilding1 = new Financial_Building(1, "cat1", makeDate(23, 04, 2015), 100, 15);    
        DatabaseManager.AddFinancialBuilding(testBuilding1);
        
        Financial_Building testBuilding2 = new Financial_Building(2, "cat2", makeDate(23, 04, 2015), 200, 15);
        DatabaseManager.AddFinancialBuilding(testBuilding2);
       
        Financial_Building testBuilding3 = new Financial_Building(3, "cat3aaaaaa", makeDate(23, 04, 2015), 300, 15);
        DatabaseManager.AddFinancialBuilding(testBuilding3);
       
        Financial_Sales a = new Financial_Sales(1, 1, 1, 1, 10, "CREDIT CARD");
        DatabaseManager.AddFinancialSales(a);
        
        a.setID(2);
        a.setEmployeeID(2);
        a.setClientID(2);
        a.setServiceID(2);
        a.setValue(20);
        a.setPaymentType("DEBIT CARD");
        DatabaseManager.AddFinancialSales(a);
        
        a.setID(3);
        a.setEmployeeID(3);
        a.setClientID(3);
        a.setServiceID(3);
        a.setValue(30);
        a.setPaymentType("CASH");
        DatabaseManager.AddFinancialSales(a);
        
        a.setID(4);
        a.setEmployeeID(3);
        a.setClientID(3);
        a.setServiceID(3);
        a.setValue(30);
        a.setPaymentType("CASH");
        DatabaseManager.AddFinancialSales(a);
        
        Employee E = new Employee(57, "matt", "meyer", "1234_ABC_St", "St_Louis", "MO", "3148675309", "mattchainsaw@gmail.com", 5000000, true);
        DatabaseManager.AddEmployee(E);
        Employee e = new Employee(51, "emily", "hunt", "1234_ABC_St", "St_Louis", "MO", "6666666666", "M_LAY@gmail.com", 5, false);
        DatabaseManager.AddEmployee(e);
        
        Supplier S = new Supplier(1, "fornecedor", "St. 123", "St. Louis", "MO", "1231231234", "aaa@aaaa.com");
        DatabaseManager.AddSupplier(S);
        
        Financial_Data teste = new Financial_Data();

        System.out.println(teste.getPercentageMonthBuilding());
        
        System.out.println(teste.getPercentageMonthCreditIncome());
        System.out.println(teste.getPercentageMonthDebitIncome());
        System.out.println(teste.getPercentageMonthCashIncome());
        
        teste.addBuilding("cat1", makeDate(23, 04, 2015),  10, 10);

        teste.removeBuilding(4);
        
        teste.upDateBuilding(3, testBuilding3);
        
        teste.addEmployee("matt", "meyer", 8);
        
        teste.addSupplier("fornecedor", makeDate(27,04, 2015), 100, 0);
        
        //addSale(String employeeFirstName, String employeeLastName, String clientFirstName, String clientLastName, String serviceName, String paymentType)
        teste.addSales("emily", "hunt", "Felipe", "Oliveira", "test1", "CASH");
        
    }
    
    public static Date makeDate (int day, int month, int year){ //dd-mm-yyyy
        int auxYear;
        auxYear = year - 2000 + 100;
        Date a = new Date(auxYear, month - 1, day);
        return a;
        
    }
    
}
