package exchanger;

import lombok.Getter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
@Getter
public class NbpExchangeRate {

    private String no;
    private XMLGregorianCalendar effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;

}
