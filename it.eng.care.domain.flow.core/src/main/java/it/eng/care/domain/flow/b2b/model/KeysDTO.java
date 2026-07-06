package it.eng.care.domain.flow.b2b.model;

import java.util.Objects;

public class KeysDTO {

    String key;
    String value;
    

    public KeysDTO() {
		super();
	}

	public KeysDTO(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeysDTO)) return false;
        KeysDTO keysDTO = (KeysDTO) o;
        return Objects.equals(key, keysDTO.key)
                && Objects.equals(value, keysDTO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
    
}
