package it.eng.care.domain.flow.drools.model.lookup;

import java.util.Date;
import java.util.List;

public class LookupFieldFilter {

	private List<LookupField> fields;

	private Date referenceDate;

	public List<LookupField> getFields() {
		return fields;
	}

	public void setFields(List<LookupField> fields) {
		this.fields = fields;
	}

	public Date getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}

}
