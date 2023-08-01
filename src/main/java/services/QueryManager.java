package services;

public class QueryManager {
    public static final String SELECT_INVENTORY_BY_ID_QUERY = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.id = ?";
    public static final String SELECT_ALL_INVENTORY_QUERY = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id ORDER BY inventory.id";
    public static final String SELECT_INVENTORY_BY_CATEGORY_QUERY = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_category_id = ? ORDER BY inventory.id";
    public static final String SELECT_INVENTORY_BY_LOCATION_QUERY = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ? ORDER BY inventory.id";
    public static final String SELECT_INVENTORY_BY_LOCATION_AND_CATEGORY_QUERY = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ? AND inventory.item_category_id = ? ORDER BY inventory.id";
    public static final String DELETE_INVENTORY_ITEM_BY_ID_QUERY = "DELETE FROM inventory WHERE id = ?";
    public static final String ADD_INVENTORY_ITEM_QUERY = "INSERT INTO inventory (id, item_name, item_quantity, item_category_id, item_location_id) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_INVENTORY_ITEM_QUERY = "UPDATE inventory SET item_name = ?, item_quantity = ?, item_category_id = ?, item_location_id = ? WHERE id = ?";
    public static final String SELECT_ALL_PHONE_NAMES_QUERY = "SELECT item_name FROM inventory";
}