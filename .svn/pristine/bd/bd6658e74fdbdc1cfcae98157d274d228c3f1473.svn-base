package it.eng.care.domain.flow.core.converter.FormFlow;

import it.eng.care.domain.flow.core.dao.FlowTableDAO;
import it.eng.care.domain.flow.core.dao.FlowTableFieldDAO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableLinkDTO;
import it.eng.care.domain.flow.core.entity.FlowForeignKeyDO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;

public class FormFlowTableLinkDTOtoFlowForeignKeyDO implements Converter<FormFlowTableLinkDTO, FlowForeignKeyDO> {

    @Autowired
    private FlowTableDAO flowTableDAO;
    @Autowired
    private FlowTableFieldDAO flowTableFieldDAO;

    @Override
    public void convert(FormFlowTableLinkDTO formFlowTableLinkDTO, FlowForeignKeyDO flowForeignKeyDO, ConversionContext conversionContext) {

        if (formFlowTableLinkDTO.getId() != null) {
            flowForeignKeyDO.setId(formFlowTableLinkDTO.getId());
        }
        flowForeignKeyDO.setIdTable((formFlowTableLinkDTO.getIdTable() == null || formFlowTableLinkDTO.getIdTable().isBlank()) ? null : flowTableDAO.findById(formFlowTableLinkDTO.getIdTable()).orElse(null));
        flowForeignKeyDO.setIdTableReferenced((formFlowTableLinkDTO.getIdTableReferenced() == null || formFlowTableLinkDTO.getIdTableReferenced().isBlank()) ? null : flowTableDAO.findById(formFlowTableLinkDTO.getIdTableReferenced()).orElse(null));
        flowForeignKeyDO.setIdFieldTable((formFlowTableLinkDTO.getIdFieldTable() == null || formFlowTableLinkDTO.getIdFieldTable().isBlank()) ? null : flowTableFieldDAO.findById(formFlowTableLinkDTO.getIdFieldTable()).orElse(null));
        flowForeignKeyDO.setIdFieldTableReferenced((formFlowTableLinkDTO.getIdFieldTableReferenced() == null || formFlowTableLinkDTO.getIdFieldTableReferenced().isBlank()) ? null : flowTableFieldDAO.findById(formFlowTableLinkDTO.getIdFieldTableReferenced()).orElse(null));
        //TODO campi generazione jsonschema
        //come si chiama
        flowForeignKeyDO.setJsonField(formFlowTableLinkDTO.getJsonField());
        //tipo
        flowForeignKeyDO.setJsonFieldType(formFlowTableLinkDTO.getJsonFieldType());
        flowForeignKeyDO.setMandatory(true);

    }
}