package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import it.eng.care.domain.flow.core.dto.SearchPatientDTO;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class FlowPatientSearchConverter implements PrivacyManagerLogConverter<SearchPatientDTO>{

	@Override
	public List<EventResponseAM> convertResponse(SearchPatientDTO output, Object[] input) {
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		for(Object[] row: output.getRows()) {
			EventResponseAM response = new EventResponseAM();
			SubjectAM subject = new SubjectAM();
			subject.setName((String)row[1]);
			subject.setSurname((String)row[2]);
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date)row[5]);
			subject.setDateOfBirth(cal);
			subject.setTaxCode((String)row[0]);
			response.setSubject(subject);
	  
			Collection<EventResponseItemAM> items = new ArrayList<>();
			EventResponseItemAM item = new EventResponseItemAM();
			Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
	   
			item.setKey("Flusso");
			item.setValue(output.getFlow().getName());
			item.setDataSetScope(code);
			items.add(item);
			response.setItems(items);
			list.add(response);
		}
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		SearchPatientDTO input = (SearchPatientDTO) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Ricerca delle pratiche di un paziente");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		RequestMetadata mt = new RequestMetadata();
		if(input.getTaxCode()!=null) {
			mt.setKey("TaxCode");
			mt.setValue(input.getTaxCode());
			requestMetadatas.add(mt);
		}else {
			mt.setKey("Nome");
			mt.setValue(input.getName());
			requestMetadatas.add(mt);
			RequestMetadata mt1 = new RequestMetadata();
			mt1.setKey("Cognome");
			mt1.setValue(input.getSurname());
			requestMetadatas.add(mt1);
			RequestMetadata mt2 = new RequestMetadata();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			mt2.setKey("DataNascita");
			mt2.setValue(dateFormat.format(input.getBirthDate()));
			requestMetadatas.add(mt2);
		}
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}

}
