package exchanger;

import lombok.Getter;

@Getter
public class NbpExchangeRateResult {
    private NbpExchangeRateSeries series;
    private int responseCode;
    private String errorMessage;
    private String status;

    public NbpExchangeRateResult(NbpExchangeRateSeries series, int responseCode, String errorMessage) {
        this.series = series;
        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
        if (responseCode != 200) this.status = "Fail";
        else this.status = "Succes";
    }
}
