package homework3.DAO;

import com.sun.jdi.connect.Connector;
import homework3.Entity.Animal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalDAO implements IAnimalDAO {
    @Override
    public void addAnimal(Animal animal) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        session.save(animal);

    }

    @Override
    public Optional<Animal> getAnimal(long id) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            return Optional.of(session.get(Animal.class, id));

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void updateAnimal(long id, Animal animal) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            Animal oldAnimal = session.get(Animal.class, id);

            oldAnimal.copyFrom(animal);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAnimal(long id) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            Animal animal = session.get(Animal.class, id);
            session.delete(animal);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            animals = session.createQuery("SELECT a FROM Animal a", Animal.class).list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return animals;
    }
    }

