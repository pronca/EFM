package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import it.eng.care.domain.flow.core.dto.MonitorSdoPaginDTO;
import it.eng.care.domain.flow.core.dto.MonitorSdoXlDTO;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;
import it.eng.care.platform.common.lang.StringUtils;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class MonitorSDOConverter implements PrivacyManagerLogConverter<MonitorSdoPaginDTO>{
		
	@Override
	public List<EventResponseAM> convertResponse(MonitorSdoPaginDTO output, Object[] input) {
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		
		for(MonitorSdoXlDTO monitorSdoXlDTO : output.getData()) {
			EventResponseAM response = new EventResponseAM();
			SubjectAM subject = new SubjectAM();
			String[] nominativo = monitorSdoXlDTO.getNominativo().split(" ");
			String nome = StringUtils.EMPTY;
			String cognome = StringUtils.EMPTY;
			int c=0;
	        for (String splitNome : nominativo) {
	        	if (c==0) {
	        		nome = splitNome;
	        	} else {
	        		cognome = (cognome.isEmpty() ? splitNome : cognome + " " + splitNome);
	        	}
	        	c++;
	        }
	        subject.setName(nome);
			subject.setSurname(!cognome.isEmpty() ? cognome : "XXXXXXXXXX");
			subject.setTaxCode(monitorSdoXlDTO.getNosologico()!=null ? monitorSdoXlDTO.getNosologico() : "0000000000" );
			response.setSubject(subject);
	  
			Collection<EventResponseItemAM> items = new ArrayList<>();
			Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
			
			EventResponseItemAM item = new EventResponseItemAM();
			
			if (monitorSdoXlDTO.getIdEstrazione()!=null) {
				item.setKey("Estrazione");
				item.setValue(monitorSdoXlDTO.getIdEstrazione());
				item.setDataSetScope(code);
				items.add(item);
			}
			if (monitorSdoXlDTO.getFlusso()!=null) {
				EventResponseItemAM item2 = new EventResponseItemAM();
				item2.setKey("Flusso");
				item2.setValue(monitorSdoXlDTO.getFlusso());
				item2.setDataSetScope(code);
				items.add(item2);
			}
			if (monitorSdoXlDTO.getPresidio()!=null) {
				EventResponseItemAM item4 = new EventResponseItemAM();
				item4.setKey("Presidio");
				item4.setValue(monitorSdoXlDTO.getPresidio());
				item4.setDataSetScope(code);
				items.add(item4);
			}
			if (monitorSdoXlDTO.getReparto()!=null) {
				EventResponseItemAM item5 = new EventResponseItemAM();
				item5.setKey("Reparto");
				item5.setValue(monitorSdoXlDTO.getReparto());
				item5.setDataSetScope(code);
				items.add(item5);
			}
			if (monitorSdoXlDTO.getNosologico()!=null) {
				EventResponseItemAM item6 = new EventResponseItemAM();
				item6.setKey("Nosologico");
				item6.setValue(monitorSdoXlDTO.getNosologico());
				item6.setDataSetScope(code);
				items.add(item6);
			}
			if (monitorSdoXlDTO.getProtocolloSio()!=null) {
				EventResponseItemAM item7 = new EventResponseItemAM();
				item7.setKey("Protocollo");
				item7.setValue(monitorSdoXlDTO.getProtocolloSio());
				item7.setDataSetScope(code);
				items.add(item7);
			}
			if (monitorSdoXlDTO.getDataRicovero()!=null) {
				EventResponseItemAM item8 = new EventResponseItemAM();
				item8.setKey("Data Ricovero");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				item8.setValue(dateFormat.format(monitorSdoXlDTO.getDataRicovero()));
				item8.setDataSetScope(code);
				items.add(item8);
			}
			if (monitorSdoXlDTO.getDataDimissione()!=null) {
				EventResponseItemAM item9 = new EventResponseItemAM();
				item9.setKey("Data Dimissione");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				item9.setValue(dateFormat.format(monitorSdoXlDTO.getDataDimissione()));
				item9.setDataSetScope(code);
				items.add(item9);
			}
			if (monitorSdoXlDTO.getDataInvio()!=null) {
				EventResponseItemAM item10 = new EventResponseItemAM();
				item10.setKey("Data Invio");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				item10.setValue(dateFormat.format(monitorSdoXlDTO.getDataInvio()));
				item10.setDataSetScope(code);
				items.add(item10);
			}
			if (monitorSdoXlDTO.getDataRicezione()!=null) {
				EventResponseItemAM item11 = new EventResponseItemAM();
				item11.setKey("Data Ricezione");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				item11.setValue(dateFormat.format(monitorSdoXlDTO.getDataRicezione()));
				item11.setDataSetScope(code);
				items.add(item11);
			}
			if (monitorSdoXlDTO.getTrasmissione()!=null) {
				EventResponseItemAM item12 = new EventResponseItemAM();
				item12.setKey("Trasmissione");
				item12.setValue(monitorSdoXlDTO.getTrasmissione());
				item12.setDataSetScope(code);
				items.add(item12);
			}
			if (monitorSdoXlDTO.getEsito()!=null) {
				EventResponseItemAM item13 = new EventResponseItemAM();
				item13.setKey("Esito");
				item13.setValue(monitorSdoXlDTO.getEsito());
				item13.setDataSetScope(code);
				items.add(item13);
			}
			
			response.setItems(items);
			list.add(response);
		}
  
		return list;
	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		BaseSearchInput input = (BaseSearchInput) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Visualizzazione Monitor flussi SDO");
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		String keyIdEstrazione = "";
		String valueIdEstrazione = "";
		String keyIdFlusso = "";
		String valueFlusso= "";
		
		for (Entry<String, Object> entry : input.getParams().entrySet()) {
			if(entry.getKey().equalsIgnoreCase("IDESTRAZIONE")) {
				keyIdEstrazione=entry.getKey();
				valueIdEstrazione=(String)entry.getValue();
			} else if(entry.getKey().equalsIgnoreCase("FLUSSO")) {
				keyIdFlusso=entry.getKey();
				valueFlusso=(String)entry.getValue();
			} 
		}
		
		if (!"".equals(keyIdEstrazione) && !"".equals(valueIdEstrazione)) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey(keyIdEstrazione);
			mt.setValue(valueIdEstrazione);
			requestMetadatas.add(mt);
		}
		if (!"".equals(keyIdFlusso) && !"".equals(valueFlusso)) {
			RequestMetadata mt = new RequestMetadata();
			mt.setKey("Flusso");
			mt.setValue(valueFlusso);
			requestMetadatas.add(mt);			
		}
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}
}