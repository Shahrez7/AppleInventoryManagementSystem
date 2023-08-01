import com.google.gson.Gson;
import domain.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import resource.InvResource;
import services.InvService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ResourceTest {

    private InvService inventoryServices;
    private InvResource resource;

    @BeforeEach
    public void setUp() throws ClassNotFoundException {
        inventoryServices = mock(InvService.class);
        resource = new InvResource();
        resource.InventoryServices = inventoryServices;
    }

    @Test
    public void testFetchById() throws SQLException {
        int inventoryId = 1;
        Inventory Inventory = new Inventory();
        Inventory.setId(inventoryId);
        when(inventoryServices.FetchByID(inventoryId)).thenReturn(Inventory);
        String result = resource.Fetchid(inventoryId);
        assertEquals(new Gson().toJson(Inventory), result);
    }
    @Test
    public void testAddInventoryItem_InvalidPayload() throws ClassNotFoundException {
        String invalidPayload = "{ \"item_name\": \"Invalid Item\" }";
        String result = resource.addInventoryItem(invalidPayload);
        assertNull(result);
    }

    @Test
    public void testFetchAllInventory() throws SQLException {
        ArrayList<Inventory> InventoryList = new ArrayList<>();
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        InventoryList.add(Inventory);
        when(inventoryServices.FetchAll()).thenReturn(InventoryList);
        String result = resource.FetchAllInventory();
        String expectedJson = new Gson().toJson(InventoryList);
        assertEquals(expectedJson, result);
    }
    @Test
    public void testFetchALLNames() throws SQLException {
        ArrayList<String> InventoryList = new ArrayList<>();
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        InventoryList.add(Inventory.getItemName());
        when(inventoryServices.getPhone()).thenReturn(InventoryList);
        String result = resource.getPList();
        String expectedJson = new Gson().toJson(InventoryList);
        assertEquals(expectedJson, result);
    }
    @Test
    public void TestAddInventory() throws ClassNotFoundException, SQLException {
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        String InventoryString = new Gson().toJson(Inventory);
        doNothing().when(inventoryServices).addInventoryItem(Inventory);
        String result = resource.addInventoryItem(InventoryString);
        String expectedJson = new Gson().toJson(Inventory);
        assertEquals(expectedJson, result);
    }

    @Test
    public void TestFetchAllByCategory() throws ClassNotFoundException, SQLException {
        ArrayList<Inventory> InventoryList = new ArrayList<>();
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        InventoryList.add(Inventory);
        when(inventoryServices.FetchAllByCategory(1)).thenReturn(InventoryList);
        String result = resource.FetchAllByCategory(1);
        String expectedJson = new Gson().toJson(InventoryList);
        assertEquals(expectedJson, result);
    }

    @Test
    public void TestFetchAllByLocation() throws ClassNotFoundException, SQLException {
        ArrayList<Inventory> InventoryList = new ArrayList<>();
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        InventoryList.add(Inventory);
        when(inventoryServices.FetchAllByLocation(1)).thenReturn(InventoryList);
        String result = resource.FetchAllByLocation(1);
        String expectedJson = new Gson().toJson(InventoryList);
        assertEquals(expectedJson, result);
    }

    @Test
    public void TestFetchAllByCategoryAndLocation() throws ClassNotFoundException, SQLException {
        ArrayList<Inventory> InventoryList = new ArrayList<>();
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        InventoryList.add(Inventory);
        when(inventoryServices.FetchAllByLocationAndCategory(1, 1)).thenReturn(InventoryList);
        String result = resource.FetchAllByLocationAndCategory(1, 1);
        String expectedJson = new Gson().toJson(InventoryList);
        assertEquals(expectedJson, result);
    }

    @Test
    public void TestUpdateInventory() throws ClassNotFoundException, SQLException {
        Inventory Inventory = new Inventory();
        Inventory.setId(1);
        Inventory.setItemName("Test Item");
        Inventory.setItemQuantity(10);
        String InventoryString = new Gson().toJson(Inventory);
        doNothing().when(inventoryServices).updateInventoryItem(Inventory);
        String result = resource.updateInventoryItem(1, InventoryString);
        String expectedJson = new Gson().toJson(Inventory);
        assertEquals(expectedJson, result);
    }

    @Test
    public void TestDeleteInventory() throws SQLException {
        doNothing().when(inventoryServices).deleteInventoryItemById(1);
        String result = resource.deleteInventoryItem(1);
        assertEquals("Item Deleted", result);
    }
    @Test
    public void testFetchById_InvalidId() throws SQLException {
        int inventoryId = -1;
        when(inventoryServices.FetchByID(inventoryId)).thenReturn(null);
        String result = resource.Fetchid(inventoryId);
        assertNull(result);
    }
}
