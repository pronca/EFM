package it.eng.care.domain.flow.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlowExportRequestDO is a Querydsl query type for FlowExportRequestDO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlowExportRequestDO extends EntityPathBase<FlowExportRequestDO> {

    private static final long serialVersionUID = -1376057668L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlowExportRequestDO flowExportRequestDO = new QFlowExportRequestDO("flowExportRequestDO");

    public final StringPath aziendeProfiloFlussi = createString("aziendeProfiloFlussi");

    public final SetPath<FlowConfigurationFilterDO, QFlowConfigurationFilterDO> configurationFilters = this.<FlowConfigurationFilterDO, QFlowConfigurationFilterDO>createSet("configurationFilters", FlowConfigurationFilterDO.class, QFlowConfigurationFilterDO.class, PathInits.DIRECT2);

    public final BooleanPath consolidata = createBoolean("consolidata");

    public final DateTimePath<java.util.Date> dateCons = createDateTime("dateCons", java.util.Date.class);

    public final NumberPath<Integer> deleted = createNumber("deleted", Integer.class);

    public final BooleanPath drg = createBoolean("drg");

    public final DateTimePath<java.util.Date> endExtractionDate = createDateTime("endExtractionDate", java.util.Date.class);

    public final StringPath fileTalend = createString("fileTalend");

    protected QFlowDO flow;

    public final SetPath<FlowExportMessageDetailsDO, QFlowExportMessageDetailsDO> flowExportMessageDetails = this.<FlowExportMessageDetailsDO, QFlowExportMessageDetailsDO>createSet("flowExportMessageDetails", FlowExportMessageDetailsDO.class, QFlowExportMessageDetailsDO.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    protected QJobTalendDO jobTalendId;

    public final StringPath randomId = createString("randomId");

    public final StringPath record = createString("record");

    public final StringPath regionValidationStatus = createString("regionValidationStatus");

    public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

    public final StringPath requester = createString("requester");

    public final StringPath schedulingInterval = createString("schedulingInterval");

    public final NumberPath<Integer> schedulingIntervalMinutes = createNumber("schedulingIntervalMinutes", Integer.class);

    public final NumberPath<Integer> schedulingIntervalSeconds = createNumber("schedulingIntervalSeconds", Integer.class);

    public final DateTimePath<java.util.Date> schedulingNextTime = createDateTime("schedulingNextTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> schedulingStartingTime = createDateTime("schedulingStartingTime", java.util.Date.class);

    public final StringPath schedulingType = createString("schedulingType");

    public final DateTimePath<java.util.Date> startExtractionDate = createDateTime("startExtractionDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final SetPath<UploadReturnsRequestDO, QUploadReturnsRequestDO> uploadReturnRequest = this.<UploadReturnsRequestDO, QUploadReturnsRequestDO>createSet("uploadReturnRequest", UploadReturnsRequestDO.class, QUploadReturnsRequestDO.class, PathInits.DIRECT2);

    public final StringPath userCons = createString("userCons");

    public final StringPath validationStatus = createString("validationStatus");

    public final StringPath validationStatusDrl = createString("validationStatusDrl");

    public final SetPath<FlowConfigurationFilterFieldValueDO, QFlowConfigurationFilterFieldValueDO> values = this.<FlowConfigurationFilterFieldValueDO, QFlowConfigurationFilterFieldValueDO>createSet("values", FlowConfigurationFilterFieldValueDO.class, QFlowConfigurationFilterFieldValueDO.class, PathInits.DIRECT2);

    protected QVersionDO version;

    public QFlowExportRequestDO(String variable) {
        this(FlowExportRequestDO.class, forVariable(variable), INITS);
    }

    public QFlowExportRequestDO(Path<? extends FlowExportRequestDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlowExportRequestDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlowExportRequestDO(PathMetadata metadata, PathInits inits) {
        this(FlowExportRequestDO.class, metadata, inits);
    }

    public QFlowExportRequestDO(Class<? extends FlowExportRequestDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.flow = inits.isInitialized("flow") ? new QFlowDO(forProperty("flow")) : null;
        this.jobTalendId = inits.isInitialized("jobTalendId") ? new QJobTalendDO(forProperty("jobTalendId"), inits.get("jobTalendId")) : null;
        this.version = inits.isInitialized("version") ? new QVersionDO(forProperty("version")) : null;
    }

    public QFlowDO flow() {
        if (flow == null) {
            flow = new QFlowDO(forProperty("flow"));
        }
        return flow;
    }

    public QJobTalendDO jobTalendId() {
        if (jobTalendId == null) {
            jobTalendId = new QJobTalendDO(forProperty("jobTalendId"));
        }
        return jobTalendId;
    }

    public QVersionDO version() {
        if (version == null) {
            version = new QVersionDO(forProperty("version"));
        }
        return version;
    }

}

