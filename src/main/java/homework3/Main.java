package homework3;

import homework3.DAO.DAOFactory;
import homework3.DAO.IAnimalDAO;
import homework3.DAO.IDAOFactory;
import homework3.Entity.Animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private final static IDAOFactory factory = DAOFactory.getInstance();
    private final static IAnimalDAO animalDAO = factory.getAnimalDAO();

    public static void main(String[] args) {



        animalDAO.getAnimal(1).ifPresent(System.out::println);
        Animal animal2 = new Animal("Cat", 12, true);
        Animal animal3 = new Animal("Dog", 2, false);
        animalDAO.updateAnimal(1, animal2 );
        animalDAO.addAnimal(animal3);
    }
}
