package example.dto.parsers;

import example.dto.CurrencyRateFromCBR;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface CurrenciesDTOParser {

    List<CurrencyRateFromCBR> getCurrencies(LocalDate date, String url) throws IOException;

    default String getDateStringValue(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }
}
