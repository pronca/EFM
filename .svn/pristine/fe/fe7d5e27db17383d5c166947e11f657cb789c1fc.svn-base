package it.eng.care.domain.flow.webservice.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.Files;

import it.eng.care.domain.flow.core.dao.FlowExportReqFileTalendDAO;
import it.eng.care.domain.flow.core.dao.FlowExportRequestDAO;
import it.eng.care.domain.flow.core.entity.FlowExportReqFileTalendDO;
import it.eng.care.domain.flow.core.entity.FlowExportRequestDO;
import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenDAOImpl;
import it.eng.care.domain.flow.webservice.dao.CodiciRegioneDAO;
import it.eng.care.domain.flow.webservice.dao.SdoDrgPosDAO;
import it.eng.care.domain.flow.webservice.model.CodiciRegioneDO;
import it.eng.care.domain.flow.webservice.model.SdoDrgPosDO;
import it.eng.care.domain.flow.webservice.model.SdoPositionalDTO;
import it.eng.care.domain.flow.webservice.service.WebService;
import it.eng.care.domain.flow.webservice.service.WebServiceSender;
import it.eng.care.domain.flow.webservice.sis.ws.flussi.FlussiFile;
import jakarta.persistence.EntityManager;

/**
 * 
 * @author mpirozzi
 *
 */
