package restServiceOne.BuisnesLogic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import restServiceOne.hibernate.entity.UserEntity;

public class CommonLogic {
    public static <T> boolean saveData(T data) {

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserEntity.class)
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
}
