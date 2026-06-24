package it.eng.care.domain.flow.core.enumeration;

public enum MachineState {
    RICHIESTA("RICHIESTA"),
    TERMINATA_OK("TERMINATA_OK"),
    TERMINATA_KO("TERMINATA_KO"),
    CANCELLATA("CANCELLATA"),
    IN_CORSO("IN_CORSO"),
    ANNULLATA("ANNULLATA"),
    ANNULAMENTO_IN_CORSO("ANNULAMENTO_IN_CORSO"),
    ANNULAMENTO_OK("ANNULAMENTO_OK"),
    ANNULAMENTO_KO("ANNULAMENTO_KO");

    private String state;

    MachineState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
