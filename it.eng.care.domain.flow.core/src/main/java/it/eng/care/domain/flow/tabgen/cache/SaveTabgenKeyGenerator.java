package it.eng.care.domain.flow.tabgen.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import it.eng.care.domain.flow.tabgen.dto.Tabgen;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class SaveTabgenKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		
		if(params != null && params.length > 0) {
			
			Object param = params[0];
			if(param instanceof Tabgen) {
				Tabgen input = (Tabgen)param;
				return input.getId();
			} else if (param instanceof TabgenValue) {
				TabgenValue input = (TabgenValue)param;
				return input.getTabgen().getId();
			} else if(param instanceof BaseSearchInput) {
				BaseSearchInput data = (BaseSearchInput)param;
				String tabgenId = data.getParam("id");
				return tabgenId;
			} else if (param instanceof String) {
				String tabgenId = (String)param;
				return tabgenId;
			}
			
		}
		
		return "";
	}

}
