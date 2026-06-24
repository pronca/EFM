package it.eng.care.domain.flow.tabgen.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("fm/ProfilaturaFlussi")
public class ProfilaturaFlussiControllerImpl extends TabgenControllerImpl {
	
	public ProfilaturaFlussiControllerImpl() {
		super();
		this.profilaturaFlussi = true;
	}

}
