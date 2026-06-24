package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String initialState;
    private String kind;
    private String machineId;
    private String region;
    private String state;
    private String subMachineId;
    private String initialActionId;
    private String parentStateId;
    private String name;

    public StateDTO() {
    }

    public String getId() {
        return id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
