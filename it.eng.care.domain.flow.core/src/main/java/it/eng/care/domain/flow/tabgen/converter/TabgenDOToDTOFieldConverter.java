package it.eng.care.domain.flow.tabgen.converter;

import com.github.dozermapper.core.loader.DozerBuilder;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingOption;
import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.platform.common.dozer.converter.DozerConverter;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

public class TabgenDOToDTOFieldConverter implements Converter<TabgenDO, Tabgen> {
	
	@Autowired
	private DozerConverter mapper;
	
	@Override
	public void convert(TabgenDO fromObject, Tabgen intoObject, ConversionContext ctx) {
		// da tabgendo a tabgen, senza le relazioni
		intoObject = mapper.convert(fromObject, Tabgen.class, getMapId());
	}
	
	@PostConstruct
	public void post() {
		
		mapper.register(new BeanMappingBuilder() {
		    @Override
		    protected void configure() {
				mapping(TabgenDO.class, Tabgen.class, new TypeMappingOption() {

					@Override
					public void apply(DozerBuilder.MappingBuilder fieldMappingBuilder) {
						fieldMappingBuilder.mapId(getMapId());
					}
		        	
		        })
		        .fields("tabgenFields", "tabgenFields", FieldsMappingOptions.useMapId(TabgenFieldDOToDTOFKConverter.getMapId()))
		        .exclude("tabgenValues");
		    }
		});
	}
	
	public static String getMapId() {
		return "TABGEN_DO_to_DTO_FIELD";
	}

}
