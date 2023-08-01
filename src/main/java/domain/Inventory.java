package domain;

public class Inventory {
    private int id;
    private String itemName;
    private int itemQuantity;
    private ItemCategory itemCategory;
    private ItemLocation itemLocation;

    public Inventory() {
    }

    public Inventory(int id, String itemName, int itemQuantity, ItemCategory itemCategory, ItemLocation itemLocation) {
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

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemLocation getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(ItemLocation itemLocation) {
        this.itemLocation = itemLocation;
    }
    @Override
    public String toString() {
        return "domain.Inventory{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemCategory=" + itemCategory.toString() +
                ", itemLocation=" + itemLocation.toString() +
                '}';
    }
}
