package it.eng.care.domain.flow.crypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.eng.care.domain.flow.core.dao.ConfigurationDAO;
import it.eng.care.domain.flow.core.entity.ConfigurationDO;

@Configuration
public class CryptoConfig {

	@Value("${crypto.dev.enable:false}")
	boolean enableCryptoDEV = false;

	@Value("${crypto.dev.pass:12345678}")
	String cryptoPass;

	@Autowired
	ConfigurationDAO confDAO;

	@Bean
	public CryptoManager getCryptoManager() {
		boolean enableCrypto = false;

		ConfigurationDO conf = confDAO.findByKeyId("EnableCrypto");
		if (conf != null) {
			enableCrypto = Boolean.parseBoolean(conf.getValue());
		}
		
		CryptoManager m = new CryptoManagerImpl(enableCrypto);
		if (enableCryptoDEV) {
			m.setPassword(cryptoPass);
		}
		return m;
	}

}
