package example;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CurrencyRateFromCBR {

    private final int numCode;
    private final String charCode;
    private final int nominal;
    private final String name;
    private final String value;


}
