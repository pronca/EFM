package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowOperationResult;
import it.eng.care.domain.flow.core.dto.ValidationFilter;
import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterField;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class FlowAvvioValidazioneConverter implements PrivacyManagerLogConverter<FlowOperationResult<?>>{
	
	@Override
	public List<EventResponseAM> convertResponse(FlowOperationResult<?> output, Object[] input) {
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		EventResponseAM response = new EventResponseAM();
		Collection<EventResponseItemAM> items = new ArrayList<>();
		
		ValidationFilter valFilter = (ValidationFilter) input[0];
		FlowOperationResult<?> esitoVal = (FlowOperationResult) input[1];
		FlowViewFilterField[] fields = valFilter.getField();
		Coding code = new Coding(ResourceItemScopeEnum.GENERICI);
		
		for (FlowViewFilterField field : fields) {
			EventResponseItemAM item1 = new EventResponseItemAM();
    		item1.setDataSetScope(code);
    		item1.setKey(field.getName());
    		item1.setValue(field.getParam());
    		items.add(item1);			
		}
		EventResponseItemAM item2 = new EventResponseItemAM();
		item2.setDataSetScope(code);
		item2.setKey("Flusso");
		item2.setValue(valFilter.getFlowId());
		items.add(item2);	
		
		response.setItems(items);
 
		EventResponseItemAM item = new EventResponseItemAM();   
		item.setKey("Validazione");
		if (esitoVal.getSuccess()) {
			item.setValue("Esito Positivo");
		} else {
			item.setValue("Esito Negativo");
		}

		item.setDataSetScope(code);
		items.add(item);
		response.setItems(items);
		list.add(response);
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		ValidationFilter input = (ValidationFilter) args[0];
		FlowViewFilterField[] fields = input.getField();
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Validazione pratica di un assistito");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		
		for (FlowViewFilterField field : fields) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey(field.getName());
			mt.setValue((String)field.getParam());
			requestMetadatas.add(mt);					
		}
			
		RequestMetadata mt = new RequestMetadata();
		mt.setKey("Flusso");
		mt.setValue(input.getFlowId());
		requestMetadatas.add(mt);
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}

}