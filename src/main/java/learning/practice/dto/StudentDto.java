package learning.practice.dto;

import learning.practice.model.AbstractAuditableEntity;
import lombok.Data;

@Data
public class StudentDto extends AbstractAuditableEntity {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer version;
}
