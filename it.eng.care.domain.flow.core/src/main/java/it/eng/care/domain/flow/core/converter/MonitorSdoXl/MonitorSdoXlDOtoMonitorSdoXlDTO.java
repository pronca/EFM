package it.eng.care.domain.flow.core.converter.MonitorSdoXl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.dto.MonitorSdoXlDTO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.domain.flow.core.service.MonitorSdoXlService;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class MonitorSdoXlDOtoMonitorSdoXlDTO implements Converter<MonitorSdoXlDO,MonitorSdoXlDTO>{

	@Autowired
	private MonitorSdoXlService service;
	
	@Override
	public void convert(MonitorSdoXlDO fromObject, MonitorSdoXlDTO intoObject, ConversionContext ctx) {
		intoObject.setId(fromObject.getId());
		intoObject.setFlussoId(fromObject.getFlussoId());
		intoObject.setFlusso(fromObject.getFlusso());
		intoObject.setPresidio(fromObject.getPresidio());
		intoObject.setNosologico(fromObject.getNosologico());
		intoObject.setNominativo(fromObject.getNominativo());
		intoObject.setReparto(fromObject.getReparto());
		intoObject.setDataRicovero(fromObject.getDataRicovero());
		intoObject.setDataRicoveroString(toString(fromObject.getDataRicovero()));
        intoObject.setDataDimissione(fromObject.getDataDimissione());
        intoObject.setDataDimissioneString(toString(fromObject.getDataDimissione()));
        intoObject.setOperazione(fromObject.getOperazione());
        intoObject.setTrasmissione(fromObject.getTrasmissione());
        intoObject.setProtocolloSio(fromObject.getProtocolloSio());
        intoObject.setDataInvio(fromObject.getDataInvio());
        intoObject.setDataInvioString(toString(fromObject.getDataInvio()));
        intoObject.setDataRicezione(fromObject.getDataRicezione());
        intoObject.setDataRicezioneString(toString(fromObject.getDataRicezione()));
        intoObject.setEsito(fromObject.getEsito());
        intoObject.setIdEstrazione(fromObject.getIdEstrazione());
        Integer status = service.xmlStatus(fromObject.getFlussoId(), fromObject.getPresidio(), fromObject.getNosologico());
        System.out.println("Status:: "+status);
        intoObject.setStatus(status);
		
	}
	
	
	private String toString(Date date) {
		String string = "";
		
		if(date != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
			string = df.format(date);
		}
		
		return string;
	}

}
