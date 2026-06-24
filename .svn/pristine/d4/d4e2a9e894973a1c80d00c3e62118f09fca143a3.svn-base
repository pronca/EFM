package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.platform.audit.api.model.privacymanager.AttachmentAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class FileDownloadConverter implements PrivacyManagerLogConverter<byte[]>{

	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] input) {
		
		FlowFileUploadRequest flowFileUploadRequest = (FlowFileUploadRequest) input[0];
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		if (output != null) {
			EventResponseAM response = new EventResponseAM();
			Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
			AttachmentAM attac = new AttachmentAM();
			attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
			attac.setContent(Base64.getEncoder().encodeToString(output));
			attac.setName("Download File Caricamento Flusso " + flowFileUploadRequest.getFlow().getName());
			attachments.add(attac);
			response.setAttachments(attachments);
			
			Collection<EventResponseItemAM> items = new ArrayList<>();
			EventResponseItemAM item = new EventResponseItemAM();
			Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
	   
			item.setKey("Flusso");
			item.setValue(flowFileUploadRequest.getFlow().getName());
			item.setDataSetScope(code);
			items.add(item);
			response.setItems(items);
			list.add(response);
		}
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		FlowFileUploadRequest input = (FlowFileUploadRequest) args[0];
		EventResourceAM request = new EventResourceAM();
		
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		
		request.setRequest("Download file caricamento flusso");
		
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