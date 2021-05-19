package example.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CurrencyRateFromCBR {


    @JacksonXmlProperty(localName = "ID")
    private String id;
    @JacksonXmlProperty(localName = "NumCode")
    private int numCode;
    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;
    @JacksonXmlProperty(localName = "Nominal")
    private int nominal;
    @JacksonXmlProperty(localName = "Name")
    private String name;
    @JacksonXmlProperty(localName = "Value")
    private String value;


}
