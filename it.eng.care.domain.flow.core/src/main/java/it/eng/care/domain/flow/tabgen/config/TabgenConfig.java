package it.eng.care.domain.flow.tabgen.config;

import it.eng.care.platform.common.dozer.config.DozerCommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TabgenDozerConfig.class,
        TabgenRepositoryConfig.class,
        TabgenServiceConfig.class,
        //TabgenPersistConfig.class,
        //PersistenceConfig.class,
        DozerCommonConfig.class
})
public class TabgenConfig {


}
