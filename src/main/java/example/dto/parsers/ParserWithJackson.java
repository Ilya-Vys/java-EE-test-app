package example.dto.parsers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import example.dto.CurrencyRateFromCBR;
import example.dto.ValCurs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserWithJackson implements CurrenciesDTOParser{

    @Override
    public List<CurrencyRateFromCBR> getCurrencies(LocalDate localDate, String url) throws IOException {
        String date = getDateStringValue(localDate);
        XmlMapper xmlMapper = new XmlMapper();
        ValCurs valCurs = xmlMapper.readValue(new URL(url + date), ValCurs.class);
        return new ArrayList<>(Arrays.asList(valCurs.getValute()));
    }
}
