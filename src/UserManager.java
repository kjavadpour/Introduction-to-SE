import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class UserManager {

    // افزودن کاربر به جدول User
    public static void addUser(Scanner scanner) {
        System.out.print("Enter user ID (integer): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user age (enter -1 if unknown): ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter user gender (m/f): ");
        String gender = scanner.nextLine();

        String sql = "INSERT INTO User (id, age, gender, Name) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            if (age == -1) {
                pstmt.setNull(2, Types.INTEGER);
            } else {
                pstmt.setInt(2, age);
            }
            pstmt.setString(3, gender);
            pstmt.setString(4, name);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    // محاسبه میانگین سن کاربران
    public static void printMeanAge() {
        String sql = "SELECT AVG(age) AS meanAge FROM User";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                double meanAge = rs.getDouble("meanAge");
                System.out.println("Mean age of users: " + meanAge);
            }
        } catch (SQLException e) {
            System.out.println("Error calculating mean age: " + e.getMessage());
        }
    }
}
