package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.TableNamingStrategy;
import it.eng.care.platform.persistence.impl.jpa.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FM_ERROR_MESSAGE")
@TableNamingStrategy(fieldPrefix = "")
public class ErrorMessageDO extends AbstractEntity {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SEVERITY")
    private String severity;

    @Column(name = "TYPE")
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
