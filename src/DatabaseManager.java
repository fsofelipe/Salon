/**
 * @author Matthew Meyer
 */
//package softeng;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private final static String DatabaseDriver = "org.sqlite.JDBC";
    private final static String SalonDatabase  = "jdbc:sqlite:Salon.db";
    private final static String EmployeeTable  = "EMPLOYEE";
    private final static String InventoryTable = "INVENTORY";
    private final static String FinancialBuildingTable = "FINANCIAL_BUILDING";
    private final static String FinancialEmployeeTable = "FINANCIAL_EMPLOYEE";
    private final static String FinancialSupplierTable = "FINANCIAL_SUPPLIER";
    private final static String FinancialSalesTable = "FINANCIAL_SALES";
    private final static String SupplierTable = "SUPPLIER";
    private final static String ServiceTable = "SERVICE";
    private final static String ClientTable = "CLIENT";
    /**
     * The purpose of this function is to create the database and initialize the tables.
     * It should only be called once in the lifetime of the entire program during installation.
     */
    public static void Create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DatabaseDriver);
            c = DriverManager.getConnection(SalonDatabase);
            try {
                stmt = c.createStatement();
                String sql = "CREATE TABLE " + EmployeeTable +
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " FIRST_NAME     VARCHAR(10)     NOT NULL," +
                        " LAST_NAME      VARCHAR(10)     NOT NULL," +
                        " ADDRESS        VARCHAR(50)," +
                        " CITY           VARCHAR(10)," +
                        " STATE          VARCHAR(2)," +
                        " PHONE          VARCHAR(10)," +
                        " EMAIL          VARCHAR(20)," +
                        " SALARY         INT," +
                        " MANAGER        VARCHAR(5))";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + ServiceTable + 
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " NAME           VARCHAR(15)     NOT NULL," +
                        " DESCRIPTION    VARCHAR(50)," +
                        " VALUE          INT             NOT NULL)";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + ClientTable +
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " FIRST_NAME     VARCHAR(10)     NOT NULL," +
                        " LAST_NAME      VARCHAR(10)     NOT NULL," +
                        " ADDRESS        VARCHAR(50)," +
                        " CITY           VARCHAR(10)," +
                        " STATE          VARCHAR(2)," +
                        " PHONE          VARCHAR(10)," +
                        " EMAIL          VARCHAR(20))";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + SupplierTable +
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " NAME           VARCHAR(10)     NOT NULL," +
                        " ADDRESS        VARCHAR(50)," +
                        " CITY           VARCHAR(10)," +
                        " STATE          VARCHAR(2)," +
                        " PHONE          VARCHAR(10)," +
                        " EMAIL          VARCHAR(20))"; 
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + InventoryTable +
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " ITEM           VARCHAR(20)," +
                        " STOCK          INT)";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + FinancialBuildingTable + 
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " CATEGORY       VARCHAR(15)     NOT NULL," +
                        " DATE_DUE       DATE            NOT NULL," +
                        " DATE_PAYMENT   DATE            NOT NULL," +
                        " VALUE          FLOAT(2)        NOT NULL," +
                        " VALUE_OVERDUE  FLOAT(2)        NOT NULL," +
                        " VALUE_TOTAL    FLOAT(2)        NOT NULL)";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + FinancialEmployeeTable + 
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " EMPLOYEE_ID    INT NOT NULL," +
                        " MONTH          INT             NOT NULL," +
                        " YEAR           INT             NOT NULL," +
                        " DATE_PAYMENT   DATE            NOT NULL," +
                        " HOURS_OVERTIME INT             NOT NULL," +
                        " VALUE_TOTAL    FLOAT(2)        NOT NULL," +
                        " FOREIGN KEY (EMPLOYEE_ID) REFERENCES " + EmployeeTable + "(ID))";
                stmt.executeUpdate(sql);
                
                sql = "CREATE TABLE " + FinancialSupplierTable + 
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " SUPPLIER_ID    INT             NOT NULL," +
                        " DATE_DUE       DATE            NOT NULL," +
                        " DATE_PAYMENT   DATE            NOT NULL," +
                        " VALUE          FLOAT(2)        NOT NULL," +
                        " VALUE_OVERDUE  FLOAT(2)        NOT NULL," +
                        " VALUE_TOTAL    FLOAT(2)        NOT NULL," +
                        " FOREIGN KEY (SUPPLIER_ID) REFERENCES " + SupplierTable + "(ID))";
                stmt.executeUpdate(sql);

                sql = "CREATE TABLE " + FinancialSalesTable + 
                        "(ID             INT PRIMARY KEY NOT NULL," +
                        " EMPLOYEE_ID    INT             NOT NULL," +
                        " CLIENT_ID      INT             NOT NULL," +
                        " SERVICE_ID     INT             NOT NULL," +
                        " DATE           DATE            NOT NULL," +
                        " VALUE          FLOAT(2)        NOT NULL," +
                        " PAYMENT_TYPE   VARCHAR(10)     NOT NULL," +
                        " FOREIGN KEY (EMPLOYEE_ID) REFERENCES " + EmployeeTable + "(ID)," +
                        " FOREIGN KEY (CLIENT_ID) REFERENCES " + ClientTable + "(ID)," +
                        " FOREIGN KEY (SERVICE_ID) REFERENCES " + ServiceTable + "(ID))";
                stmt.executeUpdate(sql);

                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //// EMPLOYEE ////
    /**
     * Find all the employees in the database
     * @return ArrayList of all employees
     */
    public static ArrayList<Employee> AllEmployees() {
        return LookupEmployee("","");
    }
    /**
     * Get employee info from database
     * @param attribute attribute of employee(s) that we are looking for; can be int or string
     * @param field column of database to find attribute
     * @return Arraylist of all employees who have the specified attribute of the specified field
     */
    public static ArrayList<Employee> LookupEmployee(int attribute, String field) {
        return LookupEmployee(Integer.toString(attribute), field);
    }
    public static ArrayList<Employee> LookupEmployee(String attribute, String field) {
        return Get(attribute, field, EmployeeTable, new Employee());
    }

    /**
     * Adds an employee to the database
     * @param worker employee to be added
     */
    public static void AddEmployee(Employee worker) {
        Add(worker.toString(), EmployeeTable);
    }

    /**
     * Makes the employee die with respect to the company
     * @param burntToACrisp THROW HIM TO THE DOGS!
     */
    public static void FireEmployee(Employee burntToACrisp) {
       Delete(burntToACrisp, EmployeeTable);
    }

    /**
     * Need a raise? Need to change address? Look no further!
     * This employee is already in the table, but its attribute have been changed.
     * @param worker Employee that has been modified
     */
    public static void UpdateEmployee(Employee worker) {
        FireEmployee(worker);
        AddEmployee(worker);
    }

        //INVENTORY
    
    public static ArrayList<Inventory> AllInventory() {
        return LookupInventory("","");
    }
    public static ArrayList<Inventory> LookupInventory(int attr, String field) {
        return LookupInventory(Integer.toString(attr), field);
    }
    public static ArrayList<Inventory> LookupInventory(String attribute, String field) {
       return Get(attribute, field, InventoryTable, new Inventory());
    }

    public static void AddInventory(Inventory product) {
        Add(product.toString(), InventoryTable);
    }

    public static void RemoveInventory(Inventory item) {
        Delete(item, InventoryTable);
    }

    public static void UpdateInventory(Inventory item) {
        RemoveInventory(item);
        AddInventory(item);
    }
    
    
    //FINACIAL BUILDING:
    public static ArrayList<Financial_Building> LookupFinancialBuilding(int attr, String field) {
        return LookupFinancialBuilding(Integer.toString(attr), field);
    }
    public static ArrayList<Financial_Building> LookupFinancialBuilding(String attribute, String field) {
       return Get(attribute, field, FinancialBuildingTable, new Financial_Building());
    }
    public static ArrayList<Financial_Building> AllFinancialBuilding() {
        return LookupFinancialBuilding("","");
    }
    public static void AddFinancialBuilding(Financial_Building product) {
        Add(product.toInsert(), FinancialBuildingTable); 
    }
    public static void RemoveFinancialBuilding(Financial_Building item) {
        Delete(item, FinancialBuildingTable);
    }
    public static void UpdateFinancialBuilding(Financial_Building item, Financial_Building item2) {
        RemoveFinancialBuilding(item);
        AddFinancialBuilding(item2);
    }
    
    //FINANCIAL EMPLOYEE
    public static ArrayList<Financial_Employee> LookupFinancialEmployee(int attr, String field) {
        return LookupFinancialEmployee(Integer.toString(attr), field);
    }
    public static ArrayList<Financial_Employee> LookupFinancialEmployee(String attribute, String field) {
       return Get(attribute, field, FinancialEmployeeTable, new Financial_Employee());
    }
    public static ArrayList<Financial_Employee> AllFinancialEmployee() {
        return LookupFinancialEmployee("","");
    }
    public static void AddFinancialEmployee(Financial_Employee product) {
        Add(product.toInsert(), FinancialEmployeeTable);
    }
    public static void RemoveFinancialEmployee(Financial_Employee item) {
        Delete(item, FinancialEmployeeTable);
    }
    public static void UpdateFinancialEmployee(Financial_Employee item, Financial_Employee item2) {
        RemoveFinancialEmployee(item);
        AddFinancialEmployee(item2);
    }
    
    //FINANCIAL SUPPLIER
    public static ArrayList<Financial_Supplier> LookupFinancialSupplier(int attr, String field) {
        return LookupFinancialSupplier(Integer.toString(attr), field);
    }
    public static ArrayList<Financial_Supplier> LookupFinancialSupplier(String attribute, String field) {
       return Get(attribute, field, FinancialSupplierTable, new Financial_Supplier());    
    }
    public static ArrayList<Financial_Supplier> AllFinancialSupplier() {
        return LookupFinancialSupplier("","");
    }
    public static void AddFinancialSupplier(Financial_Supplier product) {
        Add(product.toInsert(), FinancialSupplierTable);
    }
    public static void RemoveFinancialSupplier(Financial_Supplier item) {
        Delete(item, FinancialSupplierTable);
    }
    public static void UpdateFinancialSupplier(Financial_Supplier item, Financial_Supplier item2) {
        RemoveFinancialSupplier(item);
        AddFinancialSupplier(item2);
    }
    
    //FINANCIAL SALES
    public static ArrayList<Financial_Sales> LookupFinancialSales(int attr, String field) {
        return LookupFinancialSales(Integer.toString(attr), field);
    }
    public static ArrayList<Financial_Sales> LookupFinancialSales(String attribute, String field) {
       return Get(attribute, field, FinancialSalesTable, new Financial_Sales());    
    }
    public static ArrayList<Financial_Sales> AllFinancialSales() {
        return LookupFinancialSales("","");
    }
    public static void AddFinancialSales(Financial_Sales product) {
        Add(product.toInsert(), FinancialSalesTable);
    }
    public static void RemoveFinancialSales(Financial_Sales item) {
        Delete(item, FinancialSalesTable);
    }
    public static void UpdateFinancialSales(Financial_Sales item, Financial_Sales item2) {
        RemoveFinancialSales(item);
        AddFinancialSales(item2);
    }
    
    //FINANCIAL SERVICE
    public static ArrayList<Service> LookupService(int attr, String field) {
        return LookupService(Integer.toString(attr), field);
    }
    public static ArrayList<Service> LookupService(String attribute, String field) {
       return Get(attribute, field, ServiceTable, new Service());    
    }
    public static ArrayList<Service> AllService() {
        return LookupService("","");
    }
    public static void AddService(Service product) {
        Add(product.toInsert(), ServiceTable);
    }
    public static void RemoveService(Service item) {
        Delete(item, ServiceTable);
    }
    public static void UpdateService(Service item, Service item2) {
        RemoveService(item);
        AddService(item2);
    }
    
    //CLIENT
    public static ArrayList<Person> LookupClient(int attr, String field) {
        return LookupClient(Integer.toString(attr), field);
    }
    public static ArrayList<Person> LookupClient(String attribute, String field) {
       return Get(attribute, field, ClientTable, new Person());    
    }
    public static ArrayList<Person> AllClient() {
        return LookupClient("","");
    }
    public static void AddClient(Person product) {
        Add(product.toString(), ClientTable);
    }
    public static void RemoveClient(Person item) {
        Delete(item, ClientTable);
    }
    public static void UpdateClient(Service item, Service item2) {
        RemoveService(item);
        AddService(item2);
    }
    //AUX
    public static int getLastID(String table) {
        Connection c = null;
        Statement stmt = null;
        int teste = -1;
        try {
            Class.forName(DatabaseDriver);
            c = DriverManager.getConnection(SalonDatabase);
            c.setAutoCommit(false);
            try {
                stmt = c.createStatement();
                String sql = "SELECT 'ID'FROM " + table;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    teste = rs.getInt(1);
                    
                }
                rs.close();
                stmt.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
            c.close();
        } catch (SQLException E) {
            E.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teste;   
                
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private static void Add(String values, String table) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DatabaseDriver);
            c = DriverManager.getConnection(SalonDatabase);
            c.setAutoCommit(false);
            try {
                stmt = c.createStatement();
                String sql = "INSERT INTO " + table +
                        " VALUES (" + values + ");";
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
            c.commit();
            c.close();
        } catch (ClassNotFoundException E) {
            E.printStackTrace();
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }

    private static <T> ArrayList<T> Get(String attribute, String field, String table, T obj) {
        // check for empty args
        if (attribute.equals("") && !field.equals("")) return null;
        if (!attribute.equals("") && field.equals("")) return null;
        // if both are empty, then select everything
        String selector = (field.equals("") && attribute.equals("")) ?
                "" : " WHERE " + field + "='" + attribute + "'";
        ArrayList<T> tableItems = new ArrayList<T>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DatabaseDriver);
            c = DriverManager.getConnection(SalonDatabase);
            c.setAutoCommit(false);
            try {
                stmt = c.createStatement();
                String sql = "SELECT * FROM " + table + selector;
                ResultSet rs = stmt.executeQuery(sql);

                if (obj instanceof Employee) {
                    while (rs.next()) {
                        ((Employee) obj).setId(rs.getInt("id"));
                        ((Employee) obj).setFirstName(rs.getString("first_name"));
                        ((Employee) obj).setLastName(rs.getString("last_name"));
                        ((Employee) obj).setAddress(rs.getString("address"));
                        ((Employee) obj).setCity(rs.getString("city"));
                        ((Employee) obj).setState(rs.getString("state"));
                        ((Employee) obj).setPhone(rs.getString("phone"));
                        ((Employee) obj).setEmail(rs.getString("email"));
                        ((Employee) obj).setSalary(rs.getInt("salary"));
                        ((Employee) obj).setManager((rs.getString("manager")).equals("true"));
                        tableItems.add(obj);
                    }
                }
                else if (obj instanceof Inventory) {
                    while (rs.next()) {
                        ((Inventory) obj).setId(rs.getInt("id"));
                        ((Inventory) obj).setItem(rs.getString("item"));
                        ((Inventory) obj).setStock(rs.getInt("stock"));
                        tableItems.add(obj);
                    }
                }
                else if (obj instanceof Financial_Building) {
                    while (rs.next()) {
                        ((Financial_Building) obj).setID(rs.getInt("ID"));
                        ((Financial_Building) obj).setCategory(rs.getString("CATEGORY"));
                        ((Financial_Building) obj).setDateDue(rs.getDate("DATE_DUE"));                        
                        ((Financial_Building) obj).setDatePayment(rs.getDate("DATE_PAYMENT"));                        
                        ((Financial_Building) obj).setValue(rs.getFloat("VALUE"));                        
                        ((Financial_Building) obj).setValueOverdue(rs.getFloat("VALUE_OVERDUE"));                        
                        ((Financial_Building) obj).setValueTotal(rs.getFloat("VALUE_TOTAL"));                        
                        tableItems.add(obj);
                    }
                }
                else if (obj instanceof Financial_Employee) {
                    while (rs.next()) {
                        ((Financial_Employee) obj).setID(rs.getInt("ID"));                        
                        ((Financial_Employee) obj).setEmployeeID(rs.getInt("EMPLOYEE_ID"));                        
                        ((Financial_Employee) obj).setMonth(rs.getInt("MONTH"));
                        ((Financial_Employee) obj).setYear(rs.getInt("YEAR"));
                        ((Financial_Employee) obj).setDatePayment(rs.getDate("DATE_PAYMENT"));                        
                        ((Financial_Employee) obj).setHourOvertime(rs.getInt("HOURS_OVERTIME"));
                        ((Financial_Employee) obj).setValueTotal(rs.getFloat("VALUE_TOTAL"));                        
                        tableItems.add(obj);
                    }
                }
                else if (obj instanceof Financial_Supplier) {
                    while (rs.next()) {
                        ((Financial_Supplier) obj).setID(rs.getInt("ID"));                        
                        ((Financial_Supplier) obj).setSupplierID(rs.getInt("SUPPLIER_ID"));                        
                        ((Financial_Supplier) obj).setDatePayment(rs.getDate("DATE_PAYMENT"));      
                        ((Financial_Supplier) obj).setDateDue(rs.getDate("DATE_DUE"));
                        ((Financial_Supplier) obj).setValue(rs.getFloat("VALUE"));                        
                        ((Financial_Supplier) obj).setValueOverdue(rs.getFloat("VALUE_OVERDUE"));                        
                        ((Financial_Supplier) obj).setValueTotal(rs.getFloat("VALUE_TOTAL"));                        
                        tableItems.add(obj);
                    }
                }
                else if (obj instanceof Financial_Sales) {
                    while (rs.next()) {
                        ((Financial_Sales) obj).setID(rs.getInt("ID"));                        
                        ((Financial_Sales) obj).setEmployeeID(rs.getInt("EMPLOYEE_ID"));                        
                        ((Financial_Sales) obj).setClientID(rs.getInt("CLIENT_ID"));      
                        ((Financial_Sales) obj).setServiceID(rs.getInt("SERVICE_ID"));
                        ((Financial_Sales) obj).setDate(rs.getDate("DATE"));                        
                        ((Financial_Sales) obj).setValue(rs.getFloat("VALUE"));                        
                        ((Financial_Sales) obj).setPaymentType(rs.getString("PAYMENT_TYPE"));                        
                        tableItems.add(obj);
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
            c.close();
        } catch (SQLException E) {
            E.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tableItems;
    }

    private static <T> void Delete(T obj, String table) {
        String ID = "";
        if (obj instanceof Employee) {
            ID = Integer.toString(((Employee) obj).getId());
        }
        else if (obj instanceof Inventory) {
            ID = Integer.toString(((Inventory) obj).getId());
        }
        if (ID.isEmpty()) return;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DatabaseDriver);
            c = DriverManager.getConnection(SalonDatabase);
            c.setAutoCommit(false);
            try {
                stmt = c.createStatement();
                String sql = "DELETE FROM " + table +
                        " WHERE ID=" + ID;
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
            c.commit();
            c.close();
        } catch (ClassNotFoundException E) {
            E.printStackTrace();
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }
}
