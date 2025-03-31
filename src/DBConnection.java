import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // اگر فایل دیتابیس را در پوشه Resources قرار دادی:
    private static final String DB_URL = "jdbc:sqlite:Resources/booktracker.db"
    ;
    // یا اگر از مسیر مطلق استفاده می‌کنی:
    // private static final String DB_URL = "jdbc:sqlite:C:/Users/kiana/IdeaProjects/untitled5/Resources/myDatabase.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return conn;
    }
}
