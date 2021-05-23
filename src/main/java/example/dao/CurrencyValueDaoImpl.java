package example.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@AllArgsConstructor
public class CurrencyValueDaoImpl implements CurrencyValueDao {

    private final Connection connection;

    public String getValue(long dateId, long currencyId) {
        String result = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT rate_value FROM currency_values WHERE " +
                "date_id = (?) AND currency_id = (?)")) {
            statement.setLong(1, dateId);
            statement.setLong(2, currencyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getBigDecimal("rate_value").toString();
                }
                log.info("Value has taken from db to date id: {} and currency id: {} is {}", dateId, currencyId, result);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public void save(Long dateId, long currencyId, String value) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO currency_values" +
                " (date_id, currency_id, rate_value) VALUES ((?), (?), (?))")) {
            statement.setLong(1, dateId);
            statement.setLong(2, currencyId);
            statement.setBigDecimal(3, new BigDecimal(value.replace(",", ".")));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
