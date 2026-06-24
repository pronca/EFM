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

import it.eng.care.domain.flow.drools.config.DroolsConfigTest;
import it.eng.care.domain.flow.drools.model.lookup.ColumnType;
import it.eng.care.domain.flow.drools.utility.api.DroolsQueryUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DroolsConfigTest.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDroolsQueryUtility {

	@Autowired
	private DroolsQueryUtility droolsQueryUtility;

	@Test
	public void testExecuteQuery() {
		// Esegue una query sql
		String sqlString = "select id from fm_flow";
		List<Object> res = droolsQueryUtility.executeQuery(sqlString, null, null);
		assertTrue(res != null && res.size() > 0);

		sqlString = "select id from fm_flow";
		Object[] parameters = new Object[] {};
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{});
		assertTrue(res != null && res.size() > 0);

		sqlString = "select id from fm_flow";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN});
		assertTrue(res != null && res.size() > 0);

		sqlString = "select id from fm_flow";
		parameters = new Object[] { true, "TEST" };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN, ColumnType.STRING});
		assertTrue(res != null && res.size() > 0);

		sqlString = "select id from fm_flow where name is null";
		res = droolsQueryUtility.executeQuery(sqlString, null, null);
		assertFalse(res != null && res.size() > 0);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN});
		assertTrue(res != null && res.size() > 0);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		res = droolsQueryUtility.executeQuery(sqlString, null, null);
		assertFalse(res != null && res.size() > 0);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] {};
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{});
		assertFalse(res != null && res.size() > 0);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] { null };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN});
		assertTrue(res != null);

		StringBuilder sb = new StringBuilder();
		sb.append("select fl.id from fm_flow_version fv ");
		sb.append("inner join fm_flow fl on fv.flow = fl.id ");
		sb.append("inner join fm_version v on fv.version = v.id ");
		sb.append("where fl.status = ? and data_creation > ?");
		parameters = new Object[] { true, new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 365 * 10) };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res.size() > 0);

		parameters = new Object[] { true, null };
		res = droolsQueryUtility.executeQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res.size() > 0);
	}

	@Test
	public void testExecuteUniqueQuery() {
		// Esegue una query sql
		String sqlString = "select id from fm_flow";
		Object res = droolsQueryUtility.executeUniqueQuery(sqlString, null, null);
		assertTrue(res != null);

		sqlString = "select id from fm_flow";
		Object[] parameters = new Object[] {};
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, null);
		assertTrue(res != null);

		sqlString = "select id from fm_flow";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null);

		sqlString = "select id from fm_flow";
		parameters = new Object[] { true, "TEST" };
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.STRING});
		assertTrue(res != null);

		sqlString = "select id from fm_flow where name is null";
		res = droolsQueryUtility.executeUniqueQuery(sqlString, null, null);
		assertFalse(res != null);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		res = droolsQueryUtility.executeUniqueQuery(sqlString, null, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res != null);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] {};
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res != null);

		sqlString = "select id from fm_flow where name is not null and status = ?";
		parameters = new Object[] { null };
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res != null);

		StringBuilder sb = new StringBuilder();
		sb.append("select fl.id from fm_flow_version fv ");
		sb.append("inner join fm_flow fl on fv.flow = fl.id ");
		sb.append("inner join fm_version v on fv.version = v.id ");
		sb.append("where fl.status = ? and data_creation > ?");
		parameters = new Object[] { true, new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 365 * 10) };
		res = droolsQueryUtility.executeUniqueQuery(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null);

	}

	@Test
	public void testExecuteCount() {
		String sqlString = "select count(fv) from fm_flow fv";
		Integer res = droolsQueryUtility.executeCount(sqlString, null, null);
		assertTrue(res != null && res > 0);

		sqlString = "select count(fv) from fm_flow fv";
		Object[] parameters = new Object[] {};
		res = droolsQueryUtility.executeCount(sqlString, parameters, null);
		assertTrue(res != null && res > 0);

		sqlString = "select count(fv) from fm_flow fv";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res > 0);

		sqlString = "select count(fv) from fm_flow fv";
		parameters = new Object[] { true, "TEST" };
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.STRING});
		assertTrue(res != null && res > 0);

		sqlString = "select count(fv) from fm_flow fv where fv.name is null";
		res = droolsQueryUtility.executeCount(sqlString, null, null);
		assertTrue(res != null && res == 0);

		sqlString = "select count(fv) from fm_flow fv where fv.name is not null and fv.status = ?";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res > 0);

		sqlString = "select count(fv) from fm_flow fv where fv.name is not null and fv.status = ?";
		res = droolsQueryUtility.executeCount(sqlString, null, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res == -1);

		sqlString = "select count(fv) from fm_flow fv where fv.name is not null and fv.status = ?";
		parameters = new Object[] {};
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res == -1);

		sqlString = "select count(fv) from fm_flow fv where vname is not null and fv.status = ?";
		parameters = new Object[] { null };
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res == 0);

		StringBuilder sb = new StringBuilder();
		sb.append("select count(fv) from fm_flow_version fv ");
		sb.append("inner join fm_flow fl on fv.flow = fl.id ");
		sb.append("inner join fm_version v on fv.version = v.id ");
		sb.append("where fl.status = ? and data_creation > ?");
		parameters = new Object[] { true, new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 365 * 10) };
		res = droolsQueryUtility.executeCount(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res != null && res > 0);
		
	}

	@Test
	public void testExecuteExists() {
		String sqlString = "select 1 from fm_flow";
		Boolean res = droolsQueryUtility.executeExists(sqlString, null, null);
		assertTrue(res);

		sqlString = "select 1 from fm_flow";
		Object[] parameters = new Object[] {};
		res = droolsQueryUtility.executeExists(sqlString, parameters, null);
		assertTrue(res);

		sqlString = "select 1 from fm_flow";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res);

		sqlString = "select 1 from fm_flow";
		parameters = new Object[] { true, "TEST" };
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.STRING});
		assertTrue(res);

		sqlString = "select 1 from fm_flow where name is null";
		res = droolsQueryUtility.executeExists(sqlString, null, null);
		assertFalse(res);

		sqlString = "select 1 from fm_flow where name is not null and status = ?";
		parameters = new Object[] { true };
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res);

		sqlString = "select 1 from fm_flow where name is not null and status = ?";
		res = droolsQueryUtility.executeExists(sqlString, null, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res);

		sqlString = "select 1 from fm_flow where name is not null and status = ?";
		parameters = new Object[] {};
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res);

		sqlString = "select 1 from fm_flow where name is not null and status = ?";
		parameters = new Object[] { null };
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertFalse(res);

		StringBuilder sb = new StringBuilder();
		sb.append("select 1 from fm_flow_version fv ");
		sb.append("inner join fm_flow fl on fv.flow = fl.id ");
		sb.append("inner join fm_version v on fv.version = v.id ");
		sb.append("where fl.status = ? and data_creation > ?");
		parameters = new Object[] { true, new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 365 * 10) };
		res = droolsQueryUtility.executeExists(sqlString, parameters, new ColumnType[]{ColumnType.BOOLEAN,ColumnType.DATE});
		assertTrue(res);
		
	}

}
