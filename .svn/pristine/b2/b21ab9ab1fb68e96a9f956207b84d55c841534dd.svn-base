package it.eng.care.domain.flow.core.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowDTO;
import it.eng.care.domain.flow.core.dto.FormFlowConfig.FormFlowTableDTO;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LinkedHashMapToFormFlowDTO implements Converter<LinkedHashMap, FormFlowDTO> {

    @Override
    public void convert(LinkedHashMap linkedHashMap, FormFlowDTO formFlowDTO, ConversionContext conversionContext) {

        if (linkedHashMap.get("id") != null) {
            formFlowDTO.setId(linkedHashMap.get("id").toString());
        } else {
            formFlowDTO.setId(null);
        }
        if (linkedHashMap.get("name") != null) {
            formFlowDTO.setName(linkedHashMap.get("name").toString());
        } else {
            formFlowDTO.setName(null);
        }
        if (linkedHashMap.get("description") != null) {
            formFlowDTO.setDescription(linkedHashMap.get("description").toString());
        } else {
            formFlowDTO.setDescription(null);
        }
        if (linkedHashMap.get("code") != null) {
            formFlowDTO.setCode(linkedHashMap.get("code").toString());
        } else {
            formFlowDTO.setCode(null);
        }
        if (linkedHashMap.get("status") != null) {
            formFlowDTO.setStatus((Boolean) linkedHashMap.get("status"));
        }
        if (linkedHashMap.get("uniqueness") != null) {
            formFlowDTO.setUniqueness((Boolean) linkedHashMap.get("uniqueness"));
        }
        if (linkedHashMap.get("periodicy") != null) {
            formFlowDTO.setPeriodicy(linkedHashMap.get("periodicy").toString());
        } else {
            formFlowDTO.setPeriodicy(null);
        }
        if (linkedHashMap.get("remoteSend") != null) {
            formFlowDTO.setRemoteSend((Boolean) linkedHashMap.get("remoteSend"));
        }
        if (linkedHashMap.get("scheduling") != null) {
            formFlowDTO.setScheduling((Boolean) linkedHashMap.get("scheduling"));

        }
        if (linkedHashMap.get("monthlyDeadline") != null) {
            formFlowDTO.setMonthlyDeadline(linkedHashMap.get("monthlyDeadline").toString());

        } else {
            formFlowDTO.setMonthlyDeadline(null);
        }
        if (linkedHashMap.get("commProt") != null) {
            formFlowDTO.setCommProt(linkedHashMap.get("commProt").toString());

        } else {
            formFlowDTO.setCommProt(null);
        }
        if (linkedHashMap.get("yearlyDeadline") != null) {
            formFlowDTO.setYearlyDeadline(linkedHashMap.get("yearlyDeadline").toString());
        } else {
            formFlowDTO.setYearlyDeadline(null);
        }
        if (linkedHashMap.get("flowType") != null) {
            formFlowDTO.setFlowType(linkedHashMap.get("flowType").toString());
        } else
            formFlowDTO.setFlowType(null);

        if (linkedHashMap.get("flowTableList") != null) {
            ObjectMapper mapper = new ObjectMapper();
            List<FormFlowTableDTO> tableList = new ArrayList<>();
            List<Object> list = (List<Object>) linkedHashMap.get("flowTableList");
            for (Object obj : list) {
                FormFlowTableDTO formFlowTableDTO = mapper.convertValue(obj, FormFlowTableDTO.class);
                tableList.add(formFlowTableDTO);
            }
            formFlowDTO.setFlowTableList(tableList);
        } else
            formFlowDTO.setFlowType(null);
        // TODO Manca versione
        // formFlowDTO.setVersion("prova");
    }
}