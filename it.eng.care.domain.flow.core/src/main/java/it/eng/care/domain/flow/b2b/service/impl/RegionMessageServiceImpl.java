package it.eng.care.domain.flow.b2b.service.impl;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.b2b.model.RegionErrorModelDTO;
import it.eng.care.domain.flow.b2b.service.RegionMessageService;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.RegionErrorsDAO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.RegionErrorsDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.domain.flow.core.enumeration.RegionModelTypeEnum;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Transactional
public class RegionMessageServiceImpl implements RegionMessageService {

    @Autowired
    protected FlowDAO flowDAO;

    @Autowired
    protected RegionErrorsDAO regionErrorsDAO;

    @Override
    public void insertEvent(RegionErrorModelDTO model, String flowName, String jsonString, String keyMessage) {

        if (model == null || model.getIdRequest() == null || model.getIdRequest().isBlank()) {
            return;
        }

        if (keyMessage == null || keyMessage.isBlank()) {
            return;
        }

        BaseSearchInput input = new BaseSearchInput();
        input.setParam("nameNoLike", flowName);
        List<FlowDO> flows = flowDAO.cerca(input);

        if (flows == null || flows.isEmpty()) {
            return;
        }

        FlowDO flow = flows.get(0);

        RegionErrorsDO error = existingObject(model.getIdRequest(), keyMessage, flow.getId());

        String regionStatus = normalizeRegionStatus(model.getStatusRegion());

        if (error == null) {
            RegionErrorsDO newError = new RegionErrorsDO();
            newError.setCreationDate(new Date());
            newError.setExtrId(model.getIdRequest());
            newError.setKeyMessage(keyMessage);
            newError.setJson(jsonString);
            newError.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
            newError.setRegionStatus(regionStatus);
            newError.setNretry(0);
            newError.setFlowId(flow);
            newError.setCodiceAzienda(model.getCodiceAzienda());
            newError.setDateSent(null);

            regionErrorsDAO.save(newError);

        } else {
            error.setCreationDate(new Date());
            error.setJson(jsonString);
            error.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
            error.setRegionStatus(regionStatus);
            error.setNretry(0);
            error.setDateSent(null);
            error.setCodiceAzienda(model.getCodiceAzienda());
            error.setFlowId(flow);
            error.setKeyMessage(keyMessage);

            regionErrorsDAO.save(error);
        }
    }

    @Override
    public List<RegionErrorsDO> findAllPendingErrors(int numErr) {
        return regionErrorsDAO.findTopBySendStatusAndNretryLessThan(
                ErrorServiceStatusEnum.PENDING.getStatus(),
                numErr
        );
    }

    @Override
    public void saveOrUpdateEvent(RegionErrorsDO error) {
        regionErrorsDAO.save(error);
    }

    private RegionErrorsDO existingObject(String extrId, String keyMessage, String flowId) {
        if (extrId == null || extrId.isBlank()
                || keyMessage == null || keyMessage.isBlank()
                || flowId == null || flowId.isBlank()) {
            return null;
        }

        return regionErrorsDAO.findByExtrIdAndKeyMessageAndFlowId_Id(extrId, keyMessage, flowId);
    }

    private String normalizeRegionStatus(String statusRegion) {
        if (statusRegion == null || statusRegion.isBlank()) {
            return RegionModelTypeEnum.VALID.toString();
        }

        String value = statusRegion.trim().toUpperCase();

        if (RegionModelTypeEnum.ERROR.toString().equals(value) || RegionModelTypeEnum.WARNING.toString().equals(value)) {
            return RegionModelTypeEnum.ERROR.toString();
        }

        return RegionModelTypeEnum.VALID.toString();
    }
    
    @Override
    public void insertOrUpdatePendingEvent(RegionErrorModelDTO model, String flowName, String jsonString, String keyMessage) {

        if (model == null || model.getIdRequest() == null || model.getIdRequest().isBlank()) {
            return;
        }

        if (keyMessage == null || keyMessage.isBlank()) {
            return;
        }

        BaseSearchInput input = new BaseSearchInput();
        input.setParam("nameNoLike", flowName);
        List<FlowDO> flows = flowDAO.cerca(input);

        if (flows == null || flows.isEmpty()) {
            return;
        }

        FlowDO flow = flows.get(0);
        String regionStatus = normalizeRegionStatus(model.getStatusRegion());

        RegionErrorsDO pendingError =
                regionErrorsDAO.findTopByFlowId_IdAndKeyMessageAndSendStatusOrderByCreationDateDesc(
                        flow.getId(),
                        keyMessage,
                        ErrorServiceStatusEnum.PENDING.getStatus()
                );

        if (pendingError != null) {
            pendingError.setCreationDate(new Date());
            pendingError.setExtrId(model.getIdRequest());
            pendingError.setJson(jsonString);
            pendingError.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
            pendingError.setRegionStatus(regionStatus);
            pendingError.setNretry(0);
            pendingError.setDateSent(null);
            pendingError.setCodiceAzienda(model.getCodiceAzienda());
            pendingError.setFlowId(flow);
            pendingError.setKeyMessage(keyMessage);

            regionErrorsDAO.save(pendingError);
            return;
        }

        RegionErrorsDO newError = new RegionErrorsDO();
        newError.setCreationDate(new Date());
        newError.setExtrId(model.getIdRequest());
        newError.setKeyMessage(keyMessage);
        newError.setJson(jsonString);
        newError.setSendStatus(ErrorServiceStatusEnum.PENDING.getStatus());
        newError.setRegionStatus(regionStatus);
        newError.setNretry(0);
        newError.setFlowId(flow);
        newError.setCodiceAzienda(model.getCodiceAzienda());
        newError.setDateSent(null);

        regionErrorsDAO.save(newError);
    }
}