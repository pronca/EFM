package it.eng.care.domain.flow.tabgen.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("fm/ProfilaturaValue")
public class ProfilaturaValueFlussiControllerImpl extends TabgenValueControllerImpl {
	
	public ProfilaturaValueFlussiControllerImpl() {
		super();
		this.profilaturaFlussi = true;
	}

}
