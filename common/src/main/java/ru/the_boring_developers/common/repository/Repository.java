package ru.the_boring_developers.common.repository;

import org.apache.commons.lang3.math.NumberUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public interface Repository {

    /**
     * Запись значения, которое может быть null
     * @param ps PreparedStatement
     * @param idx позиция
     * @param value значение типа Integer
     * @throws SQLException ошибка
     */
    default void setNullable(PreparedStatement ps, int idx, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(idx, value);
        } else {
            ps.setNull(idx, Types.INTEGER);
        }
    }

    /**
     * Запись значения, которое может быть null
     * @param ps PreparedStatement
     * @param idx позиция
     * @param value значение типа Long
     * @throws SQLException ошибка
     */
    default void setNullable(PreparedStatement ps, int idx, Long value) throws SQLException {
        if (value != null) {
            ps.setLong(idx, value);
        } else {
            ps.setNull(idx, Types.BIGINT);
        }
    }

    default void setNullable(PreparedStatement ps, int idx, Double value) throws SQLException {
        if (value != null) {
            ps.setDouble(idx, value);
        } else {
            ps.setNull(idx, Types.NUMERIC);
        }
    }

    /**
     * Запись значения, которое может быть null
     * @param ps PreparedStatement
     * @param idx позиция
     * @param value значение типа String
     * @throws SQLException ошибка
     */
    default void setNullableNumberString(PreparedStatement ps, int idx, String value) throws SQLException {
        if (NumberUtils.isDigits(value)) {
            ps.setString(idx, value);
        } else {
            ps.setNull(idx, Types.VARCHAR);
        }
    }

    /**
     * Запись значения, которое может быть null
     * @param ps PreparedStatement
     * @param idx позиция
     * @param value значение типа String приведенное к Long
     * @throws SQLException ошибка
     */
    default void setNullableLong(PreparedStatement ps, int idx, String value) throws SQLException {
        if (NumberUtils.isDigits(value)) {
            ps.setLong(idx, Long.parseLong(value));
        } else {
            ps.setNull(idx, Types.BIGINT);
        }
    }

    default String prepareUtf8(String value) {
        return value != null ? value.replaceAll("\\p{C}", "") : "";
    }
}
