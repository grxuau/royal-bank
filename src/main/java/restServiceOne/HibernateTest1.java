package restServiceOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import restServiceOne.hibernate.entity.UserEntity;



public class HibernateTest1 {
    public static void main(String[] args) {


        UserEntity usr = new UserEntity(1111,"Vladimir","123123",null);

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
            System.out.println(e.getMessage());
        }


    }
}
