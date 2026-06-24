package it.eng.care.domain.flow.tabgen.converter;

import com.github.dozermapper.core.loader.DozerBuilder;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingOption;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.platform.common.dozer.converter.DozerConverter;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

public class TabgenValueDOToDTOWithTableAndFieldConverter implements Converter<TabgenValueDO, TabgenValue> {
	
	@Autowired
	private DozerConverter mapper;

	@Override
	public void convert(TabgenValueDO fromObject, TabgenValue intoObject, ConversionContext ctx) {
		intoObject = mapper.convert(fromObject, TabgenValue.class, getMapId());		
	}
	
	@PostConstruct
	public void post() {
		
		mapper.register(new BeanMappingBuilder() {
		    @Override
		    protected void configure() {
				mapping(TabgenValueDO.class, TabgenValue.class, new TypeMappingOption() {

					@Override
					public void apply(DozerBuilder.MappingBuilder fieldMappingBuilder) {
						fieldMappingBuilder.mapId(getMapId());
					}
		        	
		        })
				.fields("tabgen", "tabgen", FieldsMappingOptions.useMapId(TabgenDOToDTOWithFieldsConverter.getMapId()));
		    }
		});
		
	}
	
	public static String getMapId() {
		return "TABGEN_VALUE_DO_to_DTO_WITH_TABLE_AND_FIELD";
	}

}
