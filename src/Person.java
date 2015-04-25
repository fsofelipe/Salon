/**
 * @author Matthew Meyer
 */
package softeng;
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String email;

    //// Constructor ////
    public Person() {}
    public Person(int uniqueID, String First, String Last, String Add, String Cit, String Sta,
                  String Pho, String Mail) {
        id = uniqueID;
        firstName = First;
        lastName = Last;
        address = Add;
        city = Cit;
        state = Sta;
        phone = Pho;
        email = Mail;
    }

    //// Accessors ////
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    //// Mutators ////
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //// Utility Functions ////
    @Override
    public String toString() {
        return Integer.toString(id) + ", '" +
                firstName + "', '" + lastName + "', '" +
                address   + "', '" + city     + "', '" +
                state     + "', '" + phone    + "', '" +
                email + "'";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Person) && toString().equals(obj.toString());
    }
}
