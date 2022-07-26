package restServiceOne.hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntity {
    @Id
    @Column(name = "id", unique = true)
    private Integer id;
    @Column(name = "value")
    private String value;
    @Column(name = "date")
    private java.sql.Timestamp date;
}