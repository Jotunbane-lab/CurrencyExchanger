package lab.jotunbane.CurrencyExchanger.exchanger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NbpExchangeRate {

    private String no;
    private XMLGregorianCalendar effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;}



