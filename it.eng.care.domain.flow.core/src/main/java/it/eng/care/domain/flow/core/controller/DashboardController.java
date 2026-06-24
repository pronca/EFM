package it.eng.care.domain.flow.core.controller;

import it.eng.care.domain.flow.core.dto.DashboardConfigDTO;
import it.eng.care.domain.flow.core.dto.DashboardDTO;
import it.eng.care.domain.flow.core.dto.MotherDashboardDTO;
import it.eng.care.domain.flow.core.entity.DashboardErrorsDO;
import it.eng.care.domain.flow.core.entity.DashboardPraticheDO;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface DashboardController {

    SearchOperationResult<DashboardDTO> search(BaseSearchInput searchInput);

    SearchOperationResult<DashboardPraticheDO> searchProcedure(BaseSearchInput searchInput);

    SearchOperationResult<DashboardErrorsDO> searchTopErrors(BaseSearchInput searchInput);

    SearchOperationResult<MotherDashboardDTO> searchMother(BaseSearchInput searchInput);

	SearchOperationResult<DashboardConfigDTO> searchBadge(BaseSearchInput searchInput);


}
