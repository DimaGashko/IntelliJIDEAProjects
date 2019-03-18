package learn.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class Main {
    Connection connection;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            connect();
            System.out.println("Connected!");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employee WHERE emp_id = 102");

            while(rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        LocalDate.parse(rs.getString("birth_day")),
                        rs.getString("sex"),
                        rs.getInt("salary"),
                        rs.getInt("super_id"),
                        rs.getInt("branch_id")
                );

                System.out.println(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't connect to the Database");
        }
    }

    void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_db",
                "db_user", "qqqqqqqqww");
    }
}
