
import domain.Inventory;
import domain.ItemCategory;
import domain.ItemLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.InvService;
import services.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InvServicesTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private InvService inventoryServices;

    @Mock
    private ItemCategory itemCategory;

    @Mock
    private ItemLocation itemLocation;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        inventoryServices = new InvService(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testFetchByID() throws SQLException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        Inventory inventoryDomain = inventoryServices.FetchByID(id);
        verify(mockPreparedStatement, times(1)).setInt(1, id);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(id, inventoryDomain.getId());
        assertEquals(itemName, inventoryDomain.getItemName());
        assertEquals(itemQuantity, inventoryDomain.getItemQuantity());
        assertEquals(categoryName, inventoryDomain.getItemCategory().getCategoryName());
        assertEquals(locationName, inventoryDomain.getItemLocation().getLocationName());
    }
    @Test
    void testFetchAllByLocation_ThrowsSQLException() throws SQLException {
        int locationId = 123;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.FetchAllByLocation(locationId));
        verify(mockPreparedStatement).setInt(1, locationId);
        verify(mockPreparedStatement).executeQuery();
    }
    @Test
    void testUpdateInventoryItem_ThrowsSQLException() throws SQLException {
        int updatedItemId = 123;
        String updatedItemName = "Updated Test Item";
        int updatedItemQuantity = 20;
        int updatedItemCategoryId = 456;
        int updatedItemLocationId = 789;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();
        InvService invService = new InvService(mockConnection);
        Inventory updatedItem = new Inventory();
        updatedItem.setId(updatedItemId);
        updatedItem.setItemName(updatedItemName);
        updatedItem.setItemQuantity(updatedItemQuantity);
        ItemCategory updatedItemCategory = new ItemCategory();
        updatedItemCategory.setId(updatedItemCategoryId);
        updatedItem.setItemCategory(updatedItemCategory);
        ItemLocation updatedItemLocation = new ItemLocation();
        updatedItemLocation.setId(updatedItemLocationId);
        updatedItem.setItemLocation(updatedItemLocation);
        assertThrows(SQLException.class, () -> invService.updateInventoryItem(updatedItem));
        verify(mockPreparedStatement).setString(1, updatedItemName);
        verify(mockPreparedStatement).setInt(2, updatedItemQuantity);
        verify(mockPreparedStatement).setInt(3, updatedItemCategoryId);
        verify(mockPreparedStatement).setInt(4, updatedItemLocationId);
        verify(mockPreparedStatement).setInt(5, updatedItemId);
        verify(mockPreparedStatement).executeUpdate();
    }
    @Test
    void testFetchById_ThrowsSQLException() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.FetchByID(1));
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeQuery();
    }
    @Test
    void testFetchAll_ThrowsSQLException() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.FetchAll());
        verify(mockPreparedStatement).executeQuery();
    }
    @Test
    void testFetchAllByCategory_ThrowsSQLException() throws SQLException {
        int categoryId = 123;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.FetchAllByCategory(categoryId));
        verify(mockPreparedStatement).setInt(1, categoryId);
        verify(mockPreparedStatement).executeQuery();
    }


    @Test
    void testGetPhone_ThrowsSQLException() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.getPhone());
        verify(mockPreparedStatement).executeQuery();

    }
    @Test
    void testDeleteInventoryItemById_ThrowsSQLException() throws SQLException {
        int invIdToDelete = 456;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.deleteInventoryItemById(invIdToDelete));
        verify(mockPreparedStatement).setInt(1, invIdToDelete);
        verify(mockPreparedStatement).executeUpdate();

    }
    @Test
    void testAddInventoryItem_ThrowsSQLException() throws SQLException {
        int itemId = 123;
        String itemName = "Test Item";
        int itemQuantity = 10;
        int itemCategoryId = 456;
        int itemLocationId = 789;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();
        InvService invService = new InvService(mockConnection);
        Inventory newItem = new Inventory();
        newItem.setId(itemId);
        newItem.setItemName(itemName);
        newItem.setItemQuantity(itemQuantity);

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setId(itemCategoryId);
        newItem.setItemCategory(itemCategory);

        ItemLocation itemLocation = new ItemLocation();
        itemLocation.setId(itemLocationId);
        newItem.setItemLocation(itemLocation);
        assertThrows(SQLException.class, () -> invService.addInventoryItem(newItem));
        verify(mockPreparedStatement).setInt(1, itemId);
        verify(mockPreparedStatement).setString(2, itemName);
        verify(mockPreparedStatement).setInt(3, itemQuantity);
        verify(mockPreparedStatement).setInt(4, itemCategoryId);
        verify(mockPreparedStatement).setInt(5, itemLocationId);
        verify(mockPreparedStatement).executeUpdate();

    }
    @Test
    void testFetchAllByLocationAndCategory_ThrowsSQLException() throws SQLException {
        int locationId = 456;
        int categoryId = 123;
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenThrow(new SQLException());
        InvService invService = new InvService(mockConnection);
        assertThrows(SQLException.class, () -> invService.FetchAllByLocationAndCategory(locationId, categoryId));
        verify(mockPreparedStatement).setInt(1, locationId);
        verify(mockPreparedStatement).setInt(2, categoryId);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    public void testgetPhone() throws SQLException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        List<Inventory> inventoryDomainList = inventoryServices.FetchAll();
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(itemName, inventoryDomainList.get(0).getItemName());


    }


    @Test
    public void testFetchAll() throws SQLException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        List<Inventory> inventoryDomainList = inventoryServices.FetchAll();
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(id, inventoryDomainList.get(0).getId());
        assertEquals(itemName, inventoryDomainList.get(0).getItemName());
        assertEquals(itemQuantity, inventoryDomainList.get(0).getItemQuantity());
        assertEquals(categoryName, inventoryDomainList.get(0).getItemCategory().getCategoryName());
        assertEquals(locationName, inventoryDomainList.get(0).getItemLocation().getLocationName());
    }

    @Test
    public void testFetchAllByCategory() throws SQLException, ClassNotFoundException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        List<Inventory> inventoryDomainList = inventoryServices.FetchAllByCategory(1);
        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(id, inventoryDomainList.get(0).getId());
        assertEquals(itemName, inventoryDomainList.get(0).getItemName());
        assertEquals(itemQuantity, inventoryDomainList.get(0).getItemQuantity());
        assertEquals(categoryName, inventoryDomainList.get(0).getItemCategory().getCategoryName());
        assertEquals(locationName, inventoryDomainList.get(0).getItemLocation().getLocationName());
    }

    @Test
    public void testFetchAllByLocation() throws SQLException, ClassNotFoundException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        List<Inventory> inventoryDomainList = inventoryServices.FetchAllByLocation(1);
        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(id, inventoryDomainList.get(0).getId());
        assertEquals(itemName, inventoryDomainList.get(0).getItemName());
        assertEquals(itemQuantity, inventoryDomainList.get(0).getItemQuantity());
        assertEquals(categoryName, inventoryDomainList.get(0).getItemCategory().getCategoryName());
        assertEquals(locationName, inventoryDomainList.get(0).getItemLocation().getLocationName());
    }

    @Test
    public void testFetchAllByLocationAndCategory() throws SQLException, ClassNotFoundException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(id);
        when(mockResultSet.getString("item_name")).thenReturn(itemName);
        when(mockResultSet.getInt("item_quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("item_category_id")).thenReturn(1);
        when(mockResultSet.getString("category_name")).thenReturn(categoryName);
        when(mockResultSet.getInt("item_location_id")).thenReturn(1);
        when(mockResultSet.getString("location_name")).thenReturn(locationName);
        List<Inventory> inventoryDomainList = inventoryServices.FetchAllByLocationAndCategory(1, 1);
        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertEquals(id, inventoryDomainList.get(0).getId());
        assertEquals(itemName, inventoryDomainList.get(0).getItemName());
        assertEquals(itemQuantity, inventoryDomainList.get(0).getItemQuantity());
        assertEquals(categoryName, inventoryDomainList.get(0).getItemCategory().getCategoryName());
        assertEquals(locationName, inventoryDomainList.get(0).getItemLocation().getLocationName());
    }

    @Test
    public void testAddNewInventoryItem() throws SQLException, ClassNotFoundException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        itemCategory = new ItemCategory(1, categoryName);
        itemLocation = new ItemLocation(1, locationName);
        Inventory inventoryDomain = new Inventory(id, itemName, itemQuantity, itemCategory, itemLocation);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("ID")).thenReturn(id);
        when(mockResultSet.getString("Name")).thenReturn(itemName);
        when(mockResultSet.getInt("Quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("Category_ID")).thenReturn(1);
        when(mockResultSet.getString("Category_Name")).thenReturn(categoryName);
        when(mockResultSet.getInt("Location_ID")).thenReturn(1);
        when(mockResultSet.getString("Location_Name")).thenReturn(locationName);
        inventoryServices.addInventoryItem(inventoryDomain);
        verify(mockPreparedStatement, times(1)).setInt(1, id);
        verify(mockPreparedStatement, times(1)).setString(2, itemName);
        verify(mockPreparedStatement, times(1)).setInt(3, itemQuantity);
        verify(mockPreparedStatement, times(1)).setInt(4, 1);
        verify(mockPreparedStatement, times(1)).setInt(5, 1);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateExistingInventoryItemByID() throws SQLException, ClassNotFoundException {
        int id = 1;
        String itemName = "Sample Item";
        int itemQuantity = 10;
        String categoryName = "Sample Category";
        String locationName = "Sample Location";
        itemCategory = new ItemCategory(1, categoryName);
        itemLocation = new ItemLocation(1, locationName);
        Inventory inventoryDomain = new Inventory(id, itemName, itemQuantity, itemCategory, itemLocation);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("ID")).thenReturn(id);
        when(mockResultSet.getString("Name")).thenReturn(itemName);
        when(mockResultSet.getInt("Quantity")).thenReturn(itemQuantity);
        when(mockResultSet.getInt("Category_ID")).thenReturn(1);
        when(mockResultSet.getString("Category_Name")).thenReturn(categoryName);
        when(mockResultSet.getInt("Location_ID")).thenReturn(1);
        when(mockResultSet.getString("Location_Name")).thenReturn(locationName);
        inventoryServices.updateInventoryItem(inventoryDomain);
        verify(mockPreparedStatement, times(1)).setString(1, itemName);
        verify(mockPreparedStatement, times(1)).setInt(2, itemQuantity);
        verify(mockPreparedStatement, times(1)).setInt(3, 1);
        verify(mockPreparedStatement, times(1)).setInt(4, 1);
        verify(mockPreparedStatement, times(1)).setInt(5, id);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteExistingInventoryItemById() throws SQLException, ClassNotFoundException {
        ;
        int id = 1;
        inventoryServices.deleteInventoryItemById(id);
        verify(mockPreparedStatement, times(1)).setInt(1, id);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

}
