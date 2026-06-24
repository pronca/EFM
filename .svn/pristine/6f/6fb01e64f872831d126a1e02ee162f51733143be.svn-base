package org.it.eng.care.domain.flow.b2b;

import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.b2b.service.impl.ValidationFlowServiceImpl;
import it.eng.care.domain.flow.core.utility.LogUtil;

@Service
public class ValidationFlowServiceMock extends ValidationFlowServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationFlowServiceMock.class);
	
	@Override
	public String loadSchema(String flowId, String version) {

		StringBuilder builder = new StringBuilder();

		try (InputStream schemaStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("json/schema/" + flowId + ".json");
				Scanner schemaScanner = new Scanner(schemaStream)) {

			while (schemaScanner.hasNextLine()) {
				builder.append(schemaScanner.nextLine());
			}

		} catch (Exception e) {
			LogUtil.logException(LOGGER, "", e);
		}

		return builder.toString();
	}

}
