import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        // Create an instance of Inventory
        inventory = new Inventory();
    }

    @Test
    void testGettersAndSetters() {
        // Test getters and setters
        inventory.setId(1);
        inventory.setItemName("Item A");
        inventory.setItemQuantity(10);

        ItemCategory category = new ItemCategory();
        category.setId(101);
        category.setCategoryName("Category X");
        inventory.setItemCategory(category);

        ItemLocation location = new ItemLocation();
        location.setId(201);
        location.setLocationName("Location Y");
        inventory.setItemLocation(location);

        assertEquals(1, inventory.getId());
        assertEquals("Item A", inventory.getItemName());
        assertEquals(10, inventory.getItemQuantity());
        assertEquals(category, inventory.getItemCategory());
        assertEquals(location, inventory.getItemLocation());
    }



    @Test
    void testToString() {
        // Set up Inventory object
        inventory.setId(1);
        inventory.setItemName("Item A");
        inventory.setItemQuantity(10);

        ItemCategory category = new ItemCategory();
        category.setId(101);
        category.setCategoryName("Category X");
        inventory.setItemCategory(category);

        ItemLocation location = new ItemLocation();
        location.setId(201);
        location.setLocationName("Location Y");
        inventory.setItemLocation(location);

        String expectedToString = "Inventory{id=1, itemName='Item A', itemQuantity=10, itemCategory=ItemCategory{id=101, categoryName='Category X'}, itemLocation=ItemLocation{id=201, locationName='Location Y'}}";
        assertEquals(expectedToString, inventory.toString());
    }


}
