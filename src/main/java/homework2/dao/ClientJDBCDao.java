package homework2.dao;

import homework2.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientJDBCDao implements ClientDAO {
    @Override
    public void add(Client client) {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO clients(name, age, phone) VALUES (?,?,?,?)");
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getPhone());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM clients");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Client client = new Client(
                        (int) result.getLong("id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getString("phone"));

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }


        return clients;
    }


    @Override
    public void updateClients(int id, Client client) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE clients SET name = ?, age = ?, phone = ? WHERE id = ?");
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getPhone());
            statement.setLong(4, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    public Optional<Client> getClient(int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Client client = new Client(
                        (int) result.getLong("id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getString("phone")
                );

                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

        return Optional.empty();
    }

    @Override
    public void deleteClient(int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        if(getClient(id).isPresent()) {
            try {
                statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");
                statement.setLong(1, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnections(connection, statement);
            }
        }

    }



    private void closeConnections(Connection connection, PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop", "root", "rootroot");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
