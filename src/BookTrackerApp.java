import java.util.Scanner;

public class BookTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("==== BookTracker Menu ====");
            System.out.println("1. Add a user");
            System.out.println("2. Show reading habits for a user");
            System.out.println("3. Update book title");
            System.out.println("4. Delete a reading habit record");
            System.out.println("5. Print mean age of users");
            System.out.println("6. Total users who read a specific book");
            System.out.println("7. Total pages read by all users");
            System.out.println("8. Total users who have read more than one book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    UserManager.addUser(scanner);
                    break;
                case 2:
                    ReadingHabitManager.showReadingHabits(scanner);
                    break;
                case 3:
                    ReadingHabitManager.updateBookTitle(scanner);
                    break;
                case 4:
                    ReadingHabitManager.deleteReadingHabit(scanner);
                    break;
                case 5:
                    UserManager.printMeanAge();
                    break;
                case 6:
                    ReadingHabitManager.totalUsersForBook(scanner);
                    break;
                case 7:
                    ReadingHabitManager.totalPagesRead();
                    break;
                case 8:
                    ReadingHabitManager.usersReadingMoreThanOne();
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
        scanner.close();
    }
}
