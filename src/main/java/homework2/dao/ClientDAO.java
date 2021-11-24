package homework2.dao;

import homework2.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    void add(Client client);
    List<Client> getAllClients();
    Optional<Client> getClient(int id);
    void updateClients(int id, Client client);
    void deleteClient(int id);
}
