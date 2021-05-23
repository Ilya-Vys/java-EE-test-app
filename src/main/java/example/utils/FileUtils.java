package example.utils;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FileUtils {

    public static void executeSqlFile(Connection connection, String filename) throws SQLException, FileNotFoundException {
        ResourcesReader reader = new ResourcesReader();
        try (Statement statement = connection.createStatement()) {
            for (String s : reader.readFile(filename).split(";")) {
                statement.execute(s + ";");
            }
        }
    }
}
