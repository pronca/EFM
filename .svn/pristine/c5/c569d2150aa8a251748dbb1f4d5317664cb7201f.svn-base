package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenField;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class ProfilaturaViewConverter implements PrivacyManagerLogConverter<BasePagingLoadResult<Tabgen>>{

	@Override
	public List<EventResponseAM> convertResponse(BasePagingLoadResult<Tabgen> output, Object[] input) {
		
		List<TabgenField> tabGenFields = output.getList().get(0).getTabgenFields();
		List<TabgenValue> tabGenValues = output.getList().get(0).getTabgenValues();
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();
		Coding code = new Coding(ResourceItemScopeEnum.GENERICI);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		for(TabgenValue tabGenValue : tabGenValues) {
			EventResponseAM response = new EventResponseAM();
			Collection<EventResponseItemAM> items = new ArrayList<>();
	        for(TabgenField tabgenField : tabGenFields) {
	        	if ("FIELD1".equalsIgnoreCase(tabgenField.getTabgenValueColumn())) {
		        	if (tabGenValue.getField1()!=null) {
		        		EventResponseItemAM item1 = new EventResponseItemAM();
		        		item1.setDataSetScope(code);
		        		item1.setKey(tabgenField.getDescription());
		        		item1.setValue(tabGenValue.getField1());
		        		items.add(item1);
		        	}
	        	} else if ("FIELD2".equalsIgnoreCase(tabgenField.getTabgenValueColumn())) {
	        		if (tabGenValue.getField2()!=null) {
	        			EventResponseItemAM item2 = new EventResponseItemAM();
	        			item2.setDataSetScope(code);
	        			item2.setKey(tabgenField.getDescription());
	        			item2.setValue(tabGenValue.getField2());
		        		items.add(item2);
	        		}
	        	} else if ("FIELD3".equalsIgnoreCase(tabgenField.getTabgenValueColumn())) {		        		
	        		if (tabGenValue.getField3()!=null) {
	        			EventResponseItemAM item3 = new EventResponseItemAM();
	        			item3.setDataSetScope(code);
	        			item3.setKey(tabgenField.getDescription());
	        			item3.setValue(tabGenValue.getField3());
		        		items.add(item3);
	        		}
				} else if ("FIELD4".equalsIgnoreCase(tabgenField.getTabgenValueColumn())) {		        		
	        		if (tabGenValue.getField4()!=null) {
	        			EventResponseItemAM item4 = new EventResponseItemAM();
	        			item4.setDataSetScope(code);
	        			item4.setKey(tabgenField.getDescription());
	        			item4.setValue(tabGenValue.getField4());
		        		items.add(item4);
	        		}
				} else if ("FIELD5".equalsIgnoreCase(tabgenField.getTabgenValueColumn())) {
					EventResponseItemAM item5 = new EventResponseItemAM();
        			item5.setDataSetScope(code);
	        		if (tabGenValue.getField5()!=null) {
	        			item5.setKey(tabgenField.getDescription());
	        			item5.setValue(tabGenValue.getField5());
		        		items.add(item5);
	        		} else {
	        			item5.setKey(tabgenField.getDescription());
	        			item5.setValue("S");
		        		items.add(item5);
	        		}
				}
			} 
	        
	        EventResponseItemAM item6 = new EventResponseItemAM();
	        item6.setDataSetScope(code);
	        item6.setKey("Data abilitazione");
	        item6.setValue(dateFormat.format(tabGenValue.getEnabledDate()));
    		items.add(item6);
    		
    		EventResponseItemAM item7 = new EventResponseItemAM();
    		item7.setDataSetScope(code);
    		item7.setKey("Data disabilitazione");
    		item7.setValue(dateFormat.format(tabGenValue.getDisabledDate()));
    		items.add(item7);
	        
	        response.setItems(items);
	        list.add(response);
		}
  
		return list;

	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		BaseSearchInput input = (BaseSearchInput) args[0];
		BasePagingLoadResult<Tabgen> tabgen = (BasePagingLoadResult<Tabgen>) args[1];
		EventResourceAM request = new EventResourceAM();
		
		List<RequestMetadata> requestMetadatas = new ArrayList<>();
		String selectedOption = null;
		String filterValue = null;
		String filterValueDateIn = null;
		String filterValueDateOut = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Entry<String, Object> entry : input.getParams().entrySet()) {
			if (entry.getValue()!=null) {
				if(entry.getKey().equalsIgnoreCase("FIELD")) {
					if (entry.getValue() instanceof ArrayList<?>) {
						ArrayList<String> selectedOptionList = new ArrayList<>();
						selectedOptionList = (ArrayList<String>) entry.getValue();
						if (!selectedOptionList.isEmpty()) {
							selectedOption = selectedOptionList.get(0);
						}
					} else {
						selectedOption=(String)entry.getValue();
					}
				} else if (entry.getKey().equalsIgnoreCase("PARAMETER"))  {
					if (entry.getValue() instanceof ArrayList<?>) {
						ArrayList<String> filterValueList = new ArrayList<>();
						filterValueList = (ArrayList<String>) entry.getValue();
						if (!filterValueList.isEmpty()) {
							filterValue = filterValueList.get(0);
						}
					} else {
						filterValue=(String)entry.getValue();
					} 
				} else if (entry.getKey().equalsIgnoreCase("DATEIN"))  {
					filterValueDateIn = dateFormat.format(entry.getValue());
				} else if (entry.getKey().equalsIgnoreCase("DATEOUT"))  {
					filterValueDateOut = dateFormat.format(entry.getValue());
				}
			}
		}
		
		if (selectedOption!=null || filterValue!=null || filterValueDateIn!=null || filterValueDateOut!=null) {
			request.setRequest("Ricerca profilatura");
			
			if (selectedOption!=null && filterValue!=null) {
				RequestMetadata mt = new RequestMetadata();
				for (TabgenField tabGenField : tabgen.getList().get(0).getTabgenFields()) {
					if (selectedOption.equalsIgnoreCase(tabGenField.getTabgenValueColumn())) {
						mt.setKey(tabGenField.getDescription());
					}
				}
				mt.setValue(filterValue);
				requestMetadatas.add(mt);
			}
			
			if (filterValueDateIn!=null) {
				RequestMetadata mt2 = new RequestMetadata();
				mt2.setKey("Data abilitazione");
				mt2.setValue(filterValueDateIn);
				requestMetadatas.add(mt2);
			}
			if (filterValueDateOut!=null) {
				RequestMetadata mt3 = new RequestMetadata();
				mt3.setKey("Data disabilitazione");
				mt3.setValue(filterValueDateOut);
				requestMetadatas.add(mt3);
			}
		} else {
			request.setRequest("Visualizzazione profilatura");
			RequestMetadata mt = new RequestMetadata();
			mt.setKey("Profilatura");
			mt.setValue((String)input.getParam("tabgenId"));
			requestMetadatas.add(mt);
		}
		request.setRequestMetadatas(requestMetadatas);
		
		return request;
	}

	@Override
	public void processBeforeExecution(Object[] args) {
		
	}
}