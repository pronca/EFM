package org.it.eng.care.domain.flow.b2b;

import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder;
import it.eng.care.domain.flow.b2b.service.BatchFlowService;

import java.util.List;

public class BatchFlowServiceMock implements BatchFlowService {

	public void executeBatch(List<FlowQueryBuilder> builders) {

		builders.forEach(b -> {
			System.out.println(b.getQueryString());
		});
	}

}
