package exchanger;

import lombok.Getter;

import java.util.List;
@Getter
public class NbpExchangeRateSeries {
    private String table;
    private String currency;
    private String code;
    private List<NbpExchangeRate> rates;

}
