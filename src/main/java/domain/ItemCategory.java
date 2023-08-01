package domain;

public class ItemCategory {
    private int id;
    private String categoryName;

    public ItemCategory() {
    }

    public ItemCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    @Override
    public String toString() {
        return "domain.ItemCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
