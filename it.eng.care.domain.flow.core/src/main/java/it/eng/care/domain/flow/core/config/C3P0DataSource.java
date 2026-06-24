package it.eng.care.domain.flow.core.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import it.eng.care.domain.flow.core.dto.DataSourceDTO;
import it.eng.care.domain.flow.core.utility.LogUtil;

public class C3P0DataSource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(C3P0DataSource.class);
	
	private static final Logger LOG = LoggerFactory.getLogger(C3P0DataSource.class);

    // 1 pool per "chiave datasource"
    private static final ConcurrentHashMap<String, C3P0DataSource> POOLS = new ConcurrentHashMap<>();

    private final ComboPooledDataSource combo;

    private C3P0DataSource(DataSourceDTO dto) {
        try {
            combo = new ComboPooledDataSource();

            if ("MySQL".equalsIgnoreCase(dto.getDriver().getName().trim())) {
                combo.setDriverClass("com.mysql.jdbc.Driver");
                combo.setJdbcUrl("jdbc:mysql://" + dto.getHostname() + ":" + dto.getPort() + "/" + dto.getServiceName()
                        + "?autoReconnect=true&useSSL=false");
            } else if ("Oracle".equalsIgnoreCase(dto.getDriver().getName().trim())) {
                combo.setDriverClass("oracle.jdbc.OracleDriver");
                combo.setJdbcUrl("jdbc:oracle:thin:@(DESCRIPTION="
                        + "(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=" + dto.getHostname() + ")(PORT=" + dto.getPort() + ")))"
                        + "(CONNECT_DATA=(SID=" + dto.getServiceName() + ")))");
            } else {
                throw new IllegalArgumentException("Driver non gestito: " + dto.getDriver().getName());
            }
            
            combo.setUser(dto.getUsername());
            combo.setPassword(dto.getPassword());

            LOG.info("C3P0 datasource URL: {}", combo.getJdbcUrl());

        } catch (PropertyVetoException e) {
        	LogUtil.logException(LOGGER, "", e);
        	
            throw new RuntimeException(e);
        }
    }

    private static String key(DataSourceDTO dto) {
        // chiave stabile: driver|host|port|service|user
        return (dto.getDriver().getName() + "|" + dto.getHostname() + "|" + dto.getPort() + "|" + dto.getServiceName() + "|" + dto.getUsername()).toLowerCase();
    }

    public static C3P0DataSource getInstance(DataSourceDTO dto) {
        return POOLS.computeIfAbsent(key(dto), k -> new C3P0DataSource(dto));
    }

    public Connection getConnection() throws SQLException {
        return combo.getConnection();
    }

    // opzionale: da chiamare in shutdown app
    public void close() {
        try {
            combo.close();
        } catch (Exception ignore) {}
    }

}


