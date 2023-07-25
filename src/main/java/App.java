import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App{
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        config.setJdbcUrl("jdbc:mysql://localhost:3306/aim");
        config.setUsername("root");
        config.setPassword("Shahrez7");

        dataSource = new HikariDataSource(config);
    }
    public static Inventory FetchByID(int InvId) {
        Inventory inv = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.id = ?")) {

            stmt.setInt(1, InvId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));

                    // Create and populate ItemCategory
                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);

                    // Create and populate ItemLocation
                    ItemLocation itemLocation = new ItemLocation();
                    itemLocation.setId(rs.getInt("item_location_id"));
                    itemLocation.setLocationName(rs.getString("location_name"));
                    inv.setItemLocation(itemLocation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }
    public static List<String> getPhone(){

        String USER = "root";
        String PASSWORD = "Shahrez7";

        String URL = "jdbc:mysql://localhost:3306/aim";
        List<String> listOfPhone = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM inventory");
            while ( rs.next() ) {
                String Name = rs.getString("item_name");
                listOfPhone.add(Name);
            }
            conn.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return listOfPhone;
    }
    public static List<Inventory> FetchAll() {
        List<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));

                    // Create and populate ItemCategory
                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);

                    // Create and populate ItemLocation
                    ItemLocation itemLocation = new ItemLocation();
                    itemLocation.setId(rs.getInt("item_location_id"));
                    itemLocation.setLocationName(rs.getString("location_name"));
                    inv.setItemLocation(itemLocation);

                    inventoryList.add(inv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    public static List<Inventory> FetchAllByCategory(int categoryId) {
        List<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_category_id = ?")) {

            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));

                    // Create and populate ItemCategory
                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);

                    // Create and populate ItemLocation
                    ItemLocation itemLocation = new ItemLocation();
                    itemLocation.setId(rs.getInt("item_location_id"));
                    itemLocation.setLocationName(rs.getString("location_name"));
                    inv.setItemLocation(itemLocation);

                    inventoryList.add(inv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    public static List<Inventory> FetchAllByLocation(int locationId) {
        List<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ?")) {

            stmt.setInt(1, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));

                    // Create and populate ItemCategory
                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);

                    // Create and populate ItemLocation
                    ItemLocation itemLocation = new ItemLocation();
                    itemLocation.setId(rs.getInt("item_location_id"));
                    itemLocation.setLocationName(rs.getString("location_name"));
                    inv.setItemLocation(itemLocation);

                    inventoryList.add(inv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    public static List<Inventory> FetchAllByLocationAndCategory(int locationId, int categoryId) {
        List<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ? AND inventory.item_category_id = ?")) {

            stmt.setInt(1, locationId);
            stmt.setInt(2, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));

                    // Create and populate ItemCategory
                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);

                    // Create and populate ItemLocation
                    ItemLocation itemLocation = new ItemLocation();
                    itemLocation.setId(rs.getInt("item_location_id"));
                    itemLocation.setLocationName(rs.getString("location_name"));
                    inv.setItemLocation(itemLocation);

                    inventoryList.add(inv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
    public static Inventory AddNewInventory(String jsonPayload) {
        System.out.println("Received JSON Payload: " + jsonPayload);
        Gson gson = new Gson();
        Inventory newInventory = gson.fromJson(jsonPayload, Inventory.class);
        try {

            try (Connection conn = dataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (item_name, item_quantity, item_category_id, item_location_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, newInventory.getItemName());
                stmt.setInt(2, newInventory.getItemQuantity());
                stmt.setInt(3, newInventory.getItemCategory().getId());
                stmt.setInt(4, newInventory.getItemLocation().getId());

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    // Retrieve the generated auto-incremented ID for the newly added inventory item
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newInventoryId = generatedKeys.getInt(1);
                            newInventory.setId(newInventoryId);
                            return newInventory;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deleteInventoryItemById(int InvId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM inventory WHERE id = ?")) {

            stmt.setInt(1, InvId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
