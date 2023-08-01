package services;

import domain.Inventory;
import domain.ItemCategory;
import domain.ItemLocation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvService {
    private Connection connection;

    public InvService() throws ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public InvService(Connection connection) {
        this.connection = connection;
    }

    public Inventory FetchByID(int InvId) throws SQLException {
        Inventory inv = null;
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_INVENTORY_BY_ID_QUERY);

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
            throw e;
        }
        return inv;
    }
    public List<String> getPhone() throws SQLException {

        List<String> listOfPhone = new ArrayList<>();

        try{
            PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_ALL_PHONE_NAMES_QUERY);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String Name = rs.getString("item_name");
                    listOfPhone.add(Name);
                }
                connection.close();
            }
        }
        catch (Exception e){
            throw e;
        }

        return listOfPhone;
    }
    public List<Inventory> FetchAll() throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_ALL_INVENTORY_QUERY);

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
            throw e;
        }
        return inventoryList;
    }
    public List<Inventory> FetchAllByCategory(int categoryId) throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_INVENTORY_BY_CATEGORY_QUERY);
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
            throw e;
        }
        return inventoryList;
    }
    public List<Inventory> FetchAllByLocation(int locationId) throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_INVENTORY_BY_LOCATION_QUERY);
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
            throw e;
        }
        return inventoryList;
    }
    public  List<Inventory> FetchAllByLocationAndCategory(int locationId, int categoryId) throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.SELECT_INVENTORY_BY_LOCATION_AND_CATEGORY_QUERY);
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
            throw e;
        }
        return inventoryList;
    }

    public void deleteInventoryItemById(int InvId) throws SQLException {
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.DELETE_INVENTORY_ITEM_BY_ID_QUERY);
            stmt.setInt(1, InvId);
            stmt.executeUpdate();

        } catch (SQLException e) {
           throw e;
        }

    }
    public  void addInventoryItem(Inventory newItem) throws SQLException {
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.ADD_INVENTORY_ITEM_QUERY);
            stmt.setInt(1, newItem.getId()); // Set the provided ID
            stmt.setString(2, newItem.getItemName());
            stmt.setInt(3, newItem.getItemQuantity());
            stmt.setInt(4, newItem.getItemCategory().getId());
            stmt.setInt(5, newItem.getItemLocation().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }

    }
    public  void updateInventoryItem(Inventory updatedItem) throws SQLException {
        try {
             PreparedStatement stmt = connection.prepareStatement(QueryManager.UPDATE_INVENTORY_ITEM_QUERY);

            stmt.setString(1, updatedItem.getItemName());
            stmt.setInt(2, updatedItem.getItemQuantity());
            stmt.setInt(3, updatedItem.getItemCategory().getId());
            stmt.setInt(4, updatedItem.getItemLocation().getId());
            stmt.setInt(5, updatedItem.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }

    }
}