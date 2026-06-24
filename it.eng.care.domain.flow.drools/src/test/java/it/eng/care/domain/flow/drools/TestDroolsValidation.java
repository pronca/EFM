package it.eng.care.domain.flow.drools;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.service.api.FlowValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDroolsValidation {
	
	@Autowired
	private FlowValidator validator;
	
	@Test
	public void testBuildErrorsToSend() {
		validator.buidErrorsToSend("c3baf31f-84d9-4219-99e9-e955000a2d7b", "SDOIOR", "versioneditest");
	}
	

}
