package lab.jotunbane.CurrencyExchanger.exchanger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NbpExchangeRateSeries {
    private String table;
    private String currency;
    private String code;
    private List<NbpExchangeRate> rates;

}
