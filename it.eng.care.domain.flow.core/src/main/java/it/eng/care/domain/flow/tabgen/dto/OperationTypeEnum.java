package it.eng.care.domain.flow.tabgen.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlEnum
@XmlAccessorType(XmlAccessType.FIELD)
public enum OperationTypeEnum implements Serializable {

    LOADING_DATA_TO_SHEET_EXCEL, //
    DELETING_TABLE, //
    DELETING_FIELD, //
    DELETING_VALUE, //
    SAVING_UPDATING_TABLE, //
    SAVING_UPDATING_FIELD, //
    SAVING_UPDATING_VALUE, //
    SAVING_TRANSCODE_TABLE, //
    LOADING_DATA_CODING_ERROR
}
