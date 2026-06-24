package it.eng.care.domain.flow.drools.model.row;

/**
 * Campo di una sezione di un flusso
 */
public class Field {

	/**
	 * nome del campo
	 */
	private String name;

	/**
	 * valore del campo
	 */
	private Object value;

	public Field(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
