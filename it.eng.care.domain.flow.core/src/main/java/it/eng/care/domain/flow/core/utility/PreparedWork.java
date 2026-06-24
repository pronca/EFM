package it.eng.care.domain.flow.core.utility;

import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreparedWork implements Work {
	private static final Logger LOGGER = LoggerFactory.getLogger(PreparedWork.class);
	
    private final String sql;
    private Integer result;

    public PreparedWork(String sql) {
        this.sql = sql;
    }

    public Integer getResult() {
        return result;
    }

    public String getSql() {
        return sql;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                result = 0;
            } catch (SQLException e) {
            	LogUtil.logException(LOGGER, "", e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
