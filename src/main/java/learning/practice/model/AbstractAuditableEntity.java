package learning.practice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
@Data
public abstract class AbstractAuditableEntity {

    @CreatedBy
    private String addedBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private Integer version;

    @PrePersist
    protected void onCreate() {
        addedBy = "System";
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedBy = "System";
    }
}
