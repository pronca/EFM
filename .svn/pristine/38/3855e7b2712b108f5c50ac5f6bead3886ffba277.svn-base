package it.eng.care.domain.flow.drools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.row.Field;
import it.eng.care.domain.flow.drools.utility.api.DroolsDateUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDroolsDateUtility {
	
	@Autowired
	private DroolsDateUtility droolsDateUtility;
	
	@Test
	public void droolsDateUtilityTest() throws ParseException {
		String format = "ddMMyyyy";
		String formatIn = "ddMMyyyy";
		String formatOut = "yyyyMMdd";

		// Verifica se il valore di un campo di tipo Date è incluso nell'intervallo
		// desiderato
		Field field = new Field("FIELD_A", new Date(new Date().getTime() - 1000));
		Date from = new Date();
		Date to = new Date(new Date().getTime() + 1000);
		Boolean res = droolsDateUtility.betweenFieldDate(field, from, to, format);
		assertFalse(res);

		field = new Field("FIELD_A", new Date());
		from = new Date(new Date().getTime() - 1000);
		to = new Date(new Date().getTime() + 1000);
		res = droolsDateUtility.betweenFieldDate(field, from, to, format);
		assertTrue(res);

		res = droolsDateUtility.betweenFieldDate(field, from, from, format);
		assertFalse(res);
		
		res = droolsDateUtility.betweenFieldDate(null, from, from, format);
		res = droolsDateUtility.betweenFieldDate(field, null, from, format);
		res = droolsDateUtility.betweenFieldDate(field, from, null, format);
		res = droolsDateUtility.betweenFieldDate(field, from, from, null);
		res = droolsDateUtility.betweenFieldDate(new Field(null, null), from, from, format);
		

		// Confronta due campi di tipo Date
		Field fieldA = new Field("FIELD_A", new Date(new Date().getTime() - 1000));
		Field fieldB = new Field("FIELD_B", new Date(new Date().getTime() + 1000));
		int res1 = droolsDateUtility.compareFieldDate(fieldA, fieldA, format);
		assertTrue(res1 == 0);

		res1 = droolsDateUtility.compareFieldDate(fieldA, fieldB, format);
		assertTrue(res1 < 0);

		res1 = droolsDateUtility.compareFieldDate(fieldB, fieldA, format);
		assertTrue(res1 > 0);
		
		res1 = droolsDateUtility.compareFieldDate(null, fieldA, format);
		res1 = droolsDateUtility.compareFieldDate(fieldB, null, format);
		res1 = droolsDateUtility.compareFieldDate(fieldB, fieldA, null);
		res1 = droolsDateUtility.compareFieldDate(null, null, null);

		// Converte un campo data in stringa nel formato indicato
		String res2 = droolsDateUtility.dateToString(field, format);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.parse(res2);
		
		res2 = droolsDateUtility.dateToString(null, format);
		res2 = droolsDateUtility.dateToString(field, null);

		res2 = droolsDateUtility.dateToString(field, formatIn, formatOut);
		SimpleDateFormat sdf1 = new SimpleDateFormat(formatOut);
		sdf1.parse(res2);
		
		res2 = droolsDateUtility.dateToString(null, formatIn, formatOut);
		res2 = droolsDateUtility.dateToString(field, null, formatOut);
		res2 = droolsDateUtility.dateToString(field, formatIn, null);
		res2 = droolsDateUtility.dateToString(field, null, null);
		res2 = droolsDateUtility.dateToString(null, null, null);

		// Calcola la differenza in giorni tra due campi data
		res1 = droolsDateUtility.diffDate(fieldA, fieldA, format);
		assertTrue(res1 == 0);

		fieldB.setValue(new Date(((Date) fieldA.getValue()).getTime() + 1000 * 60 * 60 * 24 * 2));
		res1 = droolsDateUtility.diffDate(fieldA, fieldB, format);
		assertTrue(res1 == 2);

		res1 = droolsDateUtility.diffDate(fieldB, fieldA, format);
		assertTrue(res1 == 2);
		
		res1 = droolsDateUtility.diffDate(null, fieldA, format);
		res1 = droolsDateUtility.diffDate(fieldB, null, format);
		res1 = droolsDateUtility.diffDate(fieldB, fieldA, null);
		res1 = droolsDateUtility.diffDate(null, null, format);
		res1 = droolsDateUtility.diffDate(null, null, null);

		// Verifica se il valore di un campo rappresenta una data valida
		res = droolsDateUtility.isDate(field, format);
		assertTrue(res);

		res = droolsDateUtility.isDate(field, formatOut);
		assertTrue(res);

		field.setValue("X");
		res = droolsDateUtility.isDate(field, format);
		assertFalse(res);
		
		field.setValue("01012019");
		res = droolsDateUtility.isDate(field, format);
		assertTrue(res);
		
		res = droolsDateUtility.isDate(null, format);
		res = droolsDateUtility.isDate(field, null);
		res = droolsDateUtility.isDate(null, null);
		
		// Converte il valore di un campo in un oggetto Date
		field.setValue("X");
		from = droolsDateUtility.toDate(field, format);
		assertNull(from);
		
		field.setValue("01012019");
		from = droolsDateUtility.toDate(field, format);
		assertNotNull(from);

		from = droolsDateUtility.toDate(null, format);
		from = droolsDateUtility.toDate(field, null);
		from = droolsDateUtility.toDate(null, null);

	}

}