@Service
@Transactional
public class WebServiceImpl implements WebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceImpl.class);

	@Autowired
	WebServiceSender webService;

	@Autowired
	FlowExportReqFileTalendDAO flowExportReqFileTalendDAO;
	
	@Autowired
	FlowExportRequestDAO flowExportReqDAO;

	@Autowired
	CodiciRegioneDAO codiciRegioneDAO;

	@Autowired
	SdoDrgPosDAO sdoDrgPosDAO;
	
	@Autowired
	private TabgenDAOImpl tabgenDAO;

	@Autowired
	EntityManager entityManager;
	/**
	 * simulazione flusso
	 */
	@Override
	public Object[] loadAndSimulateFlowOnRegion(String extractionId, String flowName, Boolean drg) {

		/*System.setProperty("http.proxySet",  "true");
		System.setProperty("http.proxyHost", "proxy.eng.it");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyUser", "*");			
		System.setProperty("http.proxyPassword", "*");

		System.setProperty("https.proxySet",  "true");
		System.setProperty("https.proxyHost", "proxy.eng.it");
		System.setProperty("https.proxyPort", "3128");
		System.setProperty("https.proxyUser", "*");
		System.setProperty("https.proxyPassword", "*");*/
		
		// effettuare login
		
		Object[] response = new Object[2];

		String[] respToken = webService.login(extractionId, drg);
		String token = respToken[0];
		if (token == null) {
			LOGGER.info("LOGIN FALLITA");
			response[1] = (String)respToken[1];
			return response;
		} else {

			List<FlussiFile> files = new ArrayList<>();

			FlowExportRequestDO extraction = new FlowExportRequestDO();

			extraction.setId(extractionId);
			// recupero file dell'estrazione di riferimento

			List<FlowExportReqFileTalendDO> extractions = flowExportReqFileTalendDAO
					.findAllByflowExportRequest(extraction);
			
			// recupero codicetipoflusso dalla tabella creata in tabgen

			CodiciRegioneDO codice = codiciRegioneDAO.findByChiave(flowName);
			for(FlowExportReqFileTalendDO extr: extractions) {
				FlussiFile file = new FlussiFile();
				file.setCodTipoFile(extr.getCodReg());
				file.setFile(extr.getXml());
				file.setNomeFile(extr.getId() + extr.getFileExt());
				file.setNumRecord(1);
				files.add(file);
//				LOGGER.info("ID FILE: " + extr.getId());
			}
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int numInvio = Calendar.getInstance().get(Calendar.MONTH + 1);
			
			FlussiFile[] filesArray = files.toArray(new FlussiFile[files.size()]);

			// invio flusso a regione

			Object[] respPrgInvio = webService.sendFlow(codice.getValore(), year, numInvio, token, filesArray, extractionId, drg);
			BigDecimal prgInvio = (BigDecimal)respPrgInvio[0];
			if (prgInvio == null) {
				response[1] = (String)respPrgInvio[1];
				return response;
			} else {

				try {
					Object[] respRitorni = webService.simulate(token, prgInvio, extractionId, drg);
					FlussiFile[] ritorni = (FlussiFile[]) respRitorni[0];
					if(respRitorni[1] != null) {
						response[1] = respRitorni[1];
						return response;
					}
					response[0] = (FlussiFile[])ritorni;
					// se è sdoior utilizza questo metodo
					if (codice.getValore().equals("SDO2") && drg == true) {
						// calcolo drg
						List<HashMap<String, SdoPositionalDTO>> fields = calcoloDrg(ritorni);
						// genero query
						List<String> allQuerys = generateUpdate(codice.getChiave(), fields);
						// eseguo query con cursore
						
						
						//calcolo concluso a buon fine new date, calcolo numero record 
						//possibile inserimento dati in tabella per visualizzazione ?
						
						try {
							executeQuerys(allQuerys);
							} catch (SQLException e) {
								LogUtil.logException(LOGGER, "", e);
//								e.printStackTrace();
						}
						return response;

					}
					return response;

				} catch (InterruptedException e) {
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}

			}
			return null;
		}
	}

	/**
	 * 
	 * @param ritorni carcica importo drg posizionale e aggiornamento dei campi su
	 *                flusso verticale
	 * 
	 */
	private List<HashMap<String, SdoPositionalDTO>> calcoloDrg(FlussiFile[] ritorni) {
		for (int i = 0; i < ritorni.length; i++) {
			if (ritorni[i].getCodTipoFile().equals("SDO2_IMPDRG")) {
				File file;
				try {
					file = File.createTempFile(ritorni[i].getNomeFile(), ".txt");
					Files.write(ritorni[i].getFile(), file);
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = br.readLine();
					List<HashMap<String, SdoPositionalDTO>> fields = new ArrayList<HashMap<String, SdoPositionalDTO>>();
					while (line != null) {
						HashMap<String, SdoPositionalDTO> field = new HashMap<String, SdoPositionalDTO>();
						List<SdoDrgPosDO> pos = sdoDrgPosDAO.findAll();
						// for sugli elementi della tabella di configurazione
						for (SdoDrgPosDO position : pos) {
							Integer fieldLength = Integer.valueOf(position.getLunghezza());
							Integer fieldPosition = Integer.valueOf(position.getPosizione()) - 1;
							String fieldValue = line.substring(fieldPosition, fieldLength + fieldPosition).trim();
							// utilizzo quest'oggetto per tracciare i campi chiave e semplificarmi la query
							SdoPositionalDTO fieldpos = new SdoPositionalDTO();
							fieldpos.setPk(position.getCampoPk());
							fieldpos.setValue(fieldValue);

							field.put(position.getNomeCampo(), fieldpos);
						}
						line = br.readLine();
						fields.add(field);
					}

					br.close();

					return fields;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					LogUtil.logException(LOGGER, "", e);
//					e.printStackTrace();
				}

			}

		}
		return null;
	}
	
	private String decodeValue(String value, String name, Map<String, Object[]> drgDecoderMap) {
		String decodedValue = value;
		
		if(drgDecoderMap != null && !drgDecoderMap.isEmpty()) {
			Object[] row = drgDecoderMap.get(name);
			if(row != null) {
				String in = (String)row[1];
				String out = (String)row[2];
				decodedValue = decodedValue.replaceAll(in, out);
			}
		}
		
		return decodedValue;
	}

	private List<String> generateUpdate(String flowName, List<HashMap<String, SdoPositionalDTO>> fields) {
		
		List<Object[]> drgDecoderList = tabgenDAO.executeSearchQuery("select campo, valore_in, valore_out from FM_CODIFICA_RISULTATI_DRG WHERE FLUSSO='SDO'");
		Map<String, Object[]> drgDecoderMap = new HashMap<String, Object[]>();
		for(Object[] row : drgDecoderList) {
			drgDecoderMap.put(((String)row[0]).toLowerCase(), row);
		}

		List<String> allQuerys = new ArrayList<>();

		for (HashMap<String, SdoPositionalDTO> pKey : fields) {

			String SQL_UPDATE_R = "UPDATE FM_FLOW_" + flowName + "_REG_1";
			String SQL_UPDATE_A = "UPDATE FM_FLOW_" + flowName + "_1";

			String where = " WHERE ";
			String set = " SET ";
			for (Entry<String, SdoPositionalDTO> entry : pKey.entrySet()) {
				String key = entry.getKey();
				SdoPositionalDTO value = entry.getValue();
				
				
				
				// controllo che siano campi chiave per la where
				if (value.getPk().equals("1")) {
					//if(key.equals("progressivosdo")) {
						//campo k da gestire
					//	value.setValue("20"+value.getValue());
					//}
					
					value.setValue(decodeValue(value.getValue(), key.toLowerCase(), drgDecoderMap));
					
					where += key + "=" +"'" + value.getValue()+"'" + " AND ";
				} else {
					// compongo i campi su cui fare update
					set += key + "=" +"'" + value.getValue()+"'" + ", ";

				}

			}

			if (where.endsWith("AND ")) {
				where = where.substring(0, where.length() - 4);
			}
			if (set.endsWith(", ")) {
				set = set.substring(0, set.length() - 2);
			}
			allQuerys.add(SQL_UPDATE_R + set + where);
			allQuerys.add(SQL_UPDATE_A + set + where);


		}

		return allQuerys;
	}



public void executeQuerys(List<String> querys) throws SQLException {
	
	
   /* List<PreparedStatement> preparedStaUpdate = new ArrayList<>();
	Integer index = 0;
    SessionImpl session = (SessionImpl) entityManager.getDelegate();
    Connection conn = session.connection();
    conn.setAutoCommit(false);*/
    //ciclo le query
    for(String query : querys) {
    	entityManager.createNativeQuery(query).executeUpdate();
    /*	PreparedStatement pr = conn.prepareStatement(query,
        		ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.FETCH_FORWARD);
    	preparedStaUpdate.add(pr);*/
    	
    }
  /*  for (PreparedStatement pre : preparedStaUpdate) {
        pre.executeBatch();
        pre.clearBatch();
        pre.clearParameters();
    }
    conn.commit();-*/
}
  



}



