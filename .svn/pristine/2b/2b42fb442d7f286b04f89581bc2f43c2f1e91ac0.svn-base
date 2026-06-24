package it.eng.care.domain.flow.jobs.look;

import java.time.Instant;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;
import it.eng.care.domain.flow.core.utility.LogUtil;

public class DatabaseLockService extends AbstractDatabaseLockService implements LockService {
	
	@Autowired
	private ConfigurationDAO configurationDAO;

	Logger logger = LoggerFactory.getLogger(DatabaseLockService.class);

	private int timeout = 30000;

	public DatabaseLockService(DataSource datasource, String tableName) {
		super(datasource, tableName);
	}

	@Override
	public boolean unlock(String key) {
		try {
			deleteRecordInTransaction(key);
			return true;
		} catch (Exception e) {
			LogUtil.logException(logger, "Error on unlock "+key+"", e);
			return false;
		}
	}

	@Override
	public boolean tryLock(String key, int timeout, String timeoutCfg) {
		if(!StringUtils.isEmpty(timeoutCfg)) {
			ConfigurationDO cfg = configurationDAO.findByKeyId(timeoutCfg);
			if(cfg != null && !StringUtils.isEmpty(cfg.getValue())) {
				timeout = Integer.valueOf(cfg.getValue());
			}
		}
		
		// se timeout è minore di 0 prendo quello di default
		logger.debug("try to lock entity: " + key);

		timeout = timeout > 0 ? timeout : this.timeout;
		boolean locked;
		try {
			Instant until = Instant.ofEpochMilli(System.currentTimeMillis() + timeout);
			locked = this.createRecord(key, until);
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
			locked = false;
		}
		if(!locked) {
			logger.debug("entity already locked: " + key);
		}
		return locked;
	}

	@Override
	public void lock(String key, int timeout) {
		// se timeout è minore di 0 prendo quello di default
		timeout = timeout > 0 ? timeout : this.timeout;
		try {
			Instant until = Instant.ofEpochMilli(System.currentTimeMillis() + timeout);
			this.createRecord(key, until);
		} catch (Exception e) {
			LogUtil.logException(logger, "", e);
		}
	}

	public boolean createRecord(String key, Instant until) {
		// eseguo tutto nella transazione
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					// cancello prima il record, se presente, solo se è scaduto
					deleteRecord(key, Instant.now());
					// provo ad inserire il nuovo record
					return insertRecord(key, until);
				} catch (DataIntegrityViolationException e) {
					LogUtil.logException(logger, "", e);
					return false;
				}
			}
		});
	}

	public boolean updateRecordInTransaction(String key, Instant until) {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				// aggiorno il record
				return updateRecord(key, until);
			}
		});
	}

	public boolean deleteRecordInTransaction(String key) {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				// aggiorno il record
				return deleteRecord(key);
			}
		});
	}

}
