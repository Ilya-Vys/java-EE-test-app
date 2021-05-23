package example.dao;

import example.entities.Currency;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CurrencyDaoImpl implements CurrencyDao {

    private final Connection connection;

    public Optional<Currency> save(Currency currency) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO currencies (currency_cbr_id, currency_num_code, currency_char_code, currency_nominal," +
                        "currency_name) VALUES ((?), (?), (?), (?), (?))")) {
            statement.setString(1, currency.getCurrencyId());
            statement.setInt(2, currency.getNumCode());
            statement.setString(3, currency.getCharCode());
            statement.setInt(4, currency.getNominal());
            statement.setString(5, currency.getName());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                currency.setId(id);
            } else {
                throw new SQLException("Insertion is failed");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(currency);
    }

    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM currencies")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    currencies.add(new Currency(resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getString(6)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currencies;
    }
}