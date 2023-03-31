package java20migration;

import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DB {
    public static void execute(@Language("H2") String sql, Object... parameters)  {
        try(Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/./h2/java20");
            PreparedStatement statement = connection.prepareStatement(sql);) {
            setPreparedStatement(statement, parameters);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getCount(@Language("H2") String sql, Object... parameters){
        try(Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/./h2/java20");
            PreparedStatement statement = connection.prepareStatement(sql);) {
            setPreparedStatement(statement, parameters);
            try(ResultSet resultSet = statement.executeQuery()) {
                return resultSet.getInt(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void setPreparedStatement(PreparedStatement ps, @NotNull Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            Object value = values[i];
            setPreparedStatement(ps, i, value);
        }
    }

    private static void setPreparedStatement(PreparedStatement ps, int i, Object value) throws SQLException {
        if (value == null) {
            ps.setNull(i + 1, Types.DATE);
        } else if (value instanceof String) {
            ps.setString(i + 1, (String)value);
        } else if (value instanceof Date) {
            ps.setTimestamp(i + 1, new Timestamp(((Date)value).getTime()));
        } else if (value instanceof Double) {
            ps.setDouble(i + 1, (Double) value);
        } else if (value instanceof Integer ) {
            ps.setInt(i + 1, (Integer)value);
        } else if (value instanceof Long) {
            ps.setLong(i + 1, (Long)value);
        } else if (value instanceof BigDecimal ) {
            ps.setBigDecimal(i + 1, (BigDecimal) value);
        } else if (value instanceof Boolean ) {
            ps.setBoolean(i + 1, (Boolean)value);
        } else if (value instanceof LocalDate ) {
            ps.setTimestamp(i + 1, Timestamp.valueOf(((LocalDate)value).atStartOfDay()));
        } else if (value instanceof LocalDateTime ) {
            ps.setTimestamp(i + 1, Timestamp.valueOf((LocalDateTime) value));
        } else {
            throw new IllegalArgumentException("unexpected type:" + value + ": " + value.getClass());
        }
    }

}
