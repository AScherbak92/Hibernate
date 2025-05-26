package by.gsu.scherbak;

import by.gsu.scherbak.tables.Captain;
import by.gsu.scherbak.tables.Ship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
class AppTest {

    @Test
    void testLoadShip() {
        /*Ship ship;
        Captain cap;
        Configuration config = new Configuration();

        config.addAnnotatedClass(Ship.class);
        config.addAnnotatedClass(Captain.class);
        config.configure("hibernate.cfg.xml");

        try (SessionFactory factory = config.buildSessionFactory();
             Session session = factory.openSession()) {

            Transaction transaction = session.beginTransaction();

            //Getting ship with id = 2
            ship = session.get(Ship.class, 2);
            System.out.println(ship);

            //Getting captain with id = 3
            cap = session.get(Captain.class, 3);
            System.out.println(cap);

            //Reducing captain experience to 20
            cap.setExperience(20);
            session.update(cap);

            //Deleting captain with id 2
            //cap = session.get(Captain.class, 2);
            //session.delete(cap);
            //System.out.println("\nDELETED...");

            List<Captain> caps = session.createQuery("SELECT c FROM Captain c", Captain.class)
                    .list();
            caps.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
