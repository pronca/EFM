package it.eng.care.domain.flow.jobs.look;

public interface LockService {

	void lock(String key, int until);

	boolean tryLock(String key, int until, String untilCfg);

	boolean unlock(String key);

}
