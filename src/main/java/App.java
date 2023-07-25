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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE id = ?")) {

            System.out.println("Here");

            stmt.setInt(1, InvId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Here");
                    inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));
                    inv.setItemCategory(rs.getInt("item_category_id"));
                    inv.setItemLocation(rs.getInt("item_location_id"));
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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));
                    inv.setItemCategory(rs.getInt("item_category_id"));
                    inv.setItemLocation(rs.getInt("item_location_id"));

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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE item_category_id = ?")) {

            stmt.setInt(1, categoryId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));
                    inv.setItemCategory(rs.getInt("item_category_id"));
                    inv.setItemLocation(rs.getInt("item_location_id"));

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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE item_location_id = ?")) {

            stmt.setInt(1, locationId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setId(rs.getInt("id"));
                    inv.setItemName(rs.getString("item_name"));
                    inv.setItemQuantity(rs.getInt("item_quantity"));
                    inv.setItemCategory(rs.getInt("item_category_id"));
                    inv.setItemLocation(rs.getInt("item_location_id"));

                    inventoryList.add(inv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryList;
    }
}
