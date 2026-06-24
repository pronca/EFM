package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
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

public class AnagraficaAssistitoDownloadConverter implements PrivacyManagerLogConverter<byte[]>{

	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] input) {
		
		List<AnagraficaAssistitoDO> anagraficaAssistitoDOs = (List<AnagraficaAssistitoDO>) input[0];
		String selectedOption = (String) input[1];
		String filterValue = (String) input[2];
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		if (output != null) {
			EventResponseAM response = new EventResponseAM();
			Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
			AttachmentAM attac = new AttachmentAM();
			attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
			attac.setContent(Base64.getEncoder().encodeToString(output));
			attac.setName("Download File Anagrafica Assistito");
			attachments.add(attac);
			response.setAttachments(attachments);
			
			Collection<EventResponseItemAM> items = new ArrayList<>();
			EventResponseItemAM item = new EventResponseItemAM();
			Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
	   
			item.setKey(selectedOption);
			if (filterValue != null && !filterValue.isEmpty()) {
				item.setValue(filterValue);
			} else {
				item.setValue("Tutti");
			}
			item.setDataSetScope(code);
			items.add(item);
			response.setItems(items);
			list.add(response);
		}
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		List<AnagraficaAssistitoDO> anagraficaAssistitoDOs = (List<AnagraficaAssistitoDO>) args[0];
		String selectedOption = (String) args[1];
		String filterValue = (String) args[2];
		EventResourceAM request = new EventResourceAM();
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		request.setRequest("Download file anagrafica assistito");
		RequestMetadata mt = new RequestMetadata();
		mt.setKey(selectedOption);
		if (filterValue != null && !filterValue.isEmpty()) {
			mt.setValue(filterValue);
		} else {
			mt.setValue("Tutti");
		}
		
		requestMetadatas.add(mt);
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}
}