package restServiceOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.internal.BootstrapServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import restServiceOne.hibernate.entity.UserEntity;



public class HibernateTest1 {
    public static void main(String[] args) {


        UserEntity usr = new UserEntity(null,"Vladimir","123123","myemail");


        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession()
            )
        {
            session.beginTransaction();
            session.persist(usr);
            session.getTransaction().commit();

        } catch (Exception e){
            throw e;
        }


    }
}
