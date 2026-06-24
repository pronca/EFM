package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class AnagraficaAssistitoSearchConverter implements PrivacyManagerLogConverter<AnagrafcaAssistitoPaginationDTO>{

	@Override
	public List<EventResponseAM> convertResponse(AnagrafcaAssistitoPaginationDTO output, Object[] input) {
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		for(AnagraficaAssistitoDTO anagraficaAssistitoDTO : output.getAnagraficaData()) {
			EventResponseAM response = new EventResponseAM();
			SubjectAM subject = new SubjectAM();
			subject.setName(anagraficaAssistitoDTO.getNome()!=null ? anagraficaAssistitoDTO.getNome() : "XXXXXXXXXX");
			subject.setSurname(anagraficaAssistitoDTO.getCognome()!=null ? anagraficaAssistitoDTO.getCognome() : "XXXXXXXXXX");
			if (anagraficaAssistitoDTO.getDatanascita()!=null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(anagraficaAssistitoDTO.getDatanascita());
				subject.setDateOfBirth(cal);
			}
			subject.setTaxCode(anagraficaAssistitoDTO.getCodiceFiscale()!=null ? anagraficaAssistitoDTO.getCodiceFiscale() : anagraficaAssistitoDTO.getId());
			response.setSubject(subject);
	  
			Collection<EventResponseItemAM> items = new ArrayList<>();
			EventResponseItemAM item = new EventResponseItemAM();
			Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
	   
			item.setKey("Anagrafica Assistito");
			item.setValue(anagraficaAssistitoDTO.getCodiceFiscale()!=null ? anagraficaAssistitoDTO.getCodiceFiscale() : anagraficaAssistitoDTO.getId());
			item.setDataSetScope(code);
			items.add(item);
			response.setItems(items);
			list.add(response);
		}
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		BaseSearchInput input = (BaseSearchInput) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Visualizzazione anagrafica assistito");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		String selectedOption = "";
		String filterValue = "";
		
		for (Entry<String, Object> entry : input.getParams().entrySet()) {
			if(entry.getKey().equalsIgnoreCase("SELECTEDOPTION")) {
				selectedOption=(String)entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("FILTERVALUE"))  {
				if (entry.getValue() instanceof Date) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					filterValue=dateFormat.format(entry.getValue());
				} else {
					filterValue=(String)entry.getValue();
				} 
			}
		}
		
		RequestMetadata mt = new RequestMetadata();
		if (selectedOption != null && !selectedOption.isEmpty() && filterValue != null && !filterValue.isEmpty()) {
			mt.setKey(selectedOption);
			mt.setValue(filterValue);
		}
		requestMetadatas.add(mt);
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}

}
