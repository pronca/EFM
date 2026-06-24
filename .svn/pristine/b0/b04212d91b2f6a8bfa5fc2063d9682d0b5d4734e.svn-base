package it.eng.care.domain.flow.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "STATEMACHINE_HISTORY_PERSIST")
public class StateMachinePersistHistoryDO {


    @Id
    @Column(name = "ID")
    protected String id;

    @Column(name = "STATE")
    protected String state;

    @Column(name = "EVENT")
    protected String event;

    @Column(name = "DATESTATUS")
    protected Date dateStatus;

    @Column(name = "ACTUALUSER")
    protected String user;

    @Column(name = "ENTITYID")
    protected String entityId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(Date dateStatus) {
        this.dateStatus = dateStatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


}


