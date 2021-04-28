package example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDB {

    private static CurrencyDB instance = new CurrencyDB();

    private HashMap<LocalDate, CurrencyRate> base = new HashMap<>();

    public CurrencyDB() {
        addCurrenciesRate(LocalDate.now().minusDays(3), new CurrencyRate(
                130.37,
                1.1986,
                0.86793,
                90.8921,
                1.4986,
                7.8157));
        addCurrenciesRate(LocalDate.now().minusDays(2), new CurrencyRate(
                131.37,
                1.1996,
                0.86893,
                90.8951,
                1.4966,
                7.8457));
        addCurrenciesRate(LocalDate.now().minusDays(1), new CurrencyRate(
                130.77,
                1.1786,
                0.86593,
                90.8981,
                1.4586,
                7.8177));
    }

    private void addCurrenciesRate(LocalDate date, CurrencyRate rate) {
        base.put(date, rate);
    }

    public static CurrencyDB getInstance() {
        return instance;
    }

    public Map<LocalDate, CurrencyRate> getBase() {
        return base;
    }
}
