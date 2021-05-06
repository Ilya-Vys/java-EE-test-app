package example;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CurrencyRate {

    private final double japaneseYen;
    private final double usDollar;
    private final double poundSterling;
    private final double russianRouble;
    private final double canadianDollar;
    private final double chineseYuan;

}
