package it.eng.care.domain.flow.drools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.utility.api.DroolsStringUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDroolsStringUtility {

	@Autowired 
	private	DroolsStringUtility droolsStringUtility;
	
	/*
	 * @Autowired private DroolsUtility droolsUtility;
	 * 
	 */

	@Test
	public void testDroolsStringUtility() {
		// Estrae una sottostringa dal valore di un campo
		Field field = new Field("PROVA", "1234567890");
		String res = droolsStringUtility.substring(field , 4, 6);
		assertTrue(res.equals("56"));
		
		res = droolsStringUtility.substring(field , 0, 100);
		assertTrue(res.equals("1234567890"));
		
		res = droolsStringUtility.substring(field , -1, 6);
		assertTrue(res.equals("123456"));
		
		res = droolsStringUtility.substring(field , -1, 100);
		assertTrue(res.equals("1234567890"));
		
		res = droolsStringUtility.substring(field , 5, 4);
		assertTrue(res.equals("4"));
		
		res = droolsStringUtility.substring(field , 100, 99);
		assertTrue(res.equals("0"));
		
		res = droolsStringUtility.substring(field , null, 99);
		assertTrue(res.equals("1234567890"));
		
		res = droolsStringUtility.substring(field , 100, null);
		assertTrue(res.equals("0"));
		
		res = droolsStringUtility.substring(field , null, null);
		assertTrue(res.equals("1234567890"));
		
		res = droolsStringUtility.substring(null , 100, 99);
		res = droolsStringUtility.substring(null, null, null);

		// Verifica se il valore di un campo rispetta un formato
		String regexp = "[0-9]*";
		Boolean res1 = droolsStringUtility.matches(field, regexp);
		assertTrue(res1);
		
		regexp = "[a-z]*";
		res1 = droolsStringUtility.matches(field, regexp);
		assertFalse(res1);

		res1 = droolsStringUtility.matches(field, null);
		assertTrue(res1);
		
		res1 = droolsStringUtility.matches(new Field("", ""), regexp);
		assertFalse(res1);
		
		res1 = droolsStringUtility.matches(new Field("", null), regexp);
		assertFalse(res1);
		
		res1 = droolsStringUtility.matches(null, regexp);
		assertFalse(res1);
		
		res1 = droolsStringUtility.matches(null, null);
		assertFalse(res1);

		// Verifica se il valore di un campo è assente
		res1 = droolsStringUtility.isEmpty(new Field("", ""));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isEmpty(new Field("", " "));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isEmpty(new Field("a", "11"));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isEmpty(null);
		assertTrue(res1);
		
		res1 = droolsStringUtility.isEmpty(new Field("", null));
		assertTrue(res1);

		// Confronta i valori di due campi
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), new Field("PROVA", "1234567890"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), new Field("PROVA", "12345678901"));
		assertFalse(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", ""), new Field("PROVA", ""));
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", "123"), new Field("PROVA", ""));
		assertFalse(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", null), new Field("PROVA", ""));
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", ""), new Field("PROVA", null));
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", null), new Field("PROVA", null));
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(null, new Field("PROVA", null));
		assertTrue(res1);
		
		Field a = null;
		res1 = droolsStringUtility.equals(null, a);
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(a, a);
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), "");
		assertFalse(res1);
		
		String b = null;
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), b);
		assertFalse(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), "1234567890");
		assertTrue(res1);
		
		res1 = droolsStringUtility.equals(new Field("PROVA", "1234567890"), "12345678901");
		assertFalse(res1);

		// Verifica se il valore di un campo è contenuto in un insieme di valori
		res1 = droolsStringUtility.in(new Field("PROVA", "1234567890"), new String[]{"1234567890","123"});
		assertTrue(res1);
		
		res1 = droolsStringUtility.in(new Field("PROVA", "1234567890"), new String[]{"23443","123"});
		assertFalse(res1);
		
		res1 = droolsStringUtility.in(null, new String[]{"23443","123"});
		assertFalse(res1);
		
		res1 = droolsStringUtility.in(new Field("PROVA", ""), new String[]{"23443","123"});
		assertFalse(res1);
		
		res1 = droolsStringUtility.in(new Field("PROVA", null), new String[]{"23443","123"});
		assertFalse(res1);
		
		res1 = droolsStringUtility.in(new Field("PROVA", null), null);
		assertFalse(res1);
		
		res1 = droolsStringUtility.in(null, null);
		assertFalse(res1);

		// Verifica se il valore di un campo inizia con il prefisso desiderato
		res1 = droolsStringUtility.startWith(new Field("PROVA", "1234567890"), "123" );
		assertTrue(res1);
		
		res1 = droolsStringUtility.startWith(new Field("PROVA", "1234567890"), "2123" );
		assertFalse(res1);
		
		res1 = droolsStringUtility.startWith(new Field("PROVA", ""), "123" );
		assertFalse(res1);
		
		res1 = droolsStringUtility.startWith(new Field("PROVA", null), "123" );
		assertFalse(res1);
		
		res1 = droolsStringUtility.startWith(null, "123" );
		assertFalse(res1);
		
		res1 = droolsStringUtility.startWith(new Field("PROVA", "1234567890"), "" );
		assertTrue(res1);
		
		res1 = droolsStringUtility.startWith(new Field("PROVA", "1234567890"), null);
		assertTrue(res1);
		
		// Converte il valore di un campo in un intero
		Integer res2 = droolsStringUtility.toInt(new Field("PROVA", "123"));
		assertTrue(res2 != null);
		
		res2 = droolsStringUtility.toInt(new Field("PROVA", "123A"));
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt(new Field("PROVA", "123,123"));
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt(new Field("PROVA", "123.123"));
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt(new Field("PROVA", null));
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt(a);
		assertTrue(res2 == null);
		
		// Converte il valore di un campo in un intero
		res2 = droolsStringUtility.toInt("1");
		assertTrue(res2 != null);
		
		res2 = droolsStringUtility.toInt("1.1");
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt("1,1");
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt("1A");
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt("");
		assertTrue(res2 == null);
		
		res2 = droolsStringUtility.toInt(b);
		assertTrue(res2 == null);
		
		// Converte il valore di un campo in un double
		Double res3 = droolsStringUtility.toDouble(new Field("PROVA", "123"));
		assertTrue(res3 != null);

		res3 = droolsStringUtility.toDouble(new Field("PROVA", "123,123"));
		assertTrue(res3 != null);
		
		res3 = droolsStringUtility.toDouble(new Field("PROVA", "123.123"));
		assertTrue(res3 != null);
		
		res3 = droolsStringUtility.toDouble(new Field("PROVA", "123D"));
		assertTrue(res3 != null);
		
		res3 = droolsStringUtility.toDouble(new Field("PROVA", "1ss23"));
		assertTrue(res3 == null);
		
		res3 = droolsStringUtility.toDouble(new Field("PROVA", null));
		assertTrue(res3 == null);
		
		res3 = droolsStringUtility.toDouble(new Field("PROVA", ""));
		assertTrue(res3 == null);
		
		res3 = droolsStringUtility.toDouble(a);
		assertTrue(res3 == null);
		
		// Converte il valore di un campo in un double
		String d = null;
		res3 = droolsStringUtility.toDouble("11");
		assertTrue(res3 != null);
		
		res3 = droolsStringUtility.toDouble("");
		assertTrue(res3 == null);
		
		res3 = droolsStringUtility.toDouble(d);
		assertTrue(res3 == null);
		
		
		// Verifica se il valore di un campo rappresenta un intero
		res1 = droolsStringUtility.isInt(new Field("PROVA", "123"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isInt(new Field("PROVA", "123,123"));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isInt(new Field("PROVA", "123.123"));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isInt(new Field("PROVA", ""));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isInt(new Field("PROVA", null));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isInt(a);
		assertFalse(res1);
				
		//  Verifica se il valore di un campo rappresenta un intero
		res1 = droolsStringUtility.isInt("123");
		assertTrue(res1);
		
		res1 = droolsStringUtility.isInt("");
		assertFalse(res1);
		
		res1 = droolsStringUtility.isInt(b);
		assertFalse(res1);
		
		// Verifica se il valore di un campo rappresenta un double. La virgola viene sostituita con il punto
		res1 = droolsStringUtility.isDouble(new Field("PROVA", "123"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble(new Field("PROVA", "123D"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble(new Field("PROVA", "123.123"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble(new Field("PROVA", "123,123"));
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble(new Field("PROVA", ""));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isDouble(new Field("PROVA", null));
		assertFalse(res1);
		
		res1 = droolsStringUtility.isDouble(a);
		assertFalse(res1);
		
		// Verifica se il valore di un campo rappresenta un double. La virgola viene sostituita con il punto
		res1 = droolsStringUtility.isDouble("123");
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble("123D");
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble("123,123");
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble("123.123");
		assertTrue(res1);
		
		res1 = droolsStringUtility.isDouble("");
		assertFalse(res1);
		
		res1 = droolsStringUtility.isDouble(b);
		assertFalse(res1);
		
		
	}
	
	
}
