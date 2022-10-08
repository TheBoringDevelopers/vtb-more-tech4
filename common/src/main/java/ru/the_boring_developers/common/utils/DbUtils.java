package ru.the_boring_developers.common.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Collection;

import static java.sql.Types.BIGINT;

public class DbUtils {

    private DbUtils() {
    }

    public static String prepareQuestionStatement(Collection args) {
        return args != null ? prepareQuestionStatement(args.size()) : "";
    }

    public static String prepareQuestionStatement(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(sb.length() > 0 ? ", " : "").append("?");
        }
        return sb.toString();
    }

    public static Integer getInteger(ResultSet resultSet, String columnLabel) throws SQLException {
        int result = resultSet.getInt(columnLabel);
        return resultSet.wasNull() ? null : result;
    }

    public static Long getLong(ResultSet resultSet, String columnLabel) throws SQLException {
        long result = resultSet.getLong(columnLabel);
        return resultSet.wasNull() ? null : result;
    }

    public static Double getDouble(ResultSet resultSet, String columnLabel) throws SQLException {
        Double result = resultSet.getDouble(columnLabel);
        return resultSet.wasNull() ? null : result;
    }

    public static void setLong(PreparedStatement preparedStatement, int idx, Long value) throws SQLException {
        if (value != null) {
            preparedStatement.setLong(idx, value);
        } else {
            preparedStatement.setNull(idx, BIGINT);
        }
    }

    public static OffsetDateTime getOffsetDateTime(ResultSet resultSet, String columnLabel) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(columnLabel);
        return timestamp != null ? DateUtil.toOffsetDateTime(timestamp.toLocalDateTime()) : null;
    }
}
