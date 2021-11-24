package homework2;

import homework2.dao.ClientDAO;
import homework2.dao.DAOFactory;
import homework2.dao.IDAOFactory;
import homework2.entity.Client;

public class Main {
    private final static IDAOFactory factory = DAOFactory.getInstance();
    private final static ClientDAO clientDAO = factory.getclientDAO();
    public static void main(String[] args) {

        Client client = new Client(4, "yura", 23, "0933123");
        clientDAO.add(client);

    }
}
