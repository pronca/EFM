package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "FM_FLOW_EXP_FILE_TALEND")
public class FlowExportReqFileTalendDO implements IBaseEntity {

    @Id
    @Column(name = "ID")
    private String id;
    @Lob
    @Column(name = "XML")
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] xml;
    @Lob
    @Column(name = "log")
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] log;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTRACTION_ID")
    private FlowExportRequestDO flowExportRequest;
    @Column(name = "FILE_EXT")
    private String fileExt;
    @Column(name = "COD_REG")
    private String codReg;
    @Column(name = "SEZ_APP")
    private String sezApp;
    @Column(name = "NOME_FILE")
    private String nomeFile;
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public byte[] getXml() {
        return xml;
    }

    public void setXml(byte[] xml) {
        this.xml = xml;
    }

    public byte[] getLog() {
        return log;
    }

    public void setLog(byte[] log) {
        this.log = log;
    }

    public FlowExportRequestDO getFlowExportRequest() {
        return flowExportRequest;
    }

    public void setFlowExportRequest(FlowExportRequestDO flowExportRequest) {
        this.flowExportRequest = flowExportRequest;
    }

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getCodReg() {
		return codReg;
	}

	public void setCodReg(String codReg) {
		this.codReg = codReg;
	}

	public String getSezApp() {
		return sezApp;
	}

	public void setSezApp(String sezApp) {
		this.sezApp = sezApp;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
    
    
}
	
