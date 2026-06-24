package it.eng.care.domain.flow.tabgen.config;

import it.eng.care.domain.flow.tabgen.dao.TabgenDAO;
import it.eng.care.domain.flow.tabgen.dao.TabgenFieldDAO;
import it.eng.care.domain.flow.tabgen.dao.TabgenValueDAO;
import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenDAOImpl;
import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenFieldDAOImpl;
import it.eng.care.domain.flow.tabgen.dao.querySearch.TabgenValueDAOImpl;
import it.eng.care.platform.persistence.api.factory.CareRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabgenRepositoryConfig {

    @Bean
    public TabgenDAO tabgenDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(TabgenDAO.class);
    }

    @Bean
    public TabgenFieldDAO tabgenFieldDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(TabgenFieldDAO.class);
    }

    @Bean
    public TabgenValueDAO tabgenValueDAO(CareRepositoryFactory careRepositoryService) {
        return careRepositoryService.createRepository(TabgenValueDAO.class);
    }

    @Bean
    public TabgenDAOImpl tabgenDAOImpl() {
        return new TabgenDAOImpl();
    }

    @Bean
    public TabgenValueDAOImpl tabgenValueDAOImpl() {
        return new TabgenValueDAOImpl();
    }

    @Bean
    public TabgenFieldDAOImpl tabgenFieldDAOImpl() {
        return new TabgenFieldDAOImpl();
    }

}
