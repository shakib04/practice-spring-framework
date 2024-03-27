package learning.practice.model;

import lombok.*;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student extends AbstractAuditableEntity {
    @Id
    //@GeneratedValue(generator = "custom-generator")
    //@GenericGenerator(name = "custom-generator", strategy = "com.example.CustomIdGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}

//@Component
//class MyCustomIdGenerator implements IdentifierGenerator {
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//        return new Date();
//    }
//}