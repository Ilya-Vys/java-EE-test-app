package example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public static final String JDBC_URL_PARAM = "url";
    public static final String USERNAME_PARAM = "user";
    public static final String PASSWORD_PARAM = "password";
    public static final String DRIVER_PARAM = "driverClass";

    private final String jdbcUrl;
    private final String username;
    private final String password;

    private ConnectionManager(String jdbcUrl, String username, String password, String dbDriver) throws ClassNotFoundException {
        Class.forName(dbDriver);
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    public static ConnectionManager fromProperties(Properties properties) throws ClassNotFoundException {
        return new ConnectionManager(
                properties.getProperty(JDBC_URL_PARAM),
                properties.getProperty(USERNAME_PARAM),
                properties.getProperty(PASSWORD_PARAM),
                properties.getProperty(DRIVER_PARAM)
        );
    }

    public static ConnectionManager fromResourceProperties(String resourceName) throws ClassNotFoundException {
        try (InputStream is = ConnectionManager.class.getResourceAsStream(resourceName)) {
            Properties properties = new Properties();
            properties.load(is);
            return fromProperties(properties);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load dp properties from " + resourceName + " due " + e.getLocalizedMessage(), e);
        }
    }
}
