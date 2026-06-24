package it.eng.care.domain.flow.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAccessiPMConfig {

	@Value("${privacymanager.accessLog.visualizzazione.pratiche}")
	private String accessLogVisuaPra;
	@Value("${privacymanager.accessLog.download.pratiche.errore}")
	private String accessLogDownPraErr;
	@Value("${privacymanager.accessLog.visualizzazione.flusso}")
	private String accessLogVisuaFlusso;
	@Value("${privacymanager.accessLog.download.dati.flusso}")
	private String accessLogDownFlusso;
	@Value("${privacymanager.accessLog.download.XML.estrazioni.flusso}")
	private String accessLogDownXMLEstrazFlusso;
	@Value("${privacymanager.accessLog.download.LOG.estrazioni.flusso}")
	private String accessLogDownLOGEstrazFlusso;
	@Value("${privacymanager.accessLog.visualizzazione.profilatura}")
	private String accessLogVisuaProfil;
	@Value("${privacymanager.accessLog.ricerca.profilatura}")
	private String accessLogRicProfil;
	@Value("${privacymanager.accessLog.export.profilatura}")
	private String accessLogExpProfil;
	@Value("${privacymanager.accessLog.ricerca.assistito}")
	private String accessLogRicAssistito;
	@Value("${privacymanager.accessLog.visualizzazione.pratica.assistito}")
	private String accessLogVisuaPraAssistito;
	@Value("${privacymanager.accessLog.download.file.caricati}")
	private String accessLogDownFileCaricati;
	@Value("${privacymanager.accessLog.download.file.errati.caricati}")
	private String accessLogDownFileErrCaricati;
	@Value("${privacymanager.accessLog.visualizzazione.monitor.SDO}")
	private String accessLogVisuaMonitorSDO;
	@Value("${privacymanager.accessLog.download.monitor.SDO}")
	private String accessLogDownMonitorSDO;
	@Value("${privacymanager.accessLog.visualizzazione.anagrafica.assistito}")
	private String accessLogVisuaAnagAssistito;
	@Value("${privacymanager.accessLog.ricerca.anagrafica.assistito}")
	private String accessLogRicAnagAssistito;
	@Value("${privacymanager.accessLog.download.anagrafica.assistito}")
	private String accessLogDownAnagAssistito;
	@Value("${privacymanager.accessLog.dashboard.uploadRitorno}")
	private String accessLogDashboardUploadRitorno;
	@Value("${privacymanager.accessLog.estrazFlussi.uploadRitorno}")
	private String accessLogEstrazFlussiUploadRitorno;
	@Value("${privacymanager.accessLog.validazione.avvia}")
	private String accessLogValidazioneAvvio;
	@Value("${privacymanager.accessLog.caricamento.file}")
	private String accessLogCaricamentoFile;
	
	public String getAccessLogVisuaPra() {
		return accessLogVisuaPra;
	}
	
	public String getAccessLogDownPraErr() {
		return accessLogDownPraErr;
	}
	
	public String getAccessLogVisuaFlusso() {
		return accessLogVisuaFlusso;
	}
	
	public String getAccessLogDownFlusso() {
		return accessLogDownFlusso;
	}
	
	public String getAccessLogDownXMLEstrazFlusso() {
		return accessLogDownXMLEstrazFlusso;
	}
	
	public String getAccessLogDownLOGEstrazFlusso() {
		return accessLogDownLOGEstrazFlusso;
	}
	
	public String getAccessLogVisuaProfil() {
		return accessLogVisuaProfil;
	}
	
	public String getAccessLogRicProfil() {
		return accessLogRicProfil;
	}
	
	public String getAccessLogExpProfil() {
		return accessLogExpProfil;
	}
	
	public String getAccessLogRicAssistito() {
		return accessLogRicAssistito;
	}
	
	public String getAccessLogVisuaPraAssistito() {
		return accessLogVisuaPraAssistito;
	}
	
	public String getAccessLogDownFileCaricati() {
		return accessLogDownFileCaricati;
	}
	
	public String getAccessLogDownFileErrCaricati() {
		return accessLogDownFileErrCaricati;
	}
	
	public String getAccessLogVisuaMonitorSDO() {
		return accessLogVisuaMonitorSDO;
	}
	
	public String getAccessLogDownMonitorSDO() {
		return accessLogDownMonitorSDO;
	}
	
	public String getAccessLogVisuaAnagAssistito() {
		return accessLogVisuaAnagAssistito;
	}
	
	public String getAccessLogRicAnagAssistito() {
		return accessLogRicAnagAssistito;
	}

	public String getAccessLogDownAnagAssistito() {
		return accessLogDownAnagAssistito;
	}
	
	public String getAccessLogDashboardUploadRitorno() {
		return accessLogDashboardUploadRitorno;
	}

	public String getAccessLogEstrazFlussiUploadRitorno() {
		return accessLogEstrazFlussiUploadRitorno;
	}

	public String getAccessLogValidazioneAvvio() {
		return accessLogValidazioneAvvio;
	}

	public String getAccessLogCaricamentoFile() {
		return accessLogCaricamentoFile;
	}
	
	
}
