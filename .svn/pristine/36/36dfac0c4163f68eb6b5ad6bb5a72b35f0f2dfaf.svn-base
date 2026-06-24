package it.eng.care.domain.flow.core.service;

import java.util.List;

import org.springframework.data.util.Pair;

import it.eng.care.domain.flow.core.dto.MotherDashboardDTO;
import it.eng.care.domain.flow.core.entity.DashboardDO;
import it.eng.care.domain.flow.core.entity.DashboardErrorsDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.service.SearchInfo;

public interface DashboardService {

	Pair<List<DashboardDO>, SearchInfo> retrieveAllFiltered(BaseSearchInput searchInput);

    Pair<List<DashboardPraticheDO>, SearchInfo> retrieveAllFilteredForPratiche(BaseSearchInput searchInput);

    Pair<List<DashboardErrorsDO>, SearchInfo> retrieveAllFilteredForErrors(BaseSearchInput searchInput);

    Pair<List<MotherDashboardDTO>, SearchInfo> retrieveAllMotherFiltered(BaseSearchInput searchInput);


}
