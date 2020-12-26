package com.uni.common.entity;

import com.uni.common.core.Context;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class OrganizationEntity extends UserTimeEntity {
    public static final String ORGANIZATION_UID_COLUMN = "organization_uid";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = ORGANIZATION_UID_COLUMN, length = DataTypeLength.ID, nullable = false)
    private String organizationUid;

    @PrePersist
    private void assignOrganization() {
        if (organizationUid == null) {
            organizationUid = Context.getContext().getOrganizationUid();
        }
    }
}
