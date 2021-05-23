package example.dao;


import example.entities.RatesDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDate;


@Slf4j
@AllArgsConstructor
public class DateDaoImpl implements DateDao {

    private final Connection connection;

    public long getByDate(LocalDate localDate) {
        long date = 0L;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT date_id FROM DATES WHERE localdate = (?)")) {
            statement.setDate(1, Date.valueOf(localDate));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    date = (resultSet.getLong(1));

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return date;
    }


    public RatesDate save(LocalDate localDate) {
        RatesDate ratesDate = new RatesDate(localDate);
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO DATES (date_id, localdate) VALUES (default , (?))", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(localDate));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getInt(1);
                log.info("DB assign id {} to date {}", id, localDate);
                ratesDate.setId(id);
            } else {
                throw new SQLException("Insertion is failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ratesDate;
    }


}
