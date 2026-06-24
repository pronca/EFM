package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "FM_DRIVER")
public class DriverDO implements IBaseEntity {

    @Id
    @GeneratedValue(
            generator = "care-uuid"
    )
    @GenericGenerator(
            name = "care-uuid",
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator"
    )
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
