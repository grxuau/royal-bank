package restServiceOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import restServiceOne.hibernate.entity.User;



public class HibernateTest1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        User usr = new User(1111,"Vladimir","123123",null);

        try {
            session.beginTransaction();
            session.persist(usr);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        }
        finally {
            session.close();
            sessionFactory.close();
        }


    }
}
