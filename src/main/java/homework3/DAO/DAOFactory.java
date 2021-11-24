package homework3.DAO;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory;

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();

        }
        return factory;

    }

    @Override
    public IAnimalDAO getAnimalDAO() {
        return new AnimalDAO();
    }
}
