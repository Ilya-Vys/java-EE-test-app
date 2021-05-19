package example.dto.parsers;

import example.dto.CurrencyRateFromCBR;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParserWithJSoup implements CurrenciesDTOParser{

    @Override
    public List<CurrencyRateFromCBR> getCurrencies(LocalDate localDate, String url) throws IOException {
        String date = getDateStringValue(localDate);
        List<CurrencyRateFromCBR> rates = new ArrayList<>();
        Document page = Jsoup.parse(new URL(url + date), 500);
        Elements elements = page.getElementsByTag("Valute");
        for (Element element : elements) {
            rates.add(new CurrencyRateFromCBR(
                    element.id()
                    ,Integer.parseInt(element.getElementsByTag("Numcode").text())
                    ,element.getElementsByTag("CharCode").text()
                    ,Integer.parseInt(element.getElementsByTag("NumCode").text())
                    ,element.getElementsByTag("Name").text()
                    ,element.getElementsByTag("Value").text()));
        }
        return rates;
    }
}
