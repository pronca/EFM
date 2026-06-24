package it.eng.care.domain.flow.drools.model.rule;

import java.io.File;

import org.kie.api.runtime.KieSession;

public class KieSessionWrapper {

	private KieSession kieSession;

	private File[] files;

	public KieSessionWrapper(KieSession kieSession, File[] files) {
		super();
		this.kieSession = kieSession;
		this.files = files;
	}

	public KieSession getKieSession() {
		return kieSession;
	}

	public void setKieSession(KieSession kieSession) {
		this.kieSession = kieSession;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

}
