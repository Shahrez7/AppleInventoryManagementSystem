public class Inventory {
    private int id;
    private String itemName;
    private int itemQuantity;
    private int itemCategory;
    private int itemLocation;

    public Inventory() {
    }

    public Inventory(int id, String itemName, int itemQuantity, int itemCategory, int itemLocation) {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategory;
        this.itemLocation = itemLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }
}
