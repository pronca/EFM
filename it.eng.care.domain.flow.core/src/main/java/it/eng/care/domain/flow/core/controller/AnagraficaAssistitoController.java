package it.eng.care.domain.flow.core.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import it.eng.care.domain.flow.core.dto.AnagrafcaAssistitoPaginationDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDownloadDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface AnagraficaAssistitoController {
	
	OperationResult<AnagrafcaAssistitoPaginationDTO> search(BaseSearchInput searchInput);
	
	OperationResult<AnagrafcaAssistitoPaginationDTO> loadAnagraficaAssistitoPage(BaseSearchInput searchInput);
	
	SaveOperationResult<AnagraficaAssistitoDTO> save(AnagraficaAssistitoDTO anagraficaAssistitoDTO);
	
	OperationResult<AnagraficaAssistitoDTO> getEntityBy(String entityKeyName, String entityKeyValue);
	
	void deleteEntityBy(String entityKeyName, String entityKeyValue);
	
	OperationResult<String> fileUploadAnagraficaAssistito(String fileName,String fileType,byte[] bytes);

	SearchOperationResult<AnagraficaAssistitoDTO> checkAnagraficaAssistitoData(BaseSearchInput searchInput);
	
	SearchOperationResult<AnagraficaAssistitoDTO> exportAnagraficaAssistitoData(BaseSearchInput searchInput);
	
    HttpEntity<byte[]> downloadFlowViewXlsx(@RequestBody AnagraficaAssistitoDownloadDTO anagraficaAssistitoDownloadDTO) throws IOException;

}
