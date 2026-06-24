package it.eng.care.domain.flow.flowupload.converter;

import java.util.ArrayList;
import java.util.List;

import it.eng.care.domain.flow.core.dto.FlowDTO;
import it.eng.care.domain.flow.core.dto.VersionDTO;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequest;
import it.eng.care.domain.flow.flowupload.bean.FlowFileUploadRequestError;
import it.eng.care.domain.flow.flowupload.bean.FlowSectionFile;
import it.eng.care.domain.flow.flowupload.model.FlowFileUploadRequestDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

	
public class FlowFileUploadRequestDOtoFlowFileUploadRequestDTO implements Converter<FlowFileUploadRequestDO, FlowFileUploadRequest> {

//	@Autowired
//	private DozerConverter mapper;
	
    @Override
    public void convert(FlowFileUploadRequestDO fromObject, FlowFileUploadRequest intoObject, ConversionContext ctx) {

//    	intoObject = mapper.convert(fromObject, FlowFileUploadRequest.class);
    	
    	intoObject.setFlow(ctx.convertTo(fromObject.getFlow(), FlowDTO.class));
    	intoObject.setVersion(ctx.convertTo(fromObject.getVersion(), VersionDTO.class));
        intoObject.setStatus(fromObject.getStatus());
        intoObject.setCreationDate(fromObject.getCreationDate());
        intoObject.setValidationDate(fromObject.getValidationDate());
        List<FlowSectionFile> files = new ArrayList<FlowSectionFile>();
        for(int i=0; i<fromObject.getFiles().size(); i++) {
        	FlowSectionFile file = ctx.convertTo(fromObject.getFiles().get(i), FlowSectionFile.class);
        	files.add(file);
        }
        intoObject.setFiles(files);
        List<FlowFileUploadRequestError> errors = new ArrayList<FlowFileUploadRequestError>();
        for(int i=0; i<fromObject.getErrors().size(); i++) {
        	FlowFileUploadRequestError error = ctx.convertTo(fromObject.getErrors().get(i), FlowFileUploadRequestError.class);
        	errors.add(error);
        }
        intoObject.setErrors(errors);
        intoObject.setAziendeProfiloFlussi(fromObject.getAziendeProfiloFlussi());
        intoObject.setAziendeLoadedInFile(fromObject.getAziendeLoadedInFile());
                    
    }

}
