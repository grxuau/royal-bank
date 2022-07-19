package restServiceOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.internal.BootstrapServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import restServiceOne.hibernate.entity.UserEntity;



public class HibernateTest1 {
    public static void main(String[] args) {


       // UserEntity usr = new UserEntity(1111,"Vladimir",null,null);


        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession()
            )
        {
            session.beginTransaction();
            System.out.println("first tran");
         //   session.persist(usr);
            session.getTransaction().commit();
            session.beginTransaction();
            System.out.println("second tran");
            session.getTransaction().commit();

        } catch (Exception e){
            throw e;
        }


    }
}
