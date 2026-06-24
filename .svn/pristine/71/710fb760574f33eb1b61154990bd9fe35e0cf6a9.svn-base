package it.eng.care.domain.flow.flowupload.converter;

import java.util.ArrayList;
import java.util.List;

import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.VersionDO;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestErrorDO;
import it.eng.care.domain.flow.flowupload.model.FlowSectionFileDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class FlowFileUploadRequestDTOtoFlowFileUploadRequestDO implements Converter<FlowFileUploadRequest, FlowFileUploadRequestDO> {
	
//	@Autowired
//	private DozerConverter mapper;
	
    @Override
    public void convert(FlowFileUploadRequest fromObject, FlowFileUploadRequestDO intoObject, ConversionContext ctx) {
    	
//    	intoObject = mapper.convert(fromObject, FlowFileUploadRequestDO.class);
    	
    	try {
    		intoObject.setFlow(ctx.convertTo(fromObject.getFlow(), FlowDO.class));
        } catch (Exception e) {
        	intoObject.setFlow(null);
        }
        try {
        	intoObject.setVersion(ctx.convertTo(fromObject.getVersion(), VersionDO.class));
        } catch (Exception e) {
        	intoObject.setVersion(null);
        }
        intoObject.setStatus(fromObject.getStatus());
        intoObject.setCreationDate(fromObject.getCreationDate());
        intoObject.setValidationDate(fromObject.getValidationDate());
        List<FlowSectionFileDO> files = new ArrayList<FlowSectionFileDO>();
        for(int i=0; i<fromObject.getFiles().size(); i++) {
        	FlowSectionFileDO file = ctx.convertTo(fromObject.getFiles().get(i), FlowSectionFileDO.class);
        	files.add(file);
        }
        intoObject.setFiles(files);
        List<FlowFileUploadRequestErrorDO> errors = new ArrayList<FlowFileUploadRequestErrorDO>();
        for(int i=0; i<fromObject.getErrors().size(); i++) {
        	FlowFileUploadRequestErrorDO error = ctx.convertTo(fromObject.getErrors().get(i), FlowFileUploadRequestErrorDO.class);
        	errors.add(error);
        }
        intoObject.setErrors(errors);
        intoObject.setAziendeProfiloFlussi(fromObject.getAziendeProfiloFlussi());
        intoObject.setAziendeLoadedInFile(fromObject.getAziendeLoadedInFile());
        
    }

}
