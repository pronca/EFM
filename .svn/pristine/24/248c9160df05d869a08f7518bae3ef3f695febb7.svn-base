package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.eng.care.domain.flow.core.dto.SearchFlowPatientFilter;
import it.eng.care.domain.flow.core.dto.SearchPatientDTO;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class FlowPatientGetConverter implements PrivacyManagerLogConverter<SearchPatientDTO>{

	@Override
	public List<EventResponseAM> convertResponse(SearchPatientDTO output, Object[] input) {
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		EventResponseAM response = new EventResponseAM();
		
		SearchFlowPatientFilter paziente = (SearchFlowPatientFilter) input[0];
		HashMap<String,String> traduzioni = (HashMap<String,String>) input[1];
		HashMap<String,Object> fields = (HashMap<String,Object>) paziente.getFilters();
				
		for(Object[] row: output.getRows()) {
			SubjectAM subject = new SubjectAM();
			
			traduzioni.forEach((k,v)-> {
				fields.forEach((c,w)-> {
					if(v.equalsIgnoreCase(c)) {
						if(k.equalsIgnoreCase("CODICEFISCALE")) {
							subject.setTaxCode((String)w);
						}
						if(k.equalsIgnoreCase("NOME")) {
							subject.setName((String)w);	
						}
						if(k.equalsIgnoreCase("COGNOME")) {
							subject.setSurname((String)w);
						}
						if(k.equalsIgnoreCase("DATANASCITA")) {
							Calendar cal = Calendar.getInstance();
							cal.setTime((Date)w);
							subject.setDateOfBirth(cal);
						}
					}
				});
			});
			
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
		
		SearchFlowPatientFilter input = (SearchFlowPatientFilter) args[0];
		HashMap<String,String> traduzioni = (HashMap<String,String>) args[1];
		HashMap<String,Object> fields = (HashMap<String,Object>) input.getFilters();
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Visualizzazione pratiche di un paziente");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		
		traduzioni.forEach((k,v)-> {
			fields.forEach((c,w)-> {
				if(v.equalsIgnoreCase(c)) {
					if(k.equalsIgnoreCase("CODICEFISCALE")) {
						RequestMetadata mt = new RequestMetadata();
						mt.setKey("TaxCode");
						mt.setValue((String)w);
						requestMetadatas.add(mt);					
					}
					if(k.equalsIgnoreCase("NOME")) {
						RequestMetadata mt = new RequestMetadata();
						mt.setKey("Nome");
						mt.setValue((String)w);
						requestMetadatas.add(mt);					
					}
					if(k.equalsIgnoreCase("COGNOME")) {
						RequestMetadata mt = new RequestMetadata();
						mt.setKey("Cognome");
						mt.setValue((String)w);
						requestMetadatas.add(mt);					
					}
					if(k.equalsIgnoreCase("DATANASCITA")) {
						RequestMetadata mt = new RequestMetadata();
						mt.setKey("Datanascita");
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date datanascita = (Date)w;
						mt.setValue(dateFormat.format(datanascita));
						requestMetadatas.add(mt);
					}
				}
			});
		});
			
		RequestMetadata mt = new RequestMetadata();
		mt.setKey("Flusso");
		mt.setValue(input.getFlow().getName());
		requestMetadatas.add(mt);
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}
}