package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.platform.audit.api.model.privacymanager.AttachmentAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class FlowViewDownloadConverter implements PrivacyManagerLogConverter<byte[]>{

	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] input) {
		
		FlowViewFilter flowViewFilter = (FlowViewFilter) input[0];
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		EventResponseAM response = new EventResponseAM();
		Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
		AttachmentAM attac = new AttachmentAM();
		attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
		attac.setContent(Base64.getEncoder().encodeToString(output));
		attac.setName("Xlsx Flusso " + flowViewFilter.getFlow().getName());
		attachments.add(attac);
		response.setAttachments(attachments);
		
		Collection<EventResponseItemAM> items = new ArrayList<>();
		EventResponseItemAM item = new EventResponseItemAM();
		Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
   
		item.setKey("Flusso");
		item.setValue(flowViewFilter.getFlow().getName());
		item.setDataSetScope(code);
		items.add(item);
		response.setItems(items);
		list.add(response);
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		FlowViewFilter input = (FlowViewFilter) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Download xlsx Flusso");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		for(int i=0; i<input.getField().length; i++) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey(input.getField()[i].getName());
			mt.setValue(input.getField()[i].getParam());
			requestMetadatas.add(mt);
		}
		int countA = 0;
		int countB = 0;
		for(int i=0; i<input.getFieldData().length; i++) {
			
			if (input.getFieldData()[i].getDateIn() != null && input.getFieldData()[i].getDateOut() != null && input.getFieldData()[i].getPosition() == 2) {
				
				RequestMetadata mt = new RequestMetadata();
				mt.setKey(input.getFieldData()[i].getName());
				mt.setValue("Dal " + input.getFieldData()[countA].getDateIn());
				requestMetadatas.add(mt);
				
				RequestMetadata mt1 = new RequestMetadata();
				mt1.setKey(input.getFieldData()[i].getName());
				mt1.setValue("Al " + input.getFieldData()[countB].getDateOut());
				requestMetadatas.add(mt1);
				
				countA++;
				countB++;
			} else if (input.getFieldData()[i].getDateIn() != null && input.getFieldData()[i].getPosition() == 0) {
				
				RequestMetadata mt = new RequestMetadata();
				mt.setKey(input.getFieldData()[i].getName());
				mt.setValue("Dal " + input.getFieldData()[countA].getDateIn());
				requestMetadatas.add(mt);
				
				countA++;
			} else if (input.getFieldData()[i].getDateOut() != null && input.getFieldData()[i].getPosition() == 1) {
				
				RequestMetadata mt1 = new RequestMetadata();
				mt1.setKey(input.getFieldData()[i].getName());
				mt1.setValue("Al " + input.getFieldData()[countB].getDateOut());
				requestMetadatas.add(mt1);
				
				countB++;
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
