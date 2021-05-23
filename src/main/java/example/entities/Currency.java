package example.entities;

import example.dto.CurrencyRateFromCBR;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"currencyId", "numCode", "charCode", "nominal", "name"})
@ToString
public class Currency {

    private long id;
    private final String currencyId;
    private final int numCode;
    private final String charCode;
    private final int nominal;
    private final String name;

    public Currency(CurrencyRateFromCBR currencyRateFromCBR) {
        this.currencyId = currencyRateFromCBR.getId();
        this.numCode = currencyRateFromCBR.getNumCode();
        this.charCode = currencyRateFromCBR.getCharCode();
        this.nominal = currencyRateFromCBR.getNominal();
        this.name = currencyRateFromCBR.getName();
    }


}
