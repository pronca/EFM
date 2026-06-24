package it.eng.care.domain.flow.core.converter.Dashboard;

import it.eng.care.domain.flow.core.dto.DashboardConfigDTO;
import it.eng.care.domain.flow.core.entity.DashboardConfigDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class DashboardConfigDOtoDashboardConfigDTO implements Converter<DashboardConfigDO, DashboardConfigDTO> {

	@Override
	public void convert(DashboardConfigDO fromObject, DashboardConfigDTO intoObject, ConversionContext ctx) {
		intoObject.setId(fromObject.getId());
		intoObject.setBadge(fromObject.getBadge());
		intoObject.setColor(fromObject.getColor());
		intoObject.setFooter(fromObject.getFooter());
		intoObject.setIcon(fromObject.getIcon());
		intoObject.setLabel(fromObject.getLabel());
		intoObject.setName(fromObject.getName());
		intoObject.setQuery(fromObject.getQuery());
		intoObject.setTipo(fromObject.getTipo());
		intoObject.setActivity(fromObject.getActivity());
		intoObject.setTooltip(fromObject.getTooltip());
		intoObject.setQueryDetail(fromObject.getQueryDetail());
		intoObject.setCodiceAzienda(fromObject.getCodiceAzienda());
		intoObject.setQueryDetailExp(fromObject.getQueryDetailExp());
		intoObject.setFieldAcceptedQueryDetail(fromObject.getFieldAcceptedQueryDetail());
		intoObject.setQueryDetailHeader(fromObject.getQueryDetailHeader());
	}

}
