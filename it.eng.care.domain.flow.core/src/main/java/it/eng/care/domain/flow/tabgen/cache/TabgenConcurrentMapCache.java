package it.eng.care.domain.flow.tabgen.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.concurrent.ConcurrentMapCache;

public class TabgenConcurrentMapCache extends ConcurrentMapCache {
	
	private List<String> keys;
	
	public TabgenConcurrentMapCache(String name, boolean allowNullValues) {
		super(name, allowNullValues);
		keys = new ArrayList<String>();
	}

	@Override
	public void put(Object key, Object value) {
		super.put(key, value);
		
		if(key != null) {
			this.keys.add(key.toString());
		}
	}

	@Override
	public void evict(Object key) {
		if(key != null) {
			List<String> temp = new ArrayList<String>();
			String keyStr = key.toString();
			if(this.keys != null) {
				for(String addedKey : this.keys) {
					if(addedKey.toLowerCase().startsWith(keyStr.toLowerCase())) {
						super.evict(addedKey);
					} else {
						temp.add(addedKey);
					}
				}
				this.keys = temp;
			}
		}
	}

}
