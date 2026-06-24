package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilterError;
import it.eng.care.domain.flow.core.utility.FileUtility;
import it.eng.care.platform.audit.api.model.privacymanager.AttachmentAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;

public class PraticaDownloadConverter implements PrivacyManagerLogConverter<byte[]>{

	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] input) {
		
		FlowViewFilterError flowViewFilter = (FlowViewFilterError) input[0];
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		EventResponseAM response = new EventResponseAM();
		Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
		AttachmentAM attac = new AttachmentAM();
		attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
		attac.setContent(Base64.getEncoder().encodeToString(output));
		attac.setName("Xlsx pratiche del " + flowViewFilter.getMonth() + "/" + flowViewFilter.getYear());
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
		
		FlowViewFilterError input = (FlowViewFilterError) args[0];
		EventResourceAM request = new EventResourceAM();
		
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		
		request.setRequest("Download xlsx pratiche");
		
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
