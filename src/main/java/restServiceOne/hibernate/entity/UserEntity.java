package restServiceOne.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "Name, nullable = false, unique = true")
    private String name;
    @Column(name = "Pwd_hash")
    private String hash;
    @Column(name = "Email, nullable = false, unique = true")
    private String email;
}
