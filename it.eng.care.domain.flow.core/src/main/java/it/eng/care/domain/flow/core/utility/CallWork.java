package it.eng.care.domain.flow.core.utility;

import org.hibernate.jdbc.Work;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallWork implements Work {
    private final String id;
    private final String functionName;
    private Integer result;

    public CallWork(String functionName, String id) {
        this.id = id;
        this.functionName = functionName;
    }

    public Integer getResult() {
        return result;
    }

    public String getId() {
        return id;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        CallableStatement call = null;
        call = connection.prepareCall("{ ? = call " + functionName + "(?) }");
        call.registerOutParameter(1, Types.INTEGER);
        call.setString(2, id);
        call.execute();
        result = call.getInt(1);
    }
}
