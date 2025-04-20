package org.devgen.jdbc;

import java.sql.*;

public class DatabaseConnector {
    final static String URL = "jdbc:mysql://localhost:3306/devgen";
    final static String USER = "root";
    final static String PASSWORD = "Padole@123";


    public static void main(String[] args) {

        // 1. load the driver
        // 2. Register the driver with Driver manager    // optional after java 6
        // 3. Establish the connection
        // 4. Create a statement to write query
        // 5. Execute the query
        // 6. Process the result
        // 7. Close the connection/statement/resultset




        String name = "bhushan";
        int age = 22;
        int id = 2;

        deleteRecordInDb(id);
//        updateRecordInDb(id, name, age);
        createDbConnection();
//        addRecordInDb(name, age);
    }

//    see all records
    private static void createDbConnection() {
        final String QUERY = "Select * from student";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUERY);) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(id + "  " + name + " " + age);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // insert record
    private static void addRecordInDb(String name, int age) {
//        final String query = "insert into student(name, age) values('"+name+"', "+age+");";
        final String query = "insert into student(name, age) values(?, ?);";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement =  connection.prepareStatement(query);
            ) {

//            int count = statement.executeUpdate(query);
//
//            if (count > 0) {
//                System.out.println("record inserted successfully");
//            }
//            else {
//                System.out.println("there is something issue");
//            }

//            PreparedStatement ps = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setInt(2, age);


            int count = statement.executeUpdate();

            if (count > 0) {
                System.out.println("record inserted successfully");
            }
            else {
                System.out.println("there is something issue");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // update
    private static void updateRecordInDb(int id,String name, int age) {
//        final String query = "insert into student(name, age) values('"+name+"', "+age+");";
        final String query = "update student set age=? where id = ?";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement =  connection.prepareStatement(query);
        ) {

            statement.setInt(1, age);
            statement.setInt(2, id);


            int count = statement.executeUpdate();

            if (count > 0) {
                System.out.println("record updated successfully");
            }
            else {
                System.out.println("there is something issue");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // delete

    private static void deleteRecordInDb(int id) {
//        final String query = "insert into student(name, age) values('"+name+"', "+age+");";
        final String query = "delete from student where id=?";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement =  connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);

            int count = statement.executeUpdate();

            if (count > 0) {
                System.out.println("record Deleted successfully");
            }
            else {
                System.out.println("there is something issue");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
