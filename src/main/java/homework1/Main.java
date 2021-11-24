package homework1;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb3";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

    public static void main(String[] args) {
        registerDriver();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT phone, city FROM namesphones JOIN familycity ON namesphones.id = familycity.id;");

            System.out.println("Номера и города сотрудников: ");
            while (resultSet.next()) {
                String phone = resultSet.getString("phone");
                String city = resultSet.getString("city");
                System.out.println("phone: " + phone + "; " + "city: " + city);
            }
            System.out.println("______________________________________________________________________");
            System.out.println("Номера и даты рождения всех холостых сотрудников:");

            resultSet = statement.executeQuery("SELECT phone, birthday FROM namesphones JOIN familycity ON namesphones.id WHERE maritalstatus = 'single';");

            while ((resultSet.next())) {
                String phone = resultSet.getString("phone");
                String bd = resultSet.getString("birthday");
                System.out.println("phone: " + phone + "; " + "birthday: " + bd);
            }
            System.out.println("______________________________________________________________________");
            System.out.println("Номера и даты рождения всех менеджеров:");
            resultSet = statement.executeQuery("SELECT phone, birthday FROM namesphones\n" +
                    "JOIN familycity ON namesphones.id = familycity.id JOIN work ON work.id = namesphones.id\n" +
                    "WHERE work.position = 'manager';");
            while ((resultSet.next())) {
                String phone = resultSet.getString("phone");
                String bd = resultSet.getString("birthday");
                System.out.println("phone: " + phone + "; " + "birthday: " + bd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

