import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App{
    static HikariDataSource dataSource;

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


                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);


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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id ORDER BY inventory.id")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));


                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);


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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_category_id = ? ORDER BY inventory.id")) {

            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));


                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);


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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ? ORDER BY inventory.id")) {

            stmt.setInt(1, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));


                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);


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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.id INNER JOIN item_location ON inventory.item_location_id = item_location.id WHERE inventory.item_location_id = ? AND inventory.item_category_id = ? ORDER BY inventory.id")) {

            stmt.setInt(1, locationId);
            stmt.setInt(2, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));


                    ItemCategory itemCategory = new ItemCategory();
                    itemCategory.setId(rs.getInt("item_category_id"));
                    itemCategory.setCategoryName(rs.getString("category_name"));
                    inv.setItemCategory(itemCategory);


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
    public static Inventory addInventoryItem(Inventory newItem) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (id, item_name, item_quantity, item_category_id, item_location_id) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setInt(1, newItem.getId()); // Set the provided ID
            stmt.setString(2, newItem.getItemName());
            stmt.setInt(3, newItem.getItemQuantity());
            stmt.setInt(4, newItem.getItemCategory().getId());
            stmt.setInt(5, newItem.getItemLocation().getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return newItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Inventory updateInventoryItem(Inventory updatedItem) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET item_name = ?, item_quantity = ?, item_category_id = ?, item_location_id = ? WHERE id = ?")) {

            stmt.setString(1, updatedItem.getItemName());
            stmt.setInt(2, updatedItem.getItemQuantity());
            stmt.setInt(3, updatedItem.getItemCategory().getId());
            stmt.setInt(4, updatedItem.getItemLocation().getId());
            stmt.setInt(5, updatedItem.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return updatedItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

