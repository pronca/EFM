package it.eng.care.domain.flow.b2b.service.impl;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.care.domain.flow.b2b.model.ErrorModelDTO;
import it.eng.care.domain.flow.b2b.service.ValidationMessageService;
import it.eng.care.domain.flow.core.dao.FlowDAO;
import it.eng.care.domain.flow.core.dao.ValidationErrorsDAO;
import it.eng.care.domain.flow.core.entity.FlowDO;
import it.eng.care.domain.flow.core.entity.ValidationErrorsDO;
import it.eng.care.domain.flow.core.enumeration.ErrorServiceStatusEnum;
import it.eng.care.platform.tool.transport.operations.BaseSearchInput;

@Transactional
public class ValidationMessageServiceImpl implements ValidationMessageService{

	@Autowired
	protected FlowDAO flowDAO;
	@Autowired
	protected
	ValidationErrorsDAO validationErrorsDAO;
	
	
	@Override
	public void insertEvent(ErrorModelDTO model, String flowName,String jsonString) {
		// TODO Auto-generated method stub
		
		BaseSearchInput input = new BaseSearchInput();
		input.setParam("nameNoLike", flowName);
		List<FlowDO> flow = flowDAO.cerca(input);
		
		ValidationErrorsDO error = existingObject(model.getIdRequest());
		
		if(error==null) {
			//insert
			ValidationErrorsDO newError = new ValidationErrorsDO();
			newError.setCreationDate(new Date());
			newError.setExtrId(model.getIdRequest());
			newError.setJson(jsonString);
			newError.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
			newError.setNretry(0);
			newError.setFlowId(flow.get(0));
			newError.setCodiceAzienda(model.getCodiceAzienda());
			validationErrorsDAO.save(newError);
		} else {
			//update
			error.setCreationDate(new Date());
			error.setJson(jsonString);
			error.setStatus(ErrorServiceStatusEnum.PENDING.getStatus());
			error.setNretry(0);
			error.setDateSent(null);
			error.setCodiceAzienda(model.getCodiceAzienda());
			validationErrorsDAO.save(error);

		}
		
	}

	
	
	private ValidationErrorsDO nextTry(String extrId) {
		
		ValidationErrorsDO error = validationErrorsDAO.findByExtrId(extrId);
		if(error != null) {
			error.setExtrId(error.getExtrId()+1);
			return error;
		}
		
		return null;
	}
	
	private ValidationErrorsDO existingObject(String extrId) {
		ValidationErrorsDO error = validationErrorsDAO.findByExtrId(extrId);
		if(error != null) {
			return error;
		}
		return null;
	}



	@Override
	public List<ValidationErrorsDO> findAllPendingErrors(int numErr) {
		List<ValidationErrorsDO> errors = validationErrorsDAO.findTopByStatusAndNretryLessThan(ErrorServiceStatusEnum.PENDING.getStatus(),numErr);
		return errors;
	}



	@Override
	public void saveOrUpdateEvent(ValidationErrorsDO error) {
		validationErrorsDAO.save(error);
		
	}

	
	
	
	
}
