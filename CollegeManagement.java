import java.sql.*;
import java.util.Scanner;

public class CollegeManagement {
    private static final String DB_URL = "jdbc:odbc:collegeDSN";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Scanner scanner = new Scanner(System.in)) {
            
            createTable(conn);
            
            while (true) {
                System.out.println("Choose an operation: 1-Insert, 2-Update, 3-Delete, 4-Display, 5-Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        insertRecord(conn, scanner);
                        break;
                    case 2:
                        updateRecord(conn, scanner);
                        break;
                    case 3:
                        deleteRecord(conn, scanner);
                        break;
                    case 4:
                        displayRecords(conn);
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE Courses (CourseID AUTOINCREMENT PRIMARY KEY, Name TEXT(100), Credits INTEGER)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table 'Courses' created successfully.");
        } catch (SQLException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("Table 'Courses' already exists.");
            } else {
                throw e;
            }
        }
    }

    private static void insertRecord(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter course name:");
        String name = scanner.next();
        System.out.println("Enter credits:");
        int credits = scanner.nextInt();
        
        String sql = "INSERT INTO Courses (Name, Credits) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, credits);
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");
        }
    }

    private static void updateRecord(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter CourseID to update:");
        int courseID = scanner.nextInt();
        System.out.println("Enter new credits:");
        int credits = scanner.nextInt();
        
        String sql = "UPDATE Courses SET Credits = ? WHERE CourseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, credits);
            pstmt.setInt(2, courseID);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("No record found with given CourseID.");
            }
        }
    }

    private static void deleteRecord(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter CourseID to delete:");
        int courseID = scanner.nextInt();
        
        String sql = "DELETE FROM Courses WHERE CourseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with given CourseID.");
            }
        }
    }

    private static void displayRecords(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Courses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("CourseID | Name | Credits");
            while (rs.next()) {
                System.out.println(rs.getInt("CourseID") + " | " + rs.getString("Name") + " | " + rs.getInt("Credits"));
            }
        }
    }
}

