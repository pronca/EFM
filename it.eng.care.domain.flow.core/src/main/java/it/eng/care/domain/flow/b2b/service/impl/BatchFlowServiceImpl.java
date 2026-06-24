package it.eng.care.domain.flow.b2b.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.eng.care.domain.flow.b2b.model.FlowParameter;
import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder;
import it.eng.care.domain.flow.b2b.model.FlowQueryBuilder.QueryType;
import it.eng.care.domain.flow.b2b.service.BatchFlowService;
import it.eng.care.domain.flow.core.utility.LogUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class BatchFlowServiceImpl implements BatchFlowService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchFlowServiceImpl.class);
	
    @Value("${flow.b2b.maxBatchSize:100}")
    private int maxBatchSize;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager em;

    @Override
    public void executeBatch(List<FlowQueryBuilder> builders) throws SQLException {

        if (builders == null || builders.isEmpty()) {
            return;
        }

        // final per lambda
        final List<FlowQueryBuilder> buildersFinal = builders;
        final int fetchSize = 5000;

        try {
            Session session = em.unwrap(Session.class);
            session.doWork(conn -> {
                // NIENTE: dataSource.getConnection(), setAutoCommit(false), commit(), rollback()
                // qui usi la Connection della transazione Spring/Hibernate

                Map<String, PreparedStatement> queryMap = new HashMap<>();

                try {
                    for (FlowQueryBuilder flowQueryBuilder : buildersFinal) {
                        String queryString = flowQueryBuilder.getQueryStringNoParams();

                        PreparedStatement ps = queryMap.get(queryString);
                        if (ps == null) {
                            ps = conn.prepareStatement(queryString);
                            ps.setFetchSize(fetchSize);
                            queryMap.put(queryString, ps);
                        }

                        // parametri
                        List<FlowParameter> paramList = flowQueryBuilder.getParameters();
                        if (paramList == null) paramList = Collections.emptyList();

                        if (!flowQueryBuilder.getType().equals(QueryType.INSERT)) {
                            paramList = paramList.stream()
                                    .filter(FlowParameter::isPrimaryKey)
                                    .collect(Collectors.toList());
                        }

                        for (int i = 1; i <= paramList.size(); i++) {
                            Object v = paramList.get(i - 1).getValue();

                            if (v instanceof Date d) {
                                ps.setTimestamp(i, d != null ? new Timestamp(d.getTime()) : null);
                            } else if (v instanceof Instant inst) {
                                ps.setTimestamp(i, inst != null ? Timestamp.from(inst) : null);
                            } else if (v == null) {
                                ps.setString(i, null);
                            } else {
                                ps.setString(i, String.valueOf(v));
                            }
                        }

                        ps.addBatch();

                        // opzionale: pulisco i parametri dopo addBatch (bene quando riusi lo stesso PS)
                        ps.clearParameters();
                    }

                    // Ordine esecuzione query come il tuo codice
                    ArrayList<String> keys = new ArrayList<>(queryMap.keySet());
                    keys.sort((o1, o2) -> {
                        String a = o1.toLowerCase();
                        String b = o2.toLowerCase();

                        if (a.startsWith("delete") && b.startsWith("delete")) {
                            if (a.contains("_message")) return 1;
                            return -1;
                        }
                        if (b.startsWith("insert")) return -1;
                        return 1;
                    });

                    for (String key : keys) {
                        PreparedStatement ps = queryMap.get(key);
                        ps.executeBatch();
                        ps.clearBatch();
                    }

                } catch (Exception e) {
                	LogUtil.logException(LOGGER, "Errore executeBatch (JDBC in transazione)", e);
                	
                    // Non facciamo rollback manuale: lo farà Spring a fine metodo (perché @Transactional)
                    throw new RuntimeException("Errore executeBatch (JDBC in transazione)", e);
                } finally {
                    // chiudo TUTTI i PreparedStatement
                    for (PreparedStatement ps : queryMap.values()) {
                        try { ps.close(); } catch (Exception ignore) {}
                    }
                    queryMap.clear();
                }
            });

        } catch (RuntimeException re) {
			LogUtil.logException(LOGGER, "", re);
            // Rispetta la firma throws SQLException
            Throwable cause = re.getCause();
            if (cause instanceof SQLException se) {
                throw se;
            }
            throw new SQLException(re.getMessage(), re);
        }
    }


}
