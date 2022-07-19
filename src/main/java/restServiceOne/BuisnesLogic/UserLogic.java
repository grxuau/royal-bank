package restServiceOne.BuisnesLogic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import restServiceOne.RRC.StandartRRC;
import restServiceOne.RRC.UserDTO;
import restServiceOne.SecurityController;
import restServiceOne.hibernate.entity.UserEntity;

import java.util.Optional;

public class UserLogic {

    public static StandartRRC findUser(String name, String email) {
        if (name.equals("") && email.equals("")) {
            return new StandartRRC(101, "Name or Email required");
        } else {

            UserEntity userEntity = findUserByField("name", name)
                    .orElseGet(() -> findUserByField("email", email).orElse(null) );

            if (!(userEntity == null)) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userEntity, userDTO);
                return new StandartRRC(0, "", userDTO);
            }
        }
        return new StandartRRC();
    }

    public static StandartRRC getUser(String name, String email, String hashCode) {
        if (name.equals("") && email.equals("")) {
            return new StandartRRC(101, "Name or Email required");

        } else if (hashCode.equals("")) {
            return new StandartRRC(102, "Password (hashcode) required");

        } else {

            UserEntity userEntity = findUserByField("name", name)
                    .orElseGet(() -> findUserByField("email", email).orElse(null) );

            if (!(userEntity == null) && (userEntity.getHash().equals(hashCode))) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userEntity, userDTO);
                userDTO.setToken(SecurityController.getToken(userEntity.getId()));
                return new StandartRRC(0, "", userDTO);
            }
        }
        //User not found
        return new StandartRRC();
    }

    private static Optional<UserEntity> findUserByField(String fieldName, String fieldValue) {

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()
        ) {
            session.beginTransaction();
            Query<UserEntity> query = session.createQuery(
                    String.format("from UserEntity u where u.%s=:param", fieldName),
                    UserEntity.class);
            query.setParameter("param", fieldValue);
            UserEntity user = query.uniqueResult();
            session.getTransaction().rollback();
            return Optional.of(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }
}
