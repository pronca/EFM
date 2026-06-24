package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STATE")
public class StateDO implements IBaseEntity {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "INITIALSTATE")
    private String initialState;
    @Column(name = "KIND")
    private String kind;
    @Column(name = "MACHINEID")
    private String machineId;
    @Column(name = "REGION")
    private String region;
    @Column(name = "STATE")
    private String state;
    @Column(name = "SUBMACHINEID")
    private String subMachineId;
    @Column(name = "INITIALACTION_ID")
    private String initialActionId;
    @Column(name = "PARENTSTATE_ID")
    private String parentStateId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubMachineId() {
        return subMachineId;
    }

    public void setSubMachineId(String subMachineId) {
        this.subMachineId = subMachineId;
    }

    public String getInitialActionId() {
        return initialActionId;
    }

    public void setInitialActionId(String initialActionId) {
        this.initialActionId = initialActionId;
    }

    public String getParentStateId() {
        return parentStateId;
    }

    public void setParentStateId(String parentStateId) {
        this.parentStateId = parentStateId;
    }


}
