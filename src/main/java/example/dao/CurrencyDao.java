package example.dao;

import example.entities.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {

    Optional<Currency> save(Currency currency);
    List<Currency> getAll();
}
