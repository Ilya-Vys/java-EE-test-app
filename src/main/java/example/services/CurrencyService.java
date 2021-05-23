package example.services;

import example.dao.*;
import example.dto.CurrenciesFromCBR;
import example.dto.CurrencyRateFromCBR;
import example.dto.parsers.CurrenciesDTOParser;
import example.dto.parsers.ParserWithJackson;
import example.entities.Currency;
import example.entities.RatesDate;
import example.utils.ConnectionManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
public class CurrencyService {
    private static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private final DateDao dateDao;
    private final CurrencyDao currencyDao;
    private final CurrencyValueDao currencyValueDao;
    private final CurrenciesDTOParser parser;

    public CurrencyService() throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionManager.fromResourceProperties("/db.properties").getConnection();
        this.dateDao = new DateDaoImpl(connection);
        this.currencyDao= new CurrencyDaoImpl(connection);
        this.currencyValueDao = new CurrencyValueDaoImpl(connection);
        this.parser = new ParserWithJackson();
        if (currencyDao.getAll().isEmpty()){
            log.info("Currencies table is empty. Fill table");
            fillCurrencyTable();
        }
    }

    public CurrenciesFromCBR getCurrencies(LocalDate localDate) throws IOException {
        if(dateDao.getByDate(localDate) != 0L){
            log.info("Take currency rates from DB");
            return getFromDB(localDate);
        }else {
            log.info("Take currency rates from CB RF");
            CurrenciesFromCBR fromCBR = getFromCBRF(localDate);
            saveCurrenciesIntoDb(fromCBR);
            return fromCBR;

        }
    }

    @SneakyThrows
    private void fillCurrencyTable(){
        getFromCBRF(LocalDate.now()).getRates().stream()
                .map(Currency::new)
                .forEach(currencyDao::save);
    }

    private CurrenciesFromCBR getFromDB(LocalDate localDate){
        long date = dateDao.getByDate(localDate);
        List<Currency> currencies = currencyDao.getAll();
        List<CurrencyRateFromCBR> result = new ArrayList<>();
        for (Currency currency : currencies) {
            result.add(new CurrencyRateFromCBR(
                    currency.getCurrencyId(),
                    currency.getNumCode(),
                    currency.getCharCode(),
                    currency.getNominal(),
                    currency.getName(),
                    currencyValueDao.getValue(date, currency.getId())
            ));
        }
        return new CurrenciesFromCBR(localDate, result);
    }

    private CurrenciesFromCBR getFromCBRF(LocalDate localDate) throws IOException {
            return new CurrenciesFromCBR(localDate, URL, parser);

    }

    private void saveCurrenciesIntoDb(CurrenciesFromCBR currencies){
        log.info("Save {} currency rates into DB", currencies.getLocalDate());

        Map<String, String> map = currencies.getRates().stream()
                .collect(Collectors.toMap(CurrencyRateFromCBR::getId, CurrencyRateFromCBR::getValue));
        long dateId = dateDao.save(currencies.getLocalDate()).getId();
        currencyDao.getAll().forEach(currency ->
                currencyValueDao.save(dateId, currency.getId(), map.get(currency.getCurrencyId())));
    }
}
