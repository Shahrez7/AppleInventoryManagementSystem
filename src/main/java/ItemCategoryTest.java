import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemCategoryTest {
    @Test
    void testItemCategoryConstructor() {
        ItemCategory category = new ItemCategory(1, "Electronics");
        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getCategoryName());
    }

    @Test
    void testItemCategorySettersAndGetters() {
        ItemCategory category = new ItemCategory();
        category.setId(2);
        category.setCategoryName("Clothing");
        assertEquals(2, category.getId());
        assertEquals("Clothing", category.getCategoryName());
    }


}
