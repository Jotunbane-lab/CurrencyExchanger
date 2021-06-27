package controller;

import exchanger.NbpExchangeRateDownloader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchanger")
public class Controller {
    public BigDecimal getAsk(String currencyCode){
        NbpExchangeRateDownloader downloader = NbpExchangeRateDownloader.getInstance();
        return downloader.check(currencyCode)
                .getSeries()
                .getRates()
                .get(0)
                .getAsk();
    }
    public BigDecimal getBid(String currencyCode){
        NbpExchangeRateDownloader downloader = NbpExchangeRateDownloader.getInstance();
        return downloader.check(currencyCode)
                .getSeries()
                .getRates()
                .get(0)
                .getBid();
    }
}
