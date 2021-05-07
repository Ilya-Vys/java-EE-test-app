package example;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CurrenciesFromCBR {

    private LocalDate localDate;
    private List<CurrencyRateFromCBR> rates;

    public CurrenciesFromCBR(LocalDate date) {
        this.localDate = date;
        this.rates = new ArrayList<>();
    }

    private Document getPage() throws IOException {
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        return Jsoup.parse(new URL(
                "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date), 500);
    }

    public CurrenciesFromCBR getCurrencies() throws IOException {
        Document page = getPage();
        Elements elements = page.getElementsByTag("Valute");


        for (Element element : elements) {
            rates.add(CurrencyRateFromCBR.builder()
                    .numCode(Integer.parseInt(element.getElementsByTag("NumCode").text()))
                    .charCode(element.getElementsByTag("CharCode").text())
                    .nominal(Integer.parseInt(element.getElementsByTag("NumCode").text()))
                    .name(element.getElementsByTag("Name").text())
                    .value(element.getElementsByTag("Value").text())
                    .build());
        }
        return this;
    }
}
