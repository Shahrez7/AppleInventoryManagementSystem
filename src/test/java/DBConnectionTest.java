import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBConnectionTest {
    private Connection connection;

    @BeforeEach
    public void setUp() throws ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testConnectionIsNotNull() {
        assertNotNull(connection);
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        assertTrue(connection.isValid(5));
    }
}
