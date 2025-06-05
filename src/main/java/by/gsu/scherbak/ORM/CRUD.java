package by.gsu.scherbak.ORM;


import by.gsu.scherbak.tables.Captain;
import by.gsu.scherbak.tables.Ship;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.*;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

/*
 * Class for ORM-logic methods
 * It provides all methods for CRUD operations
 *
 * @version      1.0 23.05.2025
 * @author       Scherbak Andrey
 * */
public class CRUD {
    private static final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("sea_port_pu");

    /*This method only for initializing emf on the application start*/
    public static void initializeEmf(){
    }

    /*Method for closing emf*/
    public static void closeEmf(){
        if (emf.isOpen()) {
            emf.close();
        }
    }

    /*Method for reading data from tables*/
    public static void readFromTable(Integer tableChoice){
        List<Ship> ships;
        List<Captain> captains;
        EntityManager entityManager = emf.createEntityManager();

        switch(tableChoice){
            //Reading from Ship table
            case 1:
                ships = entityManager
                        .createQuery("SELECT s FROM Ship s", Ship.class)
                        .getResultList();

                System.out.printf("%-5s %-20s %-15s %-10s %-15s%n",
                        "ID", "Ship Name", "Tonnage", "Year", "Captain");
                System.out.println("-----------------------------------------------" +
                        "-------------------");

                for(Ship ship : ships){
                    System.out.printf("%-5d %-20s %-15d %-10s %-15s%n",
                            ship.getId(),
                            ship.getShipName(),
                            ship.getTonnage(),
                            ship.getConstructionDate(),
                            ship.getCaptain() != null ? ship.getCaptain().getName() : "None"
                    );
                }
                break;
            //Reading from Ship_captain table
            case 2:
                captains = entityManager
                        .createQuery("SELECT c FROM Captain c", Captain.class)
                        .getResultList();

                System.out.printf("%-5s %-12s %-12s %-12s %-4s %-10s %-12s%n",
                        "ID", "Name", "Surname", "Lastname", "Age"
                        , "Experience", "Birth Date");
                System.out.println("-----------------------------------------" +
                        "---------------------------------------------");

                for(Captain captain: captains){
                    System.out.printf("%-5d %-12s %-12s %-12s %-4d %-10d %-12s%n",
                            captain.getId(),
                            captain.getName(),
                            captain.getSurname(),
                            captain.getLastname(),
                            captain.getAge(),
                            captain.getExperience(),
                            captain.getBirthDate()
                    );
                }
                break;
        }

        entityManager.close();
    }

    /*This method returns record by the given id and the table*/
    public static <T> T getRecordById(Integer id, Class<T> entityClass){
        EntityManager entityManager = emf.createEntityManager();

        try {
            return entityManager.find(entityClass, id);
        } finally {
            entityManager.close();
        }
    }

    /*Saving records into the tables method*/
    public static <T> void saveRecord(T entity){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            entityManager.close();
        }
    }

    /*Deleting records from the tables method*/
    public static <T> void deleteRecord(T entity){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        T managedEntity;

        try {
            transaction.begin();
            managedEntity = entityManager.merge(entity);
            entityManager.remove(managedEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            entityManager.close();
        }
    }

    /*Criteria API selection - All records in table Ship with year bigger than arg*/
    public static List<Ship> shipDateSelection(LocalDate arg){
        EntityManager entityManager = emf.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> cq = cb.createQuery(Ship.class);
        Root<Ship> ship = cq.from(Ship.class);

        cq.select(ship).where(cb.greaterThan(ship.get("constructionDate"), arg));

        return entityManager.createQuery(cq).getResultList();
    }

    /*Criteria API selection - All records in table Ship with captain older than arg*/
    public static List<Ship> shipCaptainAgeSelection(Integer arg){
        EntityManager entityManager = emf.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> cq = cb.createQuery(Ship.class);
        Root<Ship> ship = cq.from(Ship.class);

        Join<Ship, Captain> captain = ship.join("captain");

        Predicate captainOlderThanArg = cb.greaterThan(captain.get("age"), arg);
        cq.select(ship).where(captainOlderThanArg);

        return entityManager.createQuery(cq).getResultList();
    }

    /*Criteria API updating - increasing experience for all captains with age > 30*/
    public static List<Captain> increaseCaptainExperience(){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Captain> cu = cb.createCriteriaUpdate(Captain.class);
        CriteriaQuery<Captain> cq;
        Root<Captain> selectRoot;
        Root<Captain> updateRoot = cu.from(Captain.class);

        transaction.begin();
        cu.set("experience", cb.sum(updateRoot.get("experience"), 1))
                .where(cb.gt(updateRoot.get("age"), 30));
        entityManager.createQuery(cu).executeUpdate();

        cq = cb.createQuery(Captain.class);
        selectRoot = cq.from(Captain.class);
        cq.select(selectRoot);

        transaction.commit();

        return entityManager.createQuery(cq).getResultList();
    }

    /*Criteria API deleting - deleting captain from Captain table by given arg (name of the captain)*/
    public static void deleteCaptainByName(String arg){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Captain> cd = cb.createCriteriaDelete(Captain.class);
        Root<Captain> root = cd.from(Captain.class);

        cd.where(cb.equal(root.get("name"), arg));

        transaction.begin();
        entityManager.createQuery(cd).executeUpdate();
        transaction.commit();
    }
}
