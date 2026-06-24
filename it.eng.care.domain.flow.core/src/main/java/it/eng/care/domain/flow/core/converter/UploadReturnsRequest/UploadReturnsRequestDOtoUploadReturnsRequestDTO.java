package it.eng.care.domain.flow.core.converter.UploadReturnsRequest;

import it.eng.care.domain.flow.core.dto.UploadReturnsRequestDTO;
import it.eng.care.domain.flow.core.entity.UploadReturnsRequestDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class UploadReturnsRequestDOtoUploadReturnsRequestDTO implements Converter<UploadReturnsRequestDO, UploadReturnsRequestDTO> {

    @Override
    public void convert(UploadReturnsRequestDO fromObject, UploadReturnsRequestDTO intoObject, ConversionContext ctx) {

        intoObject.setId(fromObject.getId());
        intoObject.setExtractionId(fromObject.getExtractionId());
        intoObject.setCreationDate(fromObject.getCreationDate());
        intoObject.setTipoValidazioneReg(fromObject.getTipoValidazioneReg());
    }

}