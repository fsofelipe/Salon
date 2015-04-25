/**
 * @author Matthew Meyer
 */
package softeng;
public class Inventory {

    private int id;
    private String item;
    private int stock;

    Inventory() {}
    Inventory(int _id, String _item, int _stock) {
        id = _id;
        item = _item;
        stock = _stock;
    }

    public int getId() {
        return id;
    }
    public String getItem() {
        return item;
    }
    public int getStock() {
        return stock;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return Integer.toString(id) + ",'" +
                item + "', " + stock;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Inventory && toString().equals(obj.toString()));
    }

}
