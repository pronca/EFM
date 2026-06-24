package it.eng.care.domain.flow.core.enumeration;

public enum FieldType {
    TEXT("Text"),
    DATA("Date"),
    COMBOBOX("ComboBox"),
    MULTISELECT("Multi"),
    RADIO("Radio"),
    NUMERIC("Numeric"),
    CAMPI("Campi"),
    LOOKUP("Lookup"),
    CHIPS("Chips"),
    ;


    private String type;

    FieldType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
