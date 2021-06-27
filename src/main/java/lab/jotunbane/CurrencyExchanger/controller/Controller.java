package lab.jotunbane.CurrencyExchanger.controller;

import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRate;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRateDownloader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/exchanger")
public class Controller {
    private NbpExchangeRate getRates(String currencyCode) {
        NbpExchangeRateDownloader downloader = NbpExchangeRateDownloader.getInstance();
        return downloader.check(currencyCode)
                .getSeries()
                .getRates()
                .get(0);
    }

    @GetMapping("/exchanger/{fromCode}/from/{toCode}/amount/{value}/")
//    @GetMapping("/exchanger/usd/from/eur/amount/100")
    public ResponseEntity<BigDecimal> mainFunctionality(@PathVariable String toCode, @PathVariable String fromCode, @PathVariable BigDecimal value) {
        String codeTo = toCode.toUpperCase();
        String codeFrom = fromCode.toUpperCase();
        // if(value.compareTo(BigDecimal.ZERO) < 0){throw IllegalArgumentException;}; //value validator
        //here will be validator for supported currency codes

        NbpExchangeRate ratesTo = getRates(codeTo);
        NbpExchangeRate ratesFrom = getRates(codeFrom);
        BigDecimal finalValue = value.multiply(ratesFrom.getBid())
                .multiply(BigDecimal.valueOf(0.98))
                .divide(ratesTo.getAsk(), RoundingMode.DOWN)
                .multiply(BigDecimal.valueOf(0.98));

        return new ResponseEntity<>(finalValue, HttpStatus.OK);

    }

    @GetMapping("/AA")
    public ResponseEntity<String> shit(){
        String s = "kurwa!";
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


}
