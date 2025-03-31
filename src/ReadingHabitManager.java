import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReadingHabitManager {

    // نمایش عادت‌های مطالعه یک کاربر
    public static void showReadingHabits(Scanner scanner) {
        System.out.print("Enter user ID to view reading habits: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        String sql = "SELECT * FROM ReadingHabit WHERE user_id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Reading habits for user " + userId + ":");
            while (rs.next()) {
                int habitID = rs.getInt("habitID");
                String bookName = rs.getString("book");  // در دیتاست ستون مربوط به عنوان کتاب "book" نامیده شده است.
                int pagesRead = rs.getInt("pagesRead");
                String submissionMoment = rs.getString("submissionMoment");
                System.out.println("Habit ID: " + habitID + " | Book: " + bookName +
                        " | Pages Read: " + pagesRead +
                        " | Submission Moment: " + submissionMoment);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving reading habits: " + e.getMessage());
        }
    }

    // به‌روز‌رسانی عنوان کتاب برای یک رکورد خاص
    public static void updateBookTitle(Scanner scanner) {
        System.out.print("Enter habit ID for which to update book title: ");
        int habitID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new book title: ");
        String newTitle = scanner.nextLine();

        String sql = "UPDATE ReadingHabit SET book = ? WHERE habitID = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setInt(2, habitID);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Book title updated successfully.");
            } else {
                System.out.println("No record found with habitID " + habitID);
            }
        } catch (SQLException e) {
            System.out.println("Error updating book title: " + e.getMessage());
        }
    }

    // حذف یک رکورد از جدول ReadingHabit
    public static void deleteReadingHabit(Scanner scanner) {
        System.out.print("Enter habit ID to delete: ");
        int habitID = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM ReadingHabit WHERE habitID = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, habitID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Reading habit deleted successfully.");
            } else {
                System.out.println("No record found with habitID " + habitID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    // تعداد کاربرانی که از یک کتاب خاص صفحه‌ای خوانده‌اند
    public static void totalUsersForBook(Scanner scanner) {
        System.out.print("Enter book title to count users: ");
        String book = scanner.nextLine();

        String sql = "SELECT COUNT(DISTINCT user_id) AS totalUsers FROM ReadingHabit WHERE book = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalUsers = rs.getInt("totalUsers");
                System.out.println("Total users who read the book '" + book + "': " + totalUsers);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // مجموع کل صفحات خوانده شده توسط همه کاربران
    public static void totalPagesRead() {
        String sql = "SELECT SUM(pagesRead) AS totalPages FROM ReadingHabit";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int totalPages = rs.getInt("totalPages");
                System.out.println("Total pages read by all users: " + totalPages);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // تعداد کاربرانی که بیش از یک کتاب خوانده‌اند
    public static void usersReadingMoreThanOne() {
        String sql = "SELECT COUNT(*) AS totalUsers FROM (" +
                "SELECT user_id FROM ReadingHabit GROUP BY user_id HAVING COUNT(habitID) > 1" +
                ")";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int totalUsers = rs.getInt("totalUsers");
                System.out.println("Total users who have read more than one book: " + totalUsers);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
