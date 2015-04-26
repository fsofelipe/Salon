
//package softeng;

/**
 *
 * @author Felipe
 */
public class Supplier {
    private int ID;
    private String name, address, city, state, phone, email;
    
    public Supplier(){}
    
    public Supplier(int ID, String name, String address, String city, String state, String phone, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
        @Override
    public String toString() {
        return Integer.toString(ID) + ", '" + name + "', '" + address + "', '" + city + "', '" + state + "', '" + phone + "', '" + email + "'";  
    }

    
}
