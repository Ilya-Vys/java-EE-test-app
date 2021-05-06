package example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDB {

    private static CurrencyDB instance = new CurrencyDB();

    private HashMap<LocalDate, CurrencyRate> base = new HashMap<>();

    public CurrencyDB() {
        addCurrenciesRate(LocalDate.now().minusDays(3), CurrencyRate.builder()
                .japaneseYen(125.22)
                .usDollar(1.1888)
                .poundSterling(0.777)
                .russianRouble(90.13)
                .canadianDollar(1.32)
                .chineseYuan(7.797)
                .build());
        addCurrenciesRate(LocalDate.now().minusDays(2), CurrencyRate.builder()
                .japaneseYen(130.22)
                .usDollar(1.1997)
                .poundSterling(0.917)
                .russianRouble(90.22)
                .canadianDollar(1.4554)
                .chineseYuan(7.7557)
                .build());
        addCurrenciesRate(LocalDate.now().minusDays(1), CurrencyRate.builder()
                .japaneseYen(130.22)
                .usDollar(1.1887)
                .poundSterling(0.88)
                .russianRouble(90.42)
                .canadianDollar(1.64)
                .chineseYuan(7.97)
                .build());
        addCurrenciesRate(LocalDate.now(), CurrencyRate.builder()
                .japaneseYen(120.22)
                .usDollar(1.1777)
                .poundSterling(0.87)
                .russianRouble(90.12)
                .canadianDollar(1.44)
                .chineseYuan(7.77)
                .build());
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
