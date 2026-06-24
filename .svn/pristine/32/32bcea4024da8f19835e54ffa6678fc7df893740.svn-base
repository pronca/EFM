package it.eng.care.domain.flow.tabgen.config;

import it.eng.care.domain.flow.tabgen.entity.TabgenDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenFieldDO;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.platform.persistence.impl.config.PersistenceConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabgenPersistConfig {

    @Bean
    public PersistenceConfigurer pPDPersistenceConfigurer() {
        return new PersistenceConfigurer()
                .addPackage(TabgenDO.class)
                .addPackage(TabgenFieldDO.class)
                .addPackage(TabgenValueDO.class);
    }


}
