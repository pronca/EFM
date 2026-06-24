package it.eng.care.domain.flow.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.tabgen.dto.TabgenValueFilter;
import it.eng.care.domain.flow.tabgen.entity.TabgenValueDO;
import it.eng.care.domain.flow.tabgen.service.TabgenService;
import it.eng.care.platform.authentication.api.model.bean.LoggedUser;
import it.eng.care.platform.authentication.api.model.dto.OrganizationDTO;
import it.eng.care.platform.authentication.api.model.dto.RoleDTO;
import it.eng.care.platform.authentication.api.model.dto.SiteDTO;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

public class FlowManagerProfileServiceImpl implements FlowManagerProfileService {
	
	@Autowired
	private LoggedUserService loggedUserService;
	
	@Autowired
	private TabgenService tabgenService;
	
	@Autowired
	private FlowDAO flowDAO;

	@Override
	public List<String> getFlows() {
		List<String> flows = new ArrayList<String>();
		
		List<TabgenValueDO> tabgenValueList = findFlows();
		if(tabgenValueList != null && !tabgenValueList.isEmpty()) {
			for(TabgenValueDO tabgenValue : tabgenValueList) {
				String flowName = tabgenValue.getField4();
				flows.add(flowName);
			}
		} else {
			flows.add(" ");
		}
		
		if (!flows.isEmpty()) {
			flows = flows.stream().distinct().collect(Collectors.toList());
		}
		
		return flows;
	}
	
	@Override
	public List<String> getFlowIds() {
		List<String> flowIds = new ArrayList<String>();
		List<String> flows = new ArrayList<String>();
		
		List<TabgenValueDO> tabgenValueList = findFlows();
		if(tabgenValueList != null && !tabgenValueList.isEmpty()) {
			for(TabgenValueDO tabgenValue : tabgenValueList) {
				String flowName = tabgenValue.getField4();
				flows.add(flowName);
			}

			if(flows != null && !flows.isEmpty()) {
				BaseSearchInput filter = new BaseSearchInput();
				filter.setValue("flowNameList", flows);
				List<FlowDO> flowList = flowDAO.cerca(filter);
				if(flowList != null && !flowList.isEmpty()) {
					for (FlowDO flowDO : flowList) {
						flowIds.add(flowDO.getId());
					}
				}
			}
			
		} else {
			flowIds.add(" ");
		}
		
		if (!flows.isEmpty()) {
			flows = flows.stream().distinct().collect(Collectors.toList());
		}
		
		return flowIds;
	}
	
	@Override
	public Boolean checkFlowByName(String flowName) {
		List<TabgenValueDO> tabgenValueList = findFlows();
		if(tabgenValueList != null && !tabgenValueList.isEmpty()) {
			for(TabgenValueDO tabgenValue : tabgenValueList) {
				String tabgenFlowName = tabgenValue.getField4();
				if(flowName.equals(tabgenFlowName)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean checkFlowById(String id) {
		FlowDO flow = (id== null || id.isBlank()) ? null : flowDAO.findById(id).orElse(null);
		if(flow != null) {
			List<TabgenValueDO> tabgenValueList = findFlows();
			if(tabgenValueList != null && !tabgenValueList.isEmpty()) {
				for(TabgenValueDO tabgenValue : tabgenValueList) {
					String tabgenFlowName = tabgenValue.getField4();
					if(flow.getName().equals(tabgenFlowName)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private List<TabgenValueDO> findFlows() {
		List<TabgenValueDO> tabgenValueList = new ArrayList<TabgenValueDO>();
		
		LoggedUser currentUser = loggedUserService.getCurrentUser();
		
		if(currentUser != null) {
			OrganizationDTO organization = currentUser.getCurrentOrganization();
			SiteDTO site = currentUser.getCurrentSite();
			RoleDTO role = currentUser.getCurrentRole();
			
			TabgenValueFilter profileFilter = new TabgenValueFilter();
			profileFilter.setTabgenId("PROFILO_FLUSSI");
			if (organization!=null) {
				profileFilter.setField1(organization.getCode());
			}
			if (site!=null) {
				profileFilter.setField2(site.getCode());
			}
			if (role!=null) {
				profileFilter.setField3(role.getCode());
			}
			
			tabgenValueList = tabgenService.searchTabgenValueByFilter(profileFilter);
		}
		
		return tabgenValueList;
	}

	@Override
	public List<String> getAziendeForUserProfile() {
		List<String> aziendeEnabled = new ArrayList<String>();
		List<String> aziendeEnabledAll = new ArrayList<String>();
		List<TabgenValueDO> tabgenValueList = findFlows();
		if(tabgenValueList != null && !tabgenValueList.isEmpty()) {
			aziendeEnabled = tabgenValueList.stream().map(TabgenValueDO::getField6).distinct().filter(Objects::nonNull).collect(Collectors.toList());
			if (!aziendeEnabled.isEmpty()) {
				aziendeEnabledAll.addAll(aziendeEnabled);
				aziendeEnabledAll = aziendeEnabledAll.stream().distinct().collect(Collectors.toList());
				aziendeEnabled.clear();
				for (String aziendeEnabledFlow : aziendeEnabledAll) {
					aziendeEnabled.addAll(Arrays.asList(aziendeEnabledFlow.split(",")));
				}
				aziendeEnabled = aziendeEnabled.stream().distinct().collect(Collectors.toList());
			}
		}
		
		return aziendeEnabled;
	}

}
