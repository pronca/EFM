package it.eng.care.domain.flow.tabgen.config;

import it.eng.care.domain.flow.tabgen.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TabgenDozerConfig {

    @Bean
    public TabgenDOToDTOWithValuesConverter tabgenDOToDTOWithValuesConverter() {
        return new TabgenDOToDTOWithValuesConverter();
    }

    @Bean
    public TabgenDOToDTOBareConverter tabgenDOToDTOBareConverter() {
        return new TabgenDOToDTOBareConverter();
    }

    @Bean
    public TabgenDOToDTOFieldConverter tabgenDOToDTOFieldConverter() {
        return new TabgenDOToDTOFieldConverter();
    }

    @Bean
    public TabgenFieldDOToDTOFKConverter tabgenFieldDOToDTOFKConverter() {
        return new TabgenFieldDOToDTOFKConverter();
    }

    @Bean
    public TabgenFieldDOToDTOBareConverter tabgenFieldDOToDTOBareConverter() {
        return new TabgenFieldDOToDTOBareConverter();
    }

    @Bean
    public TabgenDOToDTOWithFieldsConverter tabgenDOToDTOWithFieldsConverter() {
        return new TabgenDOToDTOWithFieldsConverter();
    }

    @Bean
    public TabgenFieldDOToDTOWithTableConverter tabgenFieldDOToDTOWithTableAndFieldConverter() {
        return new TabgenFieldDOToDTOWithTableConverter();
    }

    @Bean
    public TabgenValueDOToDTOWithTableAndFieldConverter tabgenValueDOToDTOWithTableAndFieldConverter() {
        return new TabgenValueDOToDTOWithTableAndFieldConverter();
    }


}
