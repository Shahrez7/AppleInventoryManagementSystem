import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AppTest {
    private static HikariDataSource dataSource;

    @BeforeAll
    static void setup() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/aim");
        config.setUsername("root");
        config.setPassword("Shahrez7");
        dataSource = new HikariDataSource(config);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

        }
    }

    @AfterAll
    static void cleanup() {
        dataSource.close();
    }


    @Test
    void testFetchByIDWithValidId() {
        int validId = 6;
        Inventory result = App.FetchByID(validId);
        assertNotNull(result);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventory WHERE id = 6");
            assertTrue(rs.next());
            assertEquals("Mac", rs.getString("item_name"));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testFetchByIDWithInvalidId() {
        int invalidId = 100;
        Inventory result = App.FetchByID(invalidId);
        assertNull(result);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventory WHERE id = 100");
            assertFalse(rs.next());
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testFetchAllByCategory() {
        int categoryId = 22;
        List<Inventory> result = App.FetchAllByCategory(categoryId);
        assertNotNull(result);
        assertEquals(3, result.size());

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM inventory WHERE item_category_id = 22");
            assertTrue(rs.next());
            assertEquals(3, rs.getInt(1));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testGetPhone() {
        List<String> phoneList = App.getPhone();
        assertNotNull(phoneList);
        assertEquals(5, phoneList.size());
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM inventory");
            assertTrue(rs.next());
            assertEquals(5, rs.getInt(1));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testUpdateInventoryItem() {
        int itemIdToUpdate = 2;
        Inventory existingItem = App.FetchByID(itemIdToUpdate);
        assertNotNull(existingItem);
        existingItem.setItemName("Updated Item");
        existingItem.setItemQuantity(50);
        Inventory updatedItem = App.updateInventoryItem(existingItem);
        assertNotNull(updatedItem);
        assertEquals(existingItem.getId(), updatedItem.getId());
        assertEquals("Updated Item", updatedItem.getItemName());
        assertEquals(50, updatedItem.getItemQuantity());


        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT item_name, item_quantity FROM inventory WHERE id = 2");
            assertTrue(rs.next());
            assertEquals("Updated Item", rs.getString("item_name"));
            assertEquals(50, rs.getInt("item_quantity"));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testAddInventoryItem() {
        Inventory newItem = new Inventory();
        newItem.setId(7);
        newItem.setItemName("New Item");
        newItem.setItemQuantity(100);
        ItemCategory category = new ItemCategory();
        category.setId(24);
        newItem.setItemCategory(category);
        ItemLocation location = new ItemLocation();
        location.setId(7);
        newItem.setItemLocation(location);
        Inventory insertedItem = App.addInventoryItem(newItem);
        assertNotNull(insertedItem);
        assertEquals(newItem.getId(), insertedItem.getId());

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM inventory WHERE id = 7");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testDeleteInventoryItemById() {
        int idToDelete = 4;
        boolean isDeleted = App.deleteInventoryItemById(idToDelete);
        assertTrue(isDeleted);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM inventory WHERE id = 4");
            assertTrue(rs.next());
            assertEquals(0, rs.getInt(1));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }

}
