import domain.ItemLocation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemLocationTest {
    @Test
    void testItemLocationConstructor() {
        ItemLocation location = new ItemLocation(1, "Warehouse A");
        assertEquals(1, location.getId());
        assertEquals("Warehouse A", location.getLocationName());
    }

    @Test
    void testItemLocationSettersAndGetters() {
        ItemLocation location = new ItemLocation();
        location.setId(2);
        location.setLocationName("Warehouse B");
        assertEquals(2, location.getId());
        assertEquals("Warehouse B", location.getLocationName());
    }


    @Test
    void testItemLocationEquality() {
        ItemLocation location1 = new ItemLocation(1, "Warehouse A");
        ItemLocation location2 = new ItemLocation(1, "Warehouse A");
        ItemLocation location3 = new ItemLocation(2, "Warehouse B");

        assertEquals(location1.getLocationName(), location2.getLocationName());
        assertNotEquals(location1, location3);
    }
}
