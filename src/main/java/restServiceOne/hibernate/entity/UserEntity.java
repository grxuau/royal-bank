package restServiceOne.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Integer id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "surname", nullable = false, unique = false)
    private String surname;
    @Column(name = "patronymic", nullable = true, unique = false)
    private String patronymic;
    @Column(name = "date_of_birth", nullable = false, unique = false)
    private Date dateOfBirth;
    @JoinColumn(name = "passport_id", nullable = false, unique = true)
    private int passportId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private List<AccountEntity> accountId;
    @Column(name = "hashcode", nullable = false, unique = false)
    private String hashcode;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
