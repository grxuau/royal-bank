package restServiceOne.BuisnesLogic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import restServiceOne.RRC.UserRRC;
import restServiceOne.SecurityController;
import restServiceOne.hibernate.entity.UserEntity;

public class UserLogic {

   public static UserRRC findUser(String name, String email){
       if (name.equals("") && email.equals("")){
           return new UserRRC(101,"Name or Email required");
       } else if (name.equals("")) {
           UserEntity usr = findUserByField("email",email);
           if (!(usr==null)){
               return new UserRRC(0,"", usr.getName(), usr.getEmail(), "");
           }
       } else if (email.equals("")) {
           UserEntity usr = findUserByField("name",name);
           if (!(usr==null)){
               return new UserRRC(0,"", usr.getName(), usr.getEmail(), "");
           }
       }
       return new UserRRC();
    }

    public static UserRRC getUser(String name, String email, String hashCode){
        if (name.equals("") && email.equals("")){
            return new UserRRC(101,"Name or Email required");

        } else if (hashCode.equals("")) {
            return new UserRRC(102,"Password (hashcode) required");

        } else if (name.equals("")) {
            UserEntity usr = findUserByField("email",email);
            if ( !(usr==null) && (usr.getHash().equals(hashCode)) ){
                String token = SecurityController.getToken(usr.getId());
                return new UserRRC(0,"", usr.getName(), usr.getEmail(), token);
            }

        } else if (email.equals("")) {
            UserEntity usr = findUserByField("name", name);
            if ( !(usr==null) && (usr.getHash().equals(hashCode)) ){
                String token = SecurityController.getToken(usr.getId());
                return new UserRRC(0,"", usr.getName(), usr.getEmail(), token);
            }
        }
        //User not found
        return new UserRRC();
    }

    private static UserEntity findUserByField(String fieldName, String fieldValue){

        UserEntity user = null;
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession()
        )
        {
            session.beginTransaction();
            Query<UserEntity> query = session.createQuery(
                    String.format("from UserEntity u where u.%s=:param",fieldName),
                    UserEntity.class);
            query.setParameter("param", fieldValue);
            user = query.uniqueResult();
            session.getTransaction().rollback();
       } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }
}
