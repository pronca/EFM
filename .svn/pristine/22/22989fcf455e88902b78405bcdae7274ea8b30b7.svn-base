package it.eng.care.domain.flow.core.converter.MonitorSdoXl;

import it.eng.care.domain.flow.core.dto.MonitorSdoXlDTO;
import it.eng.care.domain.flow.core.entity.MonitorSdoXlDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class MonitorSdoXlDTOtoMonitorSdoXlDO implements Converter<MonitorSdoXlDTO,MonitorSdoXlDO>{

	@Override
	public void convert(MonitorSdoXlDTO fromObject, MonitorSdoXlDO intoObject, ConversionContext ctx) {
		
		intoObject.setId(fromObject.getId());
		intoObject.setFlussoId(fromObject.getFlussoId());
		intoObject.setFlusso(fromObject.getFlusso());
		intoObject.setPresidio(fromObject.getPresidio());
		intoObject.setNosologico(fromObject.getNosologico());
		intoObject.setNominativo(fromObject.getNominativo());
		intoObject.setReparto(fromObject.getReparto());
		intoObject.setDataRicovero(fromObject.getDataRicovero());
        intoObject.setDataDimissione(fromObject.getDataDimissione());
        intoObject.setOperazione(fromObject.getOperazione());
        intoObject.setTrasmissione(fromObject.getTrasmissione());
        intoObject.setProtocolloSio(fromObject.getProtocolloSio());
        intoObject.setDataInvio(fromObject.getDataInvio());
        intoObject.setDataRicezione(fromObject.getDataRicezione());
        intoObject.setEsito(fromObject.getEsito());
		
	}

}
