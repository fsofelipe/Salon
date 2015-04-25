/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softeng;

/**
 *
 * @author Felipe
 */
public class Service {
    private int ID;
    private String name, description;
    private float value;
    public Service(){}

    public Service(String name, String description, float value) {
        this.ID = 1;
        this.name = name;
        this.description = description;
        this.value = value;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return Integer.toString(ID) + ", '" + name +  "', '" + description + "'," + value;  
    }
    public String toInsert() {
        return Integer.toString(ID) + ", '" + name +  "', '" + description + "'," + value;
    }    

        
    
}
