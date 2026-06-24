package it.eng.care.domain.flow.core.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.anagraficaassistito.utility.AnagraficaAssistitoUtility;
import it.eng.care.domain.flow.core.auditLog.AnagraficaAssistitoDownloadConverter;
import it.eng.care.domain.flow.core.auditLog.AnagraficaAssistitoSearchConverter;
import it.eng.care.domain.flow.core.dao.AnagraficaAssistitoDAO;
import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.service.AnagraficaAssistitoService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dto.BasePagingLoadResult;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenField;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.service.TabgenDelegate;
import it.eng.care.platform.audit.api.model.privacymanager.annotation.PrivacyManagerLog;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventActionEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.AuditEventCategoryEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityEnum;
import it.eng.care.platform.audit.api.model.privacymanager.enumeration.EntityTypeEnum;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import jxl.Sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class AnagraficaAssistitoSeviceImpl implements AnagraficaAssistitoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagraficaAssistitoSeviceImpl.class);
	
	@Autowired
	private AnagraficaAssistitoDAO anagraficaDAO;
	
	@Autowired
	private TabgenDelegate tabgenDelegate;
	
	@Override
	public AnagraficaAssistitoDO createEntity(AnagraficaAssistitoDO dto) {
		return anagraficaDAO.save(dto);
	}

	@Override
	public AnagraficaAssistitoDO updateEntity(AnagraficaAssistitoDO dto) {
		return anagraficaDAO.save(dto);
	}

	@Override
	public AnagraficaAssistitoDO getEntityById(String id) {
		return (id == null || id.isBlank())
		        ? null
		        : anagraficaDAO.findById(id).orElse(null);

	}

	@Override
	public void deleteEntity(String id) {
		anagraficaDAO.deleteById(id);
	}

	@Override
	public Page<AnagraficaAssistitoDO> retrieveAllFiltered(BaseSearchInput searchInput) {
		int pageSize = searchInput.getValue("pageSize");
		int pageNo = searchInput.getValue("pageNo");
		List<AnagraficaAssistitoDO> loListAll = new ArrayList<AnagraficaAssistitoDO>();
		
		Object filterValue = searchInput.getParam("filterValue");
		if (filterValue instanceof List) {
			for(String filterValueSingle : (List<String>) filterValue) {
				searchInput.setParam("filterValue", filterValueSingle);
				List<AnagraficaAssistitoDO> loList = anagraficaDAO.cercaInProfile(searchInput);
				loListAll.addAll(loList);
			}
		} else {
			List<AnagraficaAssistitoDO> loList = anagraficaDAO.cercaInProfile(searchInput);
			loListAll.addAll(loList);
		}
		
		Pageable paging =  PageRequest.of(pageNo,pageSize);
		
		final int start = (int)paging.getOffset();
		final int end = Math.min((start + paging.getPageSize()), loListAll.size());
		
		Page<AnagraficaAssistitoDO> pageTmp = new PageImpl<AnagraficaAssistitoDO>(loListAll.subList(start, end), paging, loListAll.size());
    	System.out.println("CercaSize:: "+loListAll.size());  
    	
        return pageTmp;
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.SEARCH, category = AuditEventCategoryEnum.ACCESS_LOG, description="Ricerca Anagrafica Assistito", converter= AnagraficaAssistitoSearchConverter.class, entity= EntityEnum.PAZIENTE, entityType= EntityTypeEnum.SCHEDA_MEDICA)
	public AnagrafcaAssistitoPaginationDTO sendAuditRicAnaAssToPM(BaseSearchInput searchInput, AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO) {
		return anagrafcaAssistitoPaginationDTO;
	}

	@Override
	public Page<AnagraficaAssistitoDO> findAll(BaseSearchInput searchInput) {
		int pageSize = searchInput.getValue("pageSize");
		int pageNo = searchInput.getValue("pageNo");
		String sortDir = searchInput.getValue("sortDir");
		String sortField = searchInput.getValue("sortField");
		System.out.println("SortDirection:: "+sortDir);
		Sort.Order order;
		Sort sort;
		if(sortDir.equals("asc")) {
			order = new Sort.Order(Sort.Direction.ASC, sortField).ignoreCase();
			sort  =  Sort.by(order);
		}else if(sortDir.equals("desc")){
			order = new Sort.Order(Sort.Direction.DESC, sortField).ignoreCase();
			sort  =  Sort.by(order);
		}else {
			order = new Sort.Order(Sort.Direction.ASC, "nome").ignoreCase();
			sort  = Sort.by(order);
		}
		
		Pageable paging =  PageRequest.of(pageNo,pageSize,sort);
		Page<AnagraficaAssistitoDO> pagedResult = anagraficaDAO.findAll(paging);
		
		return pagedResult;
	}
	
	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Visualizzazione Anagrafica Assistito", converter= AnagraficaAssistitoSearchConverter.class, entity= EntityEnum.PAZIENTE, entityType= EntityTypeEnum.SCHEDA_MEDICA)
	public AnagrafcaAssistitoPaginationDTO sendAuditVisuaAnaAssToPM(BaseSearchInput searchInput, AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO) {
		return anagrafcaAssistitoPaginationDTO;
	}

	@Override
	public String handleExcelUpload(File file) {
		try {
			BaseSearchInput searchInput = new BaseSearchInput();
			List<AnagraficaAssistitoDO> assistitoDOList  = loadExcelSheetInAnaAssitito(file,"");
			//System.out.println("SheetData:: "+assistitoDOList.size());
			List<AnagraficaAssistitoDO> data = new ArrayList<AnagraficaAssistitoDO>();
			List<String> campi = new ArrayList<>();
			for(AnagraficaAssistitoDO anagraficaAssistitoDOTmp1 : assistitoDOList) {
				
				searchInput.setParam("nome", anagraficaAssistitoDOTmp1.getNome());
				searchInput.setParam("cognome", anagraficaAssistitoDOTmp1.getCognome());
				searchInput.setParam("datanascita", anagraficaAssistitoDOTmp1.getDatanascita());
				if (anagraficaAssistitoDOTmp1.getComunenascita() != null) {
					campi = loadTabgen(null, anagraficaAssistitoDOTmp1.getComunenascita(), "COMUNI", new String[] {"COM_COD_ISTAT"}, new String[] {"COM_DESCRIZIONE"});
					searchInput.setParam("comunenascita", campi.isEmpty() ? null : campi.get(0));
					anagraficaAssistitoDOTmp1.setComunenascita(campi.isEmpty() ? null : campi.get(0));
				}
				searchInput.setParam("sesso", anagraficaAssistitoDOTmp1.getSesso());
				searchInput.setParam("codiceFiscale", anagraficaAssistitoDOTmp1.getCodiceFiscale());
				searchInput.setParam("codicePaziente", anagraficaAssistitoDOTmp1.getCodicePaziente());
				if (anagraficaAssistitoDOTmp1.getComuneResidenza() != null) {
					campi = loadTabgen(null, anagraficaAssistitoDOTmp1.getComuneResidenza(), "COMUNI", new String[] {"COM_COD_ISTAT"}, new String[] {"COM_DESCRIZIONE"});
//					searchInput.setParam("comuneResidenza", campi.isEmpty() ? null : campi.get(0));
					anagraficaAssistitoDOTmp1.setComuneResidenza(campi.isEmpty() ? null : campi.get(0));
				}
				if (anagraficaAssistitoDOTmp1.getNazionalita() != null) {
					campi = loadTabgen(null, anagraficaAssistitoDOTmp1.getNazionalita(), "NAZIONALITA", new String[] {"CODICE"}, new String[] {"DESCRIZIONE"});
//					searchInput.setParam("nazionalita", campi.isEmpty() ? null : campi.get(0));
					anagraficaAssistitoDOTmp1.setNazionalita(campi.isEmpty() ? null : campi.get(0));
				}
				if (anagraficaAssistitoDOTmp1.getAslResidenza() != null) {
					campi = loadTabgen(null, anagraficaAssistitoDOTmp1.getAslResidenza(), "AZIENDE_SANITARIE", new String[] {"COD_AZIENDA","COD_REGIONE"}, new String[] {"DESCRIZIONE_BREVE"});
//					searchInput.setParam("aslResidenza", campi.isEmpty() ? null : campi.get(0));
					anagraficaAssistitoDOTmp1.setAslResidenza(campi.isEmpty() ? null : campi.get(0));
				}
//				searchInput.setParam("abilitazione", anagraficaAssistitoDOTmp1.getAbilitazione());
				
				 data = checkAnagraficaAssistitoData(searchInput);
				 
				if(data.size()>0) {
					String anaDoId = data.get(0).getId();
					anagraficaAssistitoDOTmp1.setId(anaDoId);
					anagraficaDAO.save(anagraficaAssistitoDOTmp1);
					data.clear();
					
				}else {
					anagraficaDAO.save(anagraficaAssistitoDOTmp1);
				}				
			}	
			//saveAllEntities(assistitoDOList);
			return "YES";		
		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
			return "NO";
		}
		
	}
	/*
	 * Extract data from sheet and put it into list
	 * @return getAnagraficaAssistitoFromSheet(AnagraficaAssistitoDO)
	 */
	private List<AnagraficaAssistitoDO> loadExcelSheetInAnaAssitito(File file, String string) {
		AnagraficaAssistitoDO assistitoDO  = null;
		//String sheetName = null;
		List<AnagraficaAssistitoDO> getAnagraficaAssistitoFromSheet = null;
		
		try {
			Sheet sheet = AnagraficaAssistitoUtility.checkSheetName(file);
			if (sheet != null) {
				//sheetName = AnagraficaAssistitoUtility.getCleanSheetName(sheet.getName());
				getAnagraficaAssistitoFromSheet = AnagraficaAssistitoUtility.getAnagraficaAssistitoFromSheet(assistitoDO, sheet);			
			}			
		}catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
//			e.printStackTrace();
		}
		return getAnagraficaAssistitoFromSheet;
	}

	@Override
	public void saveAllEntities(List<AnagraficaAssistitoDO> allAnagaraficaAss) {
		System.out.println("allAnaDataSize:: "+allAnagaraficaAss.size());
		 anagraficaDAO.saveAll(allAnagaraficaAss);
	}

	@Override
	public List<AnagraficaAssistitoDO> checkAnagraficaAssistitoData(BaseSearchInput searchInput) {
		List<AnagraficaAssistitoDO>loList = anagraficaDAO.searchAnagrafica(searchInput);
		System.out.println("CHECKLIST:::  "+loList.size());
		return loList;
	}

	@Override
	public List<AnagraficaAssistitoDO> exportAllData(BaseSearchInput searchInput) {
		return anagraficaDAO.findAll();
	}

	@Override
	public byte[] downloadAnagraficaAssistitoXlsx(List<AnagraficaAssistitoDO> dtos) {
		
		System.out.println("Dtos:: "+dtos.size());
		List<AnagraficaAssistitoDO> data;
		if(dtos == null || dtos.size() == 0) {
			data = anagraficaDAO.findAll();
		}else {
			data = dtos;
		}
		
		HSSFWorkbook workBook = AnagraficaAssistitoUtility.workBook(data);
	      		         
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	byte[] bytes = {};
	try {
       workBook.write(bos);     
       bytes= bos.toByteArray();
    } catch (IOException e) {
    	LogUtil.logException(LOGGER, "", e);
//       e.printStackTrace();
    }finally {
       try {
    	   workBook.close();
           bos.close();
       } catch (IOException e) {
    	   LogUtil.logException(LOGGER, "", e);
//           e.printStackTrace();
       }
	}	             
       return bytes;
	}

	@Override
	@PrivacyManagerLog(action = AuditEventActionEnum.READ, category = AuditEventCategoryEnum.ACCESS_LOG, description="Download file anagrafica assistito", converter=AnagraficaAssistitoDownloadConverter.class, entity= EntityEnum.FLUSSI, entityType= EntityTypeEnum.SCHEDA_MEDICA)
	public byte[] sendAuditDownAnaAssFileUplToPM(List<AnagraficaAssistitoDO> anagraficaAssistitoDOs, String selectedOption, String filterValue, byte[] byt) {
		return byt;
	}
	
	@Override
	public List<String> loadTabgen(String valueId, String valueDescr, String tabgenId, String[] codici, String[] descrizioni) {
		List<String> codiciL = new ArrayList<>(Arrays.asList(codici));
		List<String> descrizioniL = new ArrayList<>(Arrays.asList(descrizioni));
		
		List<String> list = new ArrayList<String>();
		
		TabgenValueFilter tabGenValueFilter = new TabgenValueFilter();
		
		if(!StringUtils.isEmpty(valueId)) {
			tabGenValueFilter.setTabgenId(tabgenId);
			tabGenValueFilter.setId(valueId);
			BasePagingLoadResult<Tabgen> result = tabgenDelegate.searchValue(tabGenValueFilter);
			if(result != null && result.getList() != null && !result.getList().isEmpty()) {
				Tabgen tabgen = result.getList().get(0);
				List<TabgenValue> values = tabgen.getTabgenValues();
				List<TabgenField> fields = tabgen.getTabgenFields();
				if(values != null && !values.isEmpty()) {
					String codice="", descrizione="";
					TabgenValue value = values.get(0);
					List<TabgenField> codiciF = fields.stream().filter(f -> codiciL.contains(f.getDescription())).collect(Collectors.toList());
					List<TabgenField> descrizioniF = fields.stream().filter(f -> descrizioniL.contains(f.getDescription())).collect(Collectors.toList());
					List<Method> methods = new ArrayList<Method>(Arrays.asList(TabgenValue.class.getMethods()));
					
					for(TabgenField codiceField : codiciF) {
						String mName = "get" + codiceField.getTabgenValueColumn().toLowerCase();
						Method m = methods.stream().filter(method -> method.getName().toLowerCase().equals(mName)).findFirst().get();
						try {
							String v = (String) m.invoke(value);
							codice += v;
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						}
					}
					
					list.add(codice);
					
					for(TabgenField descrizioneField : descrizioniF) {
						String mName = "get" + descrizioneField.getTabgenValueColumn().toLowerCase();
						Method m = methods.stream().filter(method -> method.getName().toLowerCase().equals(mName)).findFirst().get();
						try {
							String v = (String) m.invoke(value);
							descrizione += v;
						} catch (Exception e) {
							LogUtil.logException(LOGGER, "", e);
//							e.printStackTrace();
						}
					}	
					
					list.add(descrizione);
					
				}
			}
		} else if (!StringUtils.isEmpty(valueDescr)) {
			tabGenValueFilter.setTabgenId(tabgenId);
			if (tabgenId.equals("COMUNI")) {
				tabGenValueFilter.setField5(valueDescr);
			} else if (tabgenId.equals("NAZIONALITA")) {
				tabGenValueFilter.setField2(valueDescr);
			} else if (tabgenId.equals("AZIENDE_SANITARIE")) {
				tabGenValueFilter.setField4(valueDescr);
			}
			BasePagingLoadResult<Tabgen> result = tabgenDelegate.searchValue(tabGenValueFilter);
			if(result != null && result.getList() != null && !result.getList().isEmpty()) {
				Tabgen tabgen = result.getList().get(0);
				List<TabgenValue> values = tabgen.getTabgenValues();
				if(values != null && !values.isEmpty()) {
					String codice="";
					TabgenValue value = values.get(0);
					codice = value.getId();
					list.add(codice);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public void loadTabgenInAnagrafica(AnagrafcaAssistitoPaginationDTO anagrafcaAssistitoPaginationDTO) {
		if(anagrafcaAssistitoPaginationDTO != null && 
				anagrafcaAssistitoPaginationDTO.getAnagraficaData() != null && 
				!anagrafcaAssistitoPaginationDTO.getAnagraficaData().isEmpty()) {
			
			for(AnagraficaAssistitoDTO a : anagrafcaAssistitoPaginationDTO.getAnagraficaData()) {
				List<String> campi = loadTabgen(a.getComunenascita(), null, "COMUNI", new String[] {"COM_COD_ISTAT"}, new String[] {"COM_DESCRIZIONE"});
				a.setComunenascitaCodeField(campi.isEmpty() ? null : campi.get(0));
				a.setComunenascitaDescriptionField(campi.isEmpty() ? null : campi.get(1));
				
				campi = loadTabgen(a.getComuneResidenza(), null , "COMUNI", new String[] {"COM_COD_ISTAT"}, new String[] {"COM_DESCRIZIONE"});
				a.setComuneResidenzaCodeField(campi.isEmpty() ? null : campi.get(0));
				a.setComuneResidenzaDescriptionField(campi.isEmpty() ? null : campi.get(1));
				
				campi = loadTabgen(a.getNazionalita(), null , "NAZIONALITA", new String[] {"CODICE"}, new String[] {"DESCRIZIONE"});
				a.setNazionalitaCodeField(campi.isEmpty() ? null : campi.get(0));
				a.setNazionalitaDescriptionField(campi.isEmpty() ? null : campi.get(1));
				
				campi = loadTabgen(a.getAslResidenza(), null , "AZIENDE_SANITARIE", new String[] {"COD_AZIENDA","COD_REGIONE"}, new String[] {"DESCRIZIONE_BREVE"});
				a.setAslResidenzaCodeField(campi.isEmpty() ? null : campi.get(0));
				a.setAslResidenzaDescriptionField(campi.isEmpty() ? null : campi.get(1));
			}
		}
	}	

}
