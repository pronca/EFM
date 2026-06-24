package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.platform.audit.api.model.privacymanager.AttachmentAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class UploadRitornoConverter implements PrivacyManagerLogConverter<byte[]>{
	
	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] input) {
		
		BaseSearchInput searchInput = (BaseSearchInput) input[0];
		String flowLocal = searchInput.getValue("flowName");
        String fileName = searchInput.getValue("fileName");
        String estrazId = searchInput.getValue("id");
        
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		EventResponseAM response = new EventResponseAM();
		Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
		AttachmentAM attac = new AttachmentAM();
		attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
		attac.setContent(Base64.getEncoder().encodeToString(output));
		attac.setName(fileName);
		
		attachments.add(attac);
		response.setAttachments(attachments);
		
		Collection<EventResponseItemAM> items = new ArrayList<>();
		Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
		
		EventResponseItemAM item = new EventResponseItemAM();
		item.setKey("Flusso");
		item.setValue(flowLocal);
		item.setDataSetScope(code);
		items.add(item);
		
		if (estrazId != null && !estrazId.isEmpty()) {
			EventResponseItemAM item1 = new EventResponseItemAM();
			item1.setKey("Estrazione Id");
			item1.setValue(estrazId);
			item1.setDataSetScope(code);
			items.add(item1);
		}
		
		response.setItems(items);
		list.add(response);
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		BaseSearchInput input = (BaseSearchInput) args[0];
        String flowLocal = input.getValue("flowName");
        String estrazId = input.getValue("id");
        
		EventResourceAM request = new EventResourceAM();
		if (estrazId != null && !estrazId.isEmpty()) {
			request.setRequest("Estrazione Flussi Upload Ritorno");
		} else {
			request.setRequest("Dashboard Upload Ritorno");
		}
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		RequestMetadata mt = new RequestMetadata();
		mt.setKey("Flusso");
		mt.setValue(flowLocal);
		requestMetadatas.add(mt);
		if (estrazId != null && !estrazId.isEmpty()) {
			RequestMetadata mt1 = new RequestMetadata();
			mt1.setKey("Estrazione Id");
			mt1.setValue(estrazId);
			requestMetadatas.add(mt1);
		}
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}

}
