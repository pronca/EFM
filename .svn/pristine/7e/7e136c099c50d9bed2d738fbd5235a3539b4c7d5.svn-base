package it.eng.care.domain.flow.drools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.icu.util.Calendar;

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.lookup.LookupRow;
import it.eng.care.domain.flow.drools.utility.api.LookupUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLookupUtility {
	
	@Autowired 
	private LookupUtility lookupUtility;
	
	@Test
	public void testLoadLookup1() {
		//Boolean loaded = lookupUtility.loadLookup("COMUNI");
		//assertTrue(loaded);
		
		//loaded = lookupUtility.loadLookup("X");
		//assertFalse(loaded);
		
		//loaded = lookupUtility.loadLookup(null);
		//assertFalse(loaded);
		
		boolean loaded = lookupUtility.loadLookup("LIVELLO_ISTRUZIONE", "LIV_ISTRUZIONE", null);
		
	}

	@Test
	public void testLoadLookup2() {
		Boolean loaded = lookupUtility.loadLookup("aziende_eroganti", "aziende_eroganti1", null);
		assertTrue(loaded);
		
		loaded = lookupUtility.loadLookup("aziende_eroganti", "aziende_eroganti1", null);
		assertTrue(loaded);
		
		loaded = lookupUtility.loadLookup("aziende_eroganti", "", null);
		assertTrue(loaded);
		
		loaded = lookupUtility.loadLookup("aziende_eroganti", null, null);
		assertTrue(loaded);
		
		loaded = lookupUtility.loadLookup("aziende_eroganti", "aziende_eroganti2", new Date());
		assertTrue(loaded);
		
		loaded = lookupUtility.loadLookup("x", "aziende_eroganti2", new Date());
		assertFalse(loaded);
		
		loaded = lookupUtility.loadLookup("x", "aziende_eroganti2", null);
		assertFalse(loaded);
	}

	@Test
	public void testLoadLookup3() {
		Boolean loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov1", null, null);
		assertTrue(loaded);
		
		String[][] filter = new String[][] {{}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, new Date());
		assertTrue(loaded);
		
		filter = new String[][] {{}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertTrue(loaded);
		
		filter = new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertTrue(loaded);
		
		filter = new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, new Date());
		assertTrue(loaded);
		
		filter = new String[][] {{"codice_regione","170"},{"denominazione_regioneAAAAAAAA","Basilicata"}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertFalse(loaded);
		
		filter = new String[][] {{"codice_regione","170"},{"denominazione_regioneAAAAAAAA",null}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertFalse(loaded);
		
		filter = new String[][] {{"codice_regione","170"},{null,"Basilicata"}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertTrue(loaded);
		
		filter = new String[][] {{null, null}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertTrue(loaded);
		
		filter = new String[][] {{"dt_enable", null}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertFalse(loaded);
		
		filter = new String[][] {{"testest", null}};		
		loaded = lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov2", filter, null);
		assertTrue(loaded);
		
	}

	@Test
	public void testLookupExists1() {
		lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov", null);
		
		Boolean exists = lookupUtility.lookupExists("cod_reg_prov", null);
		assertTrue(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}});
		assertTrue(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione","000"}});
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione",""}});
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione",null}});
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione1","000"}});
		assertFalse(exists);
		
	}

	@Test
	public void testLookupExists2() {
		lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov", null);
		
		Boolean exists = lookupUtility.lookupExists("cod_reg_prov", null);
		assertTrue(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}}, new Date());
		assertTrue(exists);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2200);
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}}, cal.getTime());
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione","000"}}, new Date());
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione",""}}, new Date());
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione",null}}, new Date());
		assertFalse(exists);
		
		exists = lookupUtility.lookupExists("cod_reg_prov", new String[][] {{"codice_regione1","000"}}, new Date());
		assertFalse(exists);
	}

	@Test
	public void testLookupSelect() {
		lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov", null);
		
		List<LookupRow> list = lookupUtility.lookupSelect("cod_reg_prov", null, null);
		assertTrue(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("", null, null);
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect(null, null, null);
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}}, new Date());
		assertTrue(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione","1700"},{"denominazione_regione","Basilicata"}}, new Date());
		assertFalse(list != null && list.size() > 0);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2200);
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}}, cal.getTime());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione","17000"},{"denominazione_regione","Basilicata"}}, cal.getTime());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione","000"}}, new Date());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione",""}}, new Date());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"testest",null}}, new Date());
		assertTrue(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione",null}}, new Date());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{"codice_regione1","000"}}, new Date());
		assertFalse(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{}}, new Date());
		assertTrue(list != null && list.size() > 0);
		
		list = lookupUtility.lookupSelect("cod_reg_prov", new String[][] {{null,null},{null,null},{null,null}}, new Date());
		assertTrue(list != null && list.size() > 0);
	}

	@Test
	public void testLookupSelectFirst() {
		lookupUtility.loadLookup("cod_reg_prov", "cod_reg_prov", null);
		
		LookupRow list = lookupUtility.lookupSelectFirst("cod_reg_prov", null, null);
		assertTrue(list != null);
		
		list = lookupUtility.lookupSelectFirst("", null, null);
		assertFalse(list != null);
		
		list = lookupUtility.lookupSelectFirst(null, null, null);
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione","090"},{"denominazione_regione","Toscana"}}, new Date());
		assertTrue(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione","1700"},{"denominazione_regione","Basilicata"}}, new Date());
		assertFalse(list != null );
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2200);
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione","170"},{"denominazione_regione","Basilicata"}}, cal.getTime());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione","17000"},{"denominazione_regione","Basilicata"}}, cal.getTime());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione","000"}}, new Date());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione",""}}, new Date());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"testest",null}}, new Date());
		assertTrue(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione",null}}, new Date());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{"codice_regione1","000"}}, new Date());
		assertFalse(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{}}, new Date());
		assertTrue(list != null );
		
		list = lookupUtility.lookupSelectFirst("cod_reg_prov", new String[][] {{null,null},{null,null},{null,null}}, new Date());
		assertTrue(list != null );
	}

}
