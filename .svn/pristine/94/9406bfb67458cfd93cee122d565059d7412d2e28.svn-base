package it.eng.care.domain.flow.core.controller;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;

import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface FormFlowController {

    SaveOperationResult<FormFlowDTO> save(FormFlowDTO inputDTO);

    OperationResult<Object> getSectionBy(String entityKeyName, String entityKeyValue);

    SearchOperationResult<FormFlowDTO> search(BaseSearchInput searchInput);

    void deleteEntityBy(String entityKeyName, String entityKeyValue);

    void deleteField(String id);

    void deleteSection(BaseSearchInput baseSearchInput);
    
    void deleteFkLink(BaseSearchInput searchInput);

    FormFlowDTO searchFlowWithLastVersion(BaseSearchInput searchInput);
    
    OperationResult<String> fileImportFormFlow(String fileName,String fileType,byte[] bytes);
    
    HttpEntity<byte[]> fileExportFormFlow(FormFlowDTO inputDTO);
    
    OperationResult<String> delete(@RequestBody  Map<String, String> body);
    
}
