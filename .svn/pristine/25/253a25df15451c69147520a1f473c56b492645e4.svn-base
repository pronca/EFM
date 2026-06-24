package it.eng.care.domain.flow.core.auditLog;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

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

public class MonitorSDODownloadXmlConverter implements PrivacyManagerLogConverter<byte[]>{
	
	@Override
	public List<EventResponseAM> convertResponse(byte[] output, Object[] args) {
		
		//String estrazione = (String) input[0];
		BaseSearchInput input = (BaseSearchInput) args[0];
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		String estrazione = StringUtils.EMPTY;
		String nosologico = StringUtils.EMPTY;
		
		for (Entry<String, Object> entry : input.getParams().entrySet()) {
			if (entry.getValue()!=null) {
				if(entry.getKey().equalsIgnoreCase("IDESTRAZIONE")) {
					if (entry.getValue() instanceof ArrayList<?>) {
						ArrayList<String> inputList = new ArrayList<>();
						inputList = (ArrayList<String>) entry.getValue();
						if (!inputList.isEmpty()) {
							estrazione = inputList.get(0);
						}
					} else {
						estrazione=(String)entry.getValue();
					}
				} else if(entry.getKey().equalsIgnoreCase("NOSOLOGICO")) {
					if (entry.getValue() instanceof ArrayList<?>) {
						ArrayList<String> inputList = new ArrayList<>();
						inputList = (ArrayList<String>) entry.getValue();
						if (!inputList.isEmpty()) {
							nosologico = inputList.get(0);
						}
					} else {
						nosologico=(String)entry.getValue();
					}
				}
			}
		}
		
		EventResponseAM response = new EventResponseAM();
		Collection<AttachmentAM> attachments = new ArrayList<AttachmentAM>();
		AttachmentAM attac = new AttachmentAM();
		attac.setMimeType(FileUtility.MEDIATYPE_ZIP);
		attac.setContent(Base64.getEncoder().encodeToString(output));
		attac.setName("Monitor SDO Xml estrazione: " + estrazione+" - nosologico: "+nosologico);
		attachments.add(attac);
		response.setAttachments(attachments);
		
		Collection<EventResponseItemAM> items = new ArrayList<>();
		EventResponseItemAM item = new EventResponseItemAM();
		Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
   
		item.setKey("Nosologico");
		item.setValue(nosologico);
		item.setDataSetScope(code);
		items.add(item);
		response.setItems(items);
		list.add(response);
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		BaseSearchInput input = (BaseSearchInput) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Download Monitor flussi SDO");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		String keyIdEstrazione = "";
		String valueIdEstrazione = "";
		String keyNosologico = "";
		String valueNosologico= "";
		
		for (Entry<String, Object> entry : input.getParams().entrySet()) {
			if(entry.getKey().equalsIgnoreCase("IDESTRAZIONE")) {
				keyIdEstrazione=entry.getKey();
				valueIdEstrazione=(String)entry.getValue();
			} else if(entry.getKey().equalsIgnoreCase("FLUSSOID")) {
				keyNosologico=entry.getKey();
				valueNosologico=(String)entry.getValue();
			} 
			if(entry.getKey().equalsIgnoreCase("NOSOLOGICO")) {
				keyNosologico=entry.getKey();
				valueNosologico=(String)entry.getValue();
			} 
		}
		
		if (!"".equals(keyIdEstrazione) && !"".equals(valueIdEstrazione)) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey(keyIdEstrazione);
			mt.setValue(valueIdEstrazione);
			requestMetadatas.add(mt);
		}
		if (!"".equals(keyNosologico) && !"".equals(keyNosologico)) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey(keyNosologico);
			mt.setValue(valueNosologico);
			requestMetadatas.add(mt);			
		}
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}
}