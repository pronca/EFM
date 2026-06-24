package it.eng.care.domain.flow.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.eng.care.domain.flow.core.service.TwoLevelResultsService;
import it.eng.care.domain.flow.tabgen.dto.TabgenMap;

@Service
public class RegionErrorTableService {

    @Autowired
    private TwoLevelResultsService twoLevelResultsService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createTableIfNotExistsNoTx(TabgenMap tabgen) throws Exception {
        twoLevelResultsService.createTableIfNotExists(tabgen);
    }
}