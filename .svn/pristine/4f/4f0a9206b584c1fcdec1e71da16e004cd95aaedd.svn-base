package it.eng.care.domain.flow.tabgen.exception;

import it.eng.care.domain.flow.tabgen.dto.OperationTypeEnum;

import java.util.List;


/**
 * 
 * @author utente
 */
public class InvalidTabgenOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// SULLA TABELLA

	boolean absentTable; // flag che indica che la tabella dove caricare i dati Ã¨ assente
	boolean absentFields; // flag che indica che la tabella dove caricare i dati non ha i campi

	boolean duplicatedTable; // flag che indica se la tabella giÃ  esiste
	boolean differentSchemas;// flag che indica se gli schemi (foglio excel e tabella) sono uguali
	private OperationTypeEnum operation;// operazione sul tabgen
	private String sheetName;// nome dello sheet dell'excel
	private String inputFilename;// file excel
	private Integer sheetColumnNum;// numero di colonne dello sheet dell'excel
	private Integer tableColumnNum;// numero di colonne della tabella
	private String absentSheetColumn;// colonna dello sheet assente nella tabella
	private boolean tableAssociatedValues; // flag che indica se la tabella ha dei valori associati
	private boolean tableAssociatedFields; // flag che indica se la tabella ha dei campi associati
	private Integer errorExcelRowNumber; // numero di riga dell'excel che ha generato l'errore

	// SUI CAMPI DELLA TABELLA
	private List<String> fields; // campi della tabella su cui fare l'operation
	private String fieldTable; // tabella dei campi da cancellare
	private List<String> referencedFields; // campi con altri campi collegati (i referenziati)
	private List<String> referencingFields; // campi che referenziano un altro (i referenzianti)
	// private String fieldToSaveOrUpdate;// campo della tabella da salvare/aggiornare
	// private String fieldTableToSaveOrUpdate;// tabella del campo della tabella da salvare/aggiornare
	private boolean fieldAssociatedValues; // flag che indica se il campo della tabella ha valori associati
	private boolean fieldReferencingFields; // flag che indica se il campo della tabella ha campi che lo referenziano
	private boolean duplicatedProgressive; // flag che indica se il progressivo del campo della tabella giÃ  esiste
	private boolean duplicatedValueColumnName; // flag che indica se il valueColumn del campo della tabella giÃ  esiste
	private boolean invalidValueColumnName; // flag che indica se il valueColumn del campo Ã¨ non valido
	private boolean notEnablingFk; // flag che indica se la fk Ã¨ non abilitabile
	private String referencedField; // campo referenziato
	private String referencedFieldTable; // tabella del campo referenziato
	private boolean notEnablingNotNullable; // flag che indica se il constraint di not null Ã¨ non abilitabile
	private boolean notEnablingPk; // flag che indica se la pk Ã¨ non abilitabile
	private boolean notDisablingPk; // flag che indica se la pk Ã¨ non disabilitabile perchÃ¨ viola altre pk

	private String tableToDelete;// tabella da cancellare

	// SUI VALORI DELLA TABELLA
	private String value;// valore della tabella su cui fare l'operation
	private String valueTable;// tabella del valore della tabella da salvare/aggiornare
	// private String valueToDelete;// valore della tabella da cancellare
	// private String valueTableToDelete;// tabella del valore della tabella da cancellare
	private String absentValueField;// campo non esistente in tabella per il valore da salvare/aggiornare
	private boolean notNullable; // flag che indica che il campo del valore da inserire/aggiornare non puÃ² essere nullo
	private String notNullableField; // campo del valore da inserire/aggiornare che non puÃ² essere nullo
	private boolean violatedFk; // flag che indica se ho violato una fk
	private boolean violatedPk; // flag che indica se ho violato una pk
	private String violatedFkField; // campo che viola della fk
	private String violatedFkRefField; // campo violato della fk
	private String violatedFkRefFieldTable; // tabella del campo violato della fk
	private boolean valueReferencingValues; // flag che indica se il valore della tabella ha valori che lo referenziano
	
	private boolean unexpectedError;

	public boolean isFieldAssociatedValues() {
		return fieldAssociatedValues;
	}

	public void setFieldAssociatedValues(boolean fieldAssociatedValues) {
		this.fieldAssociatedValues = fieldAssociatedValues;
	}

	public boolean isFieldReferencingFields() {
		return fieldReferencingFields;
	}

	public void setFieldReferencingFields(boolean fieldReferencingFields) {
		this.fieldReferencingFields = fieldReferencingFields;
	}

	public boolean isTableAssociatedValues() {
		return tableAssociatedValues;
	}

	public void setTableAssociatedValues(boolean tableAssociatedValues) {
		this.tableAssociatedValues = tableAssociatedValues;
	}

	public boolean isTableAssociatedFields() {
		return tableAssociatedFields;
	}

	public void setTableAssociatedFields(boolean tableAssociatedFields) {
		this.tableAssociatedFields = tableAssociatedFields;
	}

	public InvalidTabgenOperationException() {
		super("Operation is not valid!");
	}

	public InvalidTabgenOperationException(String message) {
		super(message);
	}

	public InvalidTabgenOperationException(String message, OperationTypeEnum operation, String sheetName, String inputFilename) {
		super(message);
		setOperation(operation);
		setSheetName(sheetName);
		setInputFilename(inputFilename);
	}

	public OperationTypeEnum getOperation() {
		return operation;
	}

	public void setOperation(OperationTypeEnum operation) {
		this.operation = operation;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getInputFilename() {
		return inputFilename;
	}

	public void setInputFilename(String inputFilename) {
		this.inputFilename = inputFilename;
	}

	public Integer getSheetColumnNum() {
		return sheetColumnNum;
	}

	public void setSheetColumnNum(Integer sheetColumnNum) {
		this.sheetColumnNum = sheetColumnNum;
	}

	public Integer getTableColumnNum() {
		return tableColumnNum;
	}

	public void setTableColumnNum(Integer tableColumnNum) {
		this.tableColumnNum = tableColumnNum;
	}

	public String getAbsentSheetColumn() {
		return absentSheetColumn;
	}

	public void setAbsentSheetColumn(String absentSheetColumn) {
		this.absentSheetColumn = absentSheetColumn;
	}

	public String getTableToDelete() {
		return tableToDelete;
	}

	public void setTableToDelete(String tableToDelete) {
		this.tableToDelete = tableToDelete;
	}

	public String getAbsentValueField() {
		return absentValueField;
	}

	public void setAbsentValueField(String absentValueField) {
		this.absentValueField = absentValueField;
	}

	public boolean isDuplicatedTable() {
		return duplicatedTable;
	}

	public void setDuplicatedTable(boolean duplicatedTable) {
		this.duplicatedTable = duplicatedTable;
	}

	public boolean isDifferentSchemas() {
		return differentSchemas;
	}

	public void setDifferentSchemas(boolean differentSchemas) {
		this.differentSchemas = differentSchemas;
	}

	public boolean isNotNullable() {
		return notNullable;
	}

	public void setNotNullable(boolean notNullable) {
		this.notNullable = notNullable;
	}

	public String getNotNullableField() {
		return notNullableField;
	}

	public void setNotNullableField(String notNullableField) {
		this.notNullableField = notNullableField;
	}

	public boolean isDuplicatedProgressive() {
		return duplicatedProgressive;
	}

	public void setDuplicatedProgressive(boolean duplicatedProgressive) {
		this.duplicatedProgressive = duplicatedProgressive;
	}

	public boolean isViolatedFk() {
		return violatedFk;
	}

	public void setViolatedFk(boolean violatedFk) {
		this.violatedFk = violatedFk;
	}

	public String getViolatedFkField() {
		return violatedFkField;
	}

	public void setViolatedFkField(String violatedFkField) {
		this.violatedFkField = violatedFkField;
	}

	public String getViolatedFkRefField() {
		return violatedFkRefField;
	}

	public void setViolatedFkRefField(String violatedFkRefField) {
		this.violatedFkRefField = violatedFkRefField;
	}

	public String getViolatedFkRefFieldTable() {
		return violatedFkRefFieldTable;
	}

	public void setViolatedFkRefFieldTable(String violatedFkRefFieldTable) {
		this.violatedFkRefFieldTable = violatedFkRefFieldTable;
	}

	public String getReferencedField() {
		return referencedField;
	}

	public void setReferencedField(String referencedField) {
		this.referencedField = referencedField;
	}

	public String getReferencedFieldTable() {
		return referencedFieldTable;
	}

	public void setReferencedFieldTable(String referencedFieldTable) {
		this.referencedFieldTable = referencedFieldTable;
	}

	public boolean isNotEnablingFk() {
		return notEnablingFk;
	}

	public void setNotEnablingFk(boolean notEnablingFk) {
		this.notEnablingFk = notEnablingFk;
	}

	public boolean isNotEnablingNotNullable() {
		return notEnablingNotNullable;
	}

	public void setNotEnablingNotNullable(boolean notEnablingNotNullable) {
		this.notEnablingNotNullable = notEnablingNotNullable;
	}

	public List<String> getReferencedFields() {
		return referencedFields;
	}

	public void setReferencedFields(List<String> referencedFields) {
		this.referencedFields = referencedFields;
	}

	public List<String> getReferencingFields() {
		return referencingFields;
	}

	public void setReferencingFields(List<String> referencingFields) {
		this.referencingFields = referencingFields;
	}

	public boolean isDuplicatedValueColumnName() {
		return duplicatedValueColumnName;
	}

	public void setDuplicatedValueColumnName(boolean duplicatedValueColumnName) {
		this.duplicatedValueColumnName = duplicatedValueColumnName;
	}

	public boolean isValueReferencingValues() {
		return valueReferencingValues;
	}

	public void setValueReferencingValues(boolean valueReferencingValues) {
		this.valueReferencingValues = valueReferencingValues;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getFieldTable() {
		return fieldTable;
	}

	public void setFieldTable(String fieldTable) {
		this.fieldTable = fieldTable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueTable() {
		return valueTable;
	}

	public void setValueTable(String valueTable) {
		this.valueTable = valueTable;
	}

	public boolean isAbsentTable() {
		return absentTable;
	}

	public void setAbsentTable(boolean absentTable) {
		this.absentTable = absentTable;
	}

	public boolean isAbsentFields() {
		return absentFields;
	}

	public void setAbsentFields(boolean absentFields) {
		this.absentFields = absentFields;
	}

	public boolean isViolatedPk() {
		return violatedPk;
	}

	public void setViolatedPk(boolean violatedPk) {
		this.violatedPk = violatedPk;
	}

	public boolean isNotEnablingPk() {
		return notEnablingPk;
	}

	public void setNotEnablingPk(boolean notEnablingPk) {
		this.notEnablingPk = notEnablingPk;
	}

	public boolean isNotDisablingPk() {
		return notDisablingPk;
	}

	public void setNotDisablingPk(boolean notDisablingPk) {
		this.notDisablingPk = notDisablingPk;
	}

	public boolean isInvalidValueColumnName() {
		return invalidValueColumnName;
	}

	public void setInvalidValueColumnName(boolean invalidValueColumnName) {
		this.invalidValueColumnName = invalidValueColumnName;
	}

	public Integer getErrorExcelRowNumber() {
		return errorExcelRowNumber;
	}

	public void setErrorExcelRowNumber(Integer errorExcelRowNumber) {
		this.errorExcelRowNumber = errorExcelRowNumber;
	}

	public boolean isUnexpectedError() {
		return unexpectedError;
	}

	public void setUnexpectedError(boolean unexpectedError) {
		this.unexpectedError = unexpectedError;
	}
	
	

}
