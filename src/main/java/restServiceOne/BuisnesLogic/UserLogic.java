package restServiceOne.BuisnesLogic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import restServiceOne.RRC.StandartRRC;
import restServiceOne.RRC.UserDAO;
import restServiceOne.SecurityController;
import restServiceOne.hibernate.entity.UserEntity;

public class UserLogic {

   public static StandartRRC findUser(String name, String email){
       if (name.equals("") && email.equals("")){
           return new StandartRRC(101,"Name or Email required");
       } else {
           UserEntity usr = findUserByField("name", name);
           if (usr==null){
               usr = findUserByField("email",email);
           }
           if ( !(usr==null) ){
               UserDAO user = new UserDAO();
               BeanUtils.copyProperties(user,usr);
               return new StandartRRC(0,"", user);
           }
       }
       return new StandartRRC();
    }

    public static StandartRRC getUser(String name, String email, String hashCode){
        if (name.equals("") && email.equals("")){
            return new StandartRRC(101,"Name or Email required");

        } else if (hashCode.equals("")) {
            return new StandartRRC(102,"Password (hashcode) required");

        } else {
            UserEntity usr = findUserByField("name", name);
            if (usr==null){
                usr = findUserByField("email",email);
            }
            if ( !(usr==null) && (usr.getHash().equals(hashCode)) ){
                UserDAO user = new UserDAO();
                BeanUtils.copyProperties(user,usr);
                user.setToken(SecurityController.getToken(usr.getId()));
                return new StandartRRC(0,"", user);
            }
        }
        //User not found
        return new StandartRRC();
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
