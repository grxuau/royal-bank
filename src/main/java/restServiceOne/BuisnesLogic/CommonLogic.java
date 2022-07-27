package restServiceOne.BuisnesLogic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import restServiceOne.hibernate.entity.UserEntity;

public class CommonLogic {
    /*
    Method for saving data to the database (the saveOrUpdate() method is used).
    Used if the name of the configuration file differs from the standard one.
     */
    public static <T> boolean saveData(T data, String configFile, Object ... annotatedClasses) {

        try(SessionFactory sessionFactory = createConfiguration(annotatedClasses)
                .configure(configFile)
                .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession()
        )
        {
            session.beginTransaction();
            session.saveOrUpdate(data);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /*
    Method for saving data to the database (the saveOrUpdate() method is used).
     */
    public static <T> boolean saveData(T data, Object ... annotatedClasses) {

        try(SessionFactory sessionFactory = createConfiguration(annotatedClasses)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession()
        )
        {
            session.beginTransaction();
            session.saveOrUpdate(data);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /*
    Helper method for getting the configuration.
     */
    public static Configuration createConfiguration(Object... annotatedClasses) {
        Configuration configuration = new Configuration();
        for(Object annotatedClass: annotatedClasses) {
            configuration.addAnnotatedClass(annotatedClass.getClass());
        }
        return configuration;
    }
}
