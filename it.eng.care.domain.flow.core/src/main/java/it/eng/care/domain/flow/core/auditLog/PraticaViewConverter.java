package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMPraticaView;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class PraticaViewConverter implements PrivacyManagerLogConverter<PMPraticaView>{
	
	@Override
	public List<EventResponseAM> convertResponse(PMPraticaView output, Object[] input) {
		
		List<Object[]> queryResult = output.getQueryResult();
		FlowViewFilterError filter = (FlowViewFilterError) input[0];
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		if (queryResult != null) {
			for(int i=0; i<queryResult.size(); i++) {
				boolean check = true;
				for(int j=0; j<queryResult.get(i).length; j++) {
					if(queryResult.get(i)[j] == null) {
						check = false;
						break;
					}
				}
				if(check) {
				
					EventResponseAM response = new EventResponseAM();
					
					SubjectAM subject = new SubjectAM();
					subject.setName((String)queryResult.get(i)[3]);
					subject.setSurname((String)queryResult.get(i)[1]);
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date)queryResult.get(i)[0]);
					subject.setDateOfBirth(cal);
					subject.setTaxCode((String)queryResult.get(i)[2]);
					response.setSubject(subject);

					Collection<EventResponseItemAM> items = new ArrayList<>();
					EventResponseItemAM item = new EventResponseItemAM();
					Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
			   
					item.setKey("Flusso");
					item.setValue(filter.getFlow().getName());
					item.setDataSetScope(code);
					items.add(item);
					response.setItems(items);
					list.add(response);
				}
			}
		}
		
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		FlowViewFilterError input = (FlowViewFilterError) args[0];
		EventResourceAM request = new EventResourceAM();
		
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		if(input.getMessage()!=null) {
			request.setRequest("Visualizzazione pratiche che presentano uno specifico errore");
			RequestMetadata mt = new RequestMetadata();
			mt.setKey("ERRORE");
			mt.setValue(input.getMessage());
			requestMetadatas.add(mt);
			request.setRequestMetadatas(requestMetadatas);
			
			RequestMetadata mt1 = new RequestMetadata();
			mt1.setKey("MESE DI RIFERIMENTO");
			mt1.setValue(input.getMonth());
			requestMetadatas.add(mt1);
			request.setRequestMetadatas(requestMetadatas);
			
			RequestMetadata mt2 = new RequestMetadata();
			mt2.setKey("ANNO DI RIFERIMENTO");
			mt2.setValue(input.getYear());
			requestMetadatas.add(mt2);
			request.setRequestMetadatas(requestMetadatas);
		}else {
			request.setRequest("Visualizzazione Pratiche");
			if(input.getFilters()!=null && !input.getFilters().isEmpty()) {
				request.setRequest("Visualizzazione Pratiche");
				Map<String, String> map = input.getFilters();
				map.forEach((k,v)-> {
					if(!"".equals(v)) {
						RequestMetadata mt = new RequestMetadata();
						mt.setKey(k);
						mt.setValue(v);
						requestMetadatas.add(mt);
						request.setRequestMetadatas(requestMetadatas);
					}
				});
			}
		}
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