package example.dto;

import example.dto.parsers.CurrenciesDTOParser;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CurrenciesFromCBR {

    private LocalDate localDate;
    private List<CurrencyRateFromCBR> rates;

    public CurrenciesFromCBR(LocalDate date, String url, CurrenciesDTOParser parser) throws IOException {
        this.localDate = date;
        this.rates = parser.getCurrencies(date, url);
    }

    public CurrenciesFromCBR(LocalDate localDate, List<CurrencyRateFromCBR> rates) {
        this.localDate = localDate;
        this.rates = rates;
    }

    public CurrenciesFromCBR getCurrencies2(){
        return this;
    }

   /* private Document getPage() throws IOException {
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        return Jsoup.parse(new URL(
                "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date), 500);
    }

    public CurrenciesFromCBR getCurrencies() throws IOException {
        getValCurs();
        Document page = getPage();
        Elements elements = page.getElementsByTag("Valute");


        for (Element element : elements) {
          *//*  rates.add(CurrencyRateFromCBR.builder()
                    .id(element.id())
                    .numCode(Integer.parseInt(element.getElementsByTag("NumCode").text()))
                    .charCode(element.getElementsByTag("CharCode").text())
                    .nominal(Integer.parseInt(element.getElementsByTag("NumCode").text()))
                    .name(element.getElementsByTag("Name").text())
                    .value(element.getElementsByTag("Value").text())
                    .build());*//*
            rates.add(new CurrencyRateFromCBR(
                    element.id()
                    ,Integer.parseInt(element.getElementsByTag("Numcode").text())
                    ,element.getElementsByTag("CharCode").text()
                    ,Integer.parseInt(element.getElementsByTag("NumCode").text())
                    ,element.getElementsByTag("Name").text()
                    ,element.getElementsByTag("Value").text()));
        }

        return this;
    }

    private void getValCurs() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        *//*URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        StringBuilder builder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }
        in.close();
        String xml = builder.toString();*//*
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
       *//* CollectionType valuteListType = xmlMapper.getTypeFactory().constructCollectionType(List.class, Valute.class);
        List<Valute> valutes = xmlMapper.readValue(
                new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date), valuteListType);
        valutes.forEach(System.out::println);*//*

        ValCurs vals = xmlMapper.readValue(
                new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date), ValCurs.class);
        System.out.println(vals);
    }*/
}
