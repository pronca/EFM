package it.eng.care.domain.flow.tabgen.controller;

import it.eng.care.domain.flow.tabgen.dto.TabgenResultBean;
import it.eng.care.domain.flow.tabgen.dto.TabgenValue;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;
import it.eng.care.platform.tool.transport.operations.OperationResult;
import it.eng.care.platform.tool.transport.operations.SaveOperationResult;
import it.eng.care.platform.tool.transport.operations.SearchOperationResult;

public interface TabgenValueController {

    SearchOperationResult<TabgenValue> searchTableValue(BaseSearchInput searchInput);

    SaveOperationResult<TabgenResultBean> saveValue(TabgenValue inputDTO);

    TabgenResultBean deleteValue(TabgenValue input);

    OperationResult<Object> retriveColumnValueByColumnName(BaseSearchInput searchInput);


}
