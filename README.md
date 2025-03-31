# Introduction-to-SE
we will dive in to it :
# **BookTracker App**

## **Overview**  
BookTracker is a command-line application that allows users to track their reading habits. The application interacts with an SQLite database using Java Database Connectivity (JDBC) to store and manage user data.

## **How to Run**
1. Open the project in **IntelliJ IDEA** (or any Java-supported IDE).
2. Ensure that the database file is located in **`Resources/booktracker.db`**.
3. Run the `BookTrackerApp.java` class.

## **Key Features**
- **User Management:** Add a new user to the database.  
- **Reading Habits Tracking:** View all reading habits of a specific user.  
- **Book Management:** Update the title of a book in the database.  
- **Data Deletion:** Remove a record from the `ReadingHabit` table.  
- **Statistical Insights:**  
  - Calculate the mean age of users.  
  - Get the total number of users who have read pages from a specific book.  
  - Retrieve the total number of pages read by all users.  
  - Identify users who have read more than one book.  
- **Database Modification:** Add a new column (`Name`) to the `User` table to store user names.  

## **Additional Information**
- The application uses **SQLite** as the database.  
- SQL queries are used for calculations rather than Java-based logic to ensure efficiency.  
- No graphical interface is required; all interactions are done via the command line.
