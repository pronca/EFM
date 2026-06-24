package it.eng.care.domain.flow.core.auditLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import it.eng.care.domain.flow.core.dto.FlowView.FlowViewFilter;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableFieldDTO;
import it.eng.care.domain.flow.core.dto.PrivacyManagerDTO.PMFlowView;
import it.eng.care.platform.audit.api.model.privacymanager.EventResourceAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseAM;
import it.eng.care.platform.audit.api.model.privacymanager.EventResponseItemAM;
import it.eng.care.platform.audit.api.model.privacymanager.RequestMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.ResponseMetadata;
import it.eng.care.platform.audit.api.model.privacymanager.SubjectAM;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.ResourceItemScopeEnum;
import it.eng.care.platform.audit.api.model.privacymanager.service.PrivacyManagerLogConverter;
import it.eng.care.platform.common.coding.Coding;
import it.eng.care.platform.common.lang.StringUtils;

public class FlowViewConverter implements PrivacyManagerLogConverter<PMFlowView>{

	@Override
	public List<EventResponseAM> convertResponse(PMFlowView output, Object[] input) {
		
		FlowViewFilter flowViewFilter = (FlowViewFilter) input[0];
		HashMap<String,String> traduzioni = (HashMap<String,String>) input[1];
		HashMap<String, List<Object>> sezioni = (HashMap<String, List<Object>>)output.getFlowViewReturn().get(0);
		Set<String> keyAna = new HashSet<>();
		
		List<Object> anagrafica = sezioni.get("anagrafica");
		List<Object> ricovero = sezioni.get("ricovero");
		List<Object> diagnosi = sezioni.get("diagnosi");
		List<Object> trasferimenti = sezioni.get("trasferimenti");
		List<Object> interventi = sezioni.get("interventi");
		
		traduzioni.put("PROGRESSIVOSDO", "PROGRESSIVOSDO");
		
		List<EventResponseAM> list = new ArrayList<EventResponseAM>();

		for(int i=0; i<anagrafica.size(); i++) {
			Boolean check = true;
			EventResponseAM response = new EventResponseAM();
			SubjectAM subject = new SubjectAM();
		    Object casted = anagrafica.get(i);
		    Object[] recovered = (Object[]) casted;
		    String keySearchAna = StringUtils.EMPTY;
		    Collection<EventResponseItemAM> items = new ArrayList<>();
		    for (Entry<String, String> entry : traduzioni.entrySet()) {
		    	if(!check) {break;}
		        String v = entry.getValue();
		        String k = entry.getKey();
		        int indexVisible = 0;
		        String keySearch = StringUtils.EMPTY;
		        for(FormFlowTableFieldDTO field: flowViewFilter.getFlow().getFlowTableList().get(0).getFlowTableFieldList()) {
		        	if (!field.getActive()) {
		        		indexVisible++;
		        	}
		        	if (field.isPk()) {
		        		keyAna.add(field.getName());
		        		keySearch=keySearch+(String)recovered[field.getPosition()-indexVisible];
		        	}
					if(v.equalsIgnoreCase(field.getName())) {
						if(recovered[field.getPosition()] != null) {
							if(k.equalsIgnoreCase("CODICEFISCALE")) {
								subject.setTaxCode((String)recovered[field.getPosition()-indexVisible]);
								break;
							}
							if(k.equalsIgnoreCase("NOME")) {
								subject.setName((String)recovered[field.getPosition()-indexVisible]);
								break;
							}
							if(k.equalsIgnoreCase("COGNOME")) {
								subject.setSurname((String)recovered[field.getPosition()-indexVisible]);
								break;
							}
							if(k.equalsIgnoreCase("DATANASCITA")) {
								Calendar cal = Calendar.getInstance();
								cal.setTime((Date)recovered[field.getPosition()-indexVisible]);
								subject.setDateOfBirth(cal);
								break;
							}
							if(k.equalsIgnoreCase("PROGRESSIVOSDO")) {
								EventResponseItemAM item = new EventResponseItemAM();
								Coding code = new Coding(ResourceItemScopeEnum.SANITARI);
						   
								item.setKey("Progressivo SDO");
								item.setValue((String)recovered[field.getPosition()-indexVisible]);
								item.setDataSetScope(code);
								items.add(item);
								break;
							}
						}else {
							check = false;
							break;
						}
					}
				}
		        keySearchAna=keySearch;
		    }
		    
		    if(check) {
		    	response.setSubject(subject);
				response.setItems(items);
				List<ResponseMetadata> metas = new ArrayList<>();
				for(int ss=1; ss<flowViewFilter.getFlow().getFlowTableList().size(); ss++) {
			    	if (flowViewFilter.getFlow().getFlowTableList().get(ss).getName().toLowerCase().equals("ricovero") ) {
			    		int indexVisibleRic = 0;
			    		ResponseMetadata metaRic = new ResponseMetadata();
			    		metaRic.setKey("**********RICOVERO**********");
			    		metaRic.setValue("");
			    		metas.add(metaRic);
			    		for(int ric=0; ric<ricovero.size(); ric++) {
							Object castedRic = ricovero.get(ric);
						    Object[] recoveredRic = (Object[]) castedRic;
						    String keySearchRic = StringUtils.EMPTY;
						    for(FormFlowTableFieldDTO fieldRic: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
					    		if (!fieldRic.getActive()) {
					    			indexVisibleRic++;
					        	}
					    		for (String pk : keyAna) {
					    			if (pk.equalsIgnoreCase(fieldRic.getName())) {
					    				keySearchRic=keySearchRic+(String)recoveredRic[fieldRic.getPosition()-indexVisibleRic];
					    			}
					    		}
					    	}
						    if (keySearchAna.equals(keySearchRic)) {
						    	indexVisibleRic=0;
						    	for(FormFlowTableFieldDTO fieldRicValue: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
						    		if (!fieldRicValue.getActive()) {
						    			indexVisibleRic++;
						        	}
						    		if("regimericovero".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Ricovero in regime ordinario o diurno");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("tiporicovero".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Tipologia di ricovero");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("motivoricovero".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Scopo del ricovero (diagnostico, chirurgico, ec...) ");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("dataricovero".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Data di accesso al reparto");
											SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
											meta.setValue(dateFormat.format(recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]));
											metas.add(meta);
										}
						    		}
						    		if("oraricovero".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Ora di accesso al reparto");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("uoammissione".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Codice UO di ammissione");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("uodimissione".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Codice UO di dimissione");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    		if("datadimissione".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Data di dimissione o morte");
											SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
											meta.setValue(dateFormat.format(recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]));
											metas.add(meta);
										}
						    		}
						    		if("oradimissione".equalsIgnoreCase(fieldRicValue.getName())) {
										if(recoveredRic[fieldRicValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Ora di dimissione o morte");
											meta.setValue((String)recoveredRic[fieldRicValue.getPosition()-indexVisibleRic]);
											metas.add(meta);
										}
						    		}
						    	}
						    }
					    }
			    		
			    	} 
			    	if (flowViewFilter.getFlow().getFlowTableList().get(ss).getName().toLowerCase().equals("diagnosi") ) {
			    		int indexVisibleDia = 0;
			    		ResponseMetadata metaDia = new ResponseMetadata();
			    		metaDia.setKey("**********DIAGNOSI**********");
			    		metaDia.setValue("");
			    		metas.add(metaDia);
			    		for(int dia=0; dia<diagnosi.size(); dia++) {
							Object castedDia = diagnosi.get(dia);
						    Object[] recoveredDia = (Object[]) castedDia;
						    String keySearchDia = StringUtils.EMPTY;
						    for(FormFlowTableFieldDTO fieldDia: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
					    		if (!fieldDia.getActive()) {
					    			indexVisibleDia++;
					        	}
					    		for (String pk : keyAna) {
					    			if (pk.equalsIgnoreCase(fieldDia.getName())) {
					    				keySearchDia=keySearchDia+(String)recoveredDia[fieldDia.getPosition()-indexVisibleDia];
					    			}
					    		}
					    	}
						    if (keySearchAna.equals(keySearchDia)) {
						    	indexVisibleDia=0;
						    	for(FormFlowTableFieldDTO fieldDiaValue: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
						    		if (!fieldDiaValue.getActive()) {
						    			indexVisibleDia++;
						        	}
						    		if("progressivodiagnosi".equalsIgnoreCase(fieldDiaValue.getName())) {
										if(recoveredDia[fieldDiaValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Progressivo di identificazione della diagnosi");
											meta.setValue((String)recoveredDia[fieldDiaValue.getPosition()-indexVisibleDia]);
											metas.add(meta);
										}
						    		}
						    		if("codicediagnosi".equalsIgnoreCase(fieldDiaValue.getName())) {
										if(recoveredDia[fieldDiaValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Codice della diagnosi principale o secondaria (ICD-9-CM)");
											meta.setValue((String)recoveredDia[fieldDiaValue.getPosition()-indexVisibleDia]);
											metas.add(meta);
										}
						    		}
						    		if("diagnosiricovero".equalsIgnoreCase(fieldDiaValue.getName())) {
										if(recoveredDia[fieldDiaValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Diagnosi presente al ricovero");
											meta.setValue((String)recoveredDia[fieldDiaValue.getPosition()-indexVisibleDia]);
											metas.add(meta);
										}
						    		}
						    	}
						    }
					    }
			    		
			    	}
			    	if (flowViewFilter.getFlow().getFlowTableList().get(ss).getName().toLowerCase().equals("trasferimenti") ) {
			    		ResponseMetadata metaTra = new ResponseMetadata();
			    		metaTra.setKey("**********TRASFERIMENTI**********");
			    		metaTra.setValue("");
			    		metas.add(metaTra);
			    		int indexVisibleTra = 0;
			    		for(int tra=0; tra<trasferimenti.size(); tra++) {
							Object castedTra = trasferimenti.get(tra);
						    Object[] recoveredTra = (Object[]) castedTra;
						    String keySearchTra = StringUtils.EMPTY;
						    for(FormFlowTableFieldDTO fieldTra: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
					    		if (!fieldTra.getActive()) {
					    			indexVisibleTra++;
					        	}
					    		for (String pk : keyAna) {
					    			if (pk.equalsIgnoreCase(fieldTra.getName())) {
					    				keySearchTra=keySearchTra+(String)recoveredTra[fieldTra.getPosition()-indexVisibleTra];
					    			}
					    		}
					    	}
						    if (keySearchAna.equals(keySearchTra)) {
						    	indexVisibleTra=0;
						    	for(FormFlowTableFieldDTO fieldTraValue: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
						    		if (!fieldTraValue.getActive()) {
						    			indexVisibleTra++;
						        	}
						    		if("progressivotrasferimento".equalsIgnoreCase(fieldTraValue.getName())) {
										if(recoveredTra[fieldTraValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Progressivo di identificazione del trasferimento");
											meta.setValue((String)recoveredTra[fieldTraValue.getPosition()-indexVisibleTra]);
											metas.add(meta);
										}
						    		}
						    		if("tiporeparto".equalsIgnoreCase(fieldTraValue.getName())) {
										if(recoveredTra[fieldTraValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Tipologia del reparto (ammissione, dimissione o trasferimento)");
											meta.setValue((String)recoveredTra[fieldTraValue.getPosition()-indexVisibleTra]);
											metas.add(meta);
										}
						    		}
						    		if("codicereparto".equalsIgnoreCase(fieldTraValue.getName())) {
										if(recoveredTra[fieldTraValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Codifica ministeriale HSP12 o HSP13");
											meta.setValue((String)recoveredTra[fieldTraValue.getPosition()-indexVisibleTra]);
											metas.add(meta);
										}
						    		}
						    		if("datatrasferimento".equalsIgnoreCase(fieldTraValue.getName())) {
										if(recoveredTra[fieldTraValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Data di trasferimento");
											SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
											meta.setValue(dateFormat.format(recoveredTra[fieldTraValue.getPosition()-indexVisibleTra]));
											metas.add(meta);
										}
						    		}
						    		if("oratrasferimento".equalsIgnoreCase(fieldTraValue.getName())) {
										if(recoveredTra[fieldTraValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Ora di trasferimento");
											meta.setValue((String)recoveredTra[fieldTraValue.getPosition()-indexVisibleTra]);
											metas.add(meta);
										}
						    		}
						    	}
						    }
					    }
			    		
			    	}
			    	if (flowViewFilter.getFlow().getFlowTableList().get(ss).getName().toLowerCase().equals("interventi") ) {
			    		int indexVisibleInt = 0;
			    		ResponseMetadata metaInt = new ResponseMetadata();
			    		metaInt.setKey("**********INTERVENTI**********");
			    		metaInt.setValue("");
			    		metas.add(metaInt);
			    		for(int intv=0; intv<interventi.size(); intv++) {
							Object castedInt = interventi.get(intv);
						    Object[] recoveredInt = (Object[]) castedInt;
						    String keySearchInt = StringUtils.EMPTY;
						    for(FormFlowTableFieldDTO fieldInt: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
					    		if (!fieldInt.getActive()) {
					    			indexVisibleInt++;
					        	}
					    		for (String pk : keyAna) {
					    			if (pk.equalsIgnoreCase(fieldInt.getName())) {
					    				keySearchInt=keySearchInt+(String)recoveredInt[fieldInt.getPosition()-indexVisibleInt];
					    			}
					    		}
					    	}
						    if (keySearchAna.equals(keySearchInt)) {
						    	indexVisibleInt=0;
						    	for(FormFlowTableFieldDTO fieldIntValue: flowViewFilter.getFlow().getFlowTableList().get(ss).getFlowTableFieldList()) {
						    		if (!fieldIntValue.getActive()) {
						    			indexVisibleInt++;
						        	}
						    		if("progressivointervento".equalsIgnoreCase(fieldIntValue.getName())) {
										if(recoveredInt[fieldIntValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Progressivo di identificazione dell'intervento");
											meta.setValue((String)recoveredInt[fieldIntValue.getPosition()-indexVisibleInt]);
											metas.add(meta);
										}
						    		}
						    		if("tipointervento".equalsIgnoreCase(fieldIntValue.getName())) {
										if(recoveredInt[fieldIntValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Tipo di intervento");
											meta.setValue((String)recoveredInt[fieldIntValue.getPosition()-indexVisibleInt]);
											metas.add(meta);
										}
						    		}
						    		if("datainiziointervento".equalsIgnoreCase(fieldIntValue.getName())) {
										if(recoveredInt[fieldIntValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("datainiziointervento");
											SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
											meta.setValue(dateFormat.format(recoveredInt[fieldIntValue.getPosition()-indexVisibleInt]));
											metas.add(meta);
										}
						    		}
						    		if("orainiziointervento".equalsIgnoreCase(fieldIntValue.getName())) {
										if(recoveredInt[fieldIntValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Ora di inizio dell'intervento principale o secondario");
											meta.setValue((String)recoveredInt[fieldIntValue.getPosition()-indexVisibleInt]);
											metas.add(meta);
										}
						    		}
						    		if("codiceintervento".equalsIgnoreCase(fieldIntValue.getName())) {
										if(recoveredInt[fieldIntValue.getPosition()] != null) {
											ResponseMetadata meta = new ResponseMetadata();
											meta.setKey("Codice ICD-9-CM dell'intervento");
											meta.setValue((String)recoveredInt[fieldIntValue.getPosition()-indexVisibleInt]);
											metas.add(meta);
										}
						    		}
						    	}
						    }
					    }
			    		
			    	}
			    	response.setResponseMetadatas(metas);
			    }
				
				list.add(response);
		    }
		}
  
		return list;

	}

	@Override
	public EventResourceAM convertRequest(Object[] args) {
		
		FlowViewFilter input = (FlowViewFilter) args[0];
		EventResourceAM request = new EventResourceAM();
		request.setRequest("Visualizzazione Flusso");
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