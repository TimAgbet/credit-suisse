package com.credit.tim.logprocessor.helper;

import com.credit.tim.logprocessor.domain.LogStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper implements RowMapper<LogStatus> {
    @Override
    public LogStatus mapRow(ResultSet resultSet, int i) throws SQLException {
        LogStatus logStatus = new LogStatus();
        if (resultSet != null) {
            logStatus.setEventId(resultSet.getString("event_id"));
            logStatus.setHost(resultSet.getString("host"));
            logStatus.setAlert(resultSet.getBoolean("alert"));
            logStatus.setDuration(resultSet.getLong("duration"));
        }
        return logStatus;
    }
}
