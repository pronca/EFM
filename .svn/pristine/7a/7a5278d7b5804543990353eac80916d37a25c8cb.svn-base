package it.eng.care.domain.flow.core.converter.AnagraficaAssistito;

import it.eng.care.domain.flow.core.dto.AnagraficaAssistitoDTO;
import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class AnagraficaAssistitoDTOtoAnagraficaAssistitoDO implements Converter<AnagraficaAssistitoDTO, AnagraficaAssistitoDO>{

	@Override
	public void convert(AnagraficaAssistitoDTO fromObject, AnagraficaAssistitoDO intoObject, ConversionContext ctx) {
		
		intoObject.setId(fromObject.getId());
		intoObject.setNome(fromObject.getNome());
		intoObject.setCognome(fromObject.getCognome());
		intoObject.setDatanascita(fromObject.getDatanascita());
		intoObject.setComunenascita(fromObject.getComunenascita());
		intoObject.setSesso(fromObject.getSesso());
		intoObject.setCodiceFiscale(fromObject.getCodiceFiscale());
		intoObject.setCodicePaziente(fromObject.getCodicePaziente());
        intoObject.setComuneResidenza(fromObject.getComuneResidenza());
        intoObject.setNazionalita(fromObject.getNazionalita());
        intoObject.setAbilitazione(fromObject.getAbilitazione());
        intoObject.setAslResidenza(fromObject.getAslResidenza());
	}

}
