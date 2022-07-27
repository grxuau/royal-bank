package restServiceOne.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstrcutor - We can't use it while using @GeneratedValue
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "hashcode")
    private String hashcode;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public UserEntity(String name, String hashcode, String email) {
        this.name = name;
        this.hashcode = hashcode;
        this.email = email;
    }
}
