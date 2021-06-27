package lab.jotunbane.CurrencyExchanger.controller;

import lab.jotunbane.CurrencyExchanger.configuration.CurrencyCodes;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRate;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRateDownloader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.EnumUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@RestController
@RequestMapping
public class Controller {
    private NbpExchangeRate getRates(String currencyCode) {
        NbpExchangeRateDownloader downloader = NbpExchangeRateDownloader.getInstance();
        return downloader.check(currencyCode)
                .getSeries()
                .getRates()
                .get(0);
    }

    @GetMapping("/exchange/{fromCode}/to/{toCode}/amount/{value}")
    //  @GetMapping("/exchanger/usd/from/eur/amount/100")
    public ResponseEntity<?> mainFunctionality(@PathVariable String toCode, @PathVariable String fromCode, @PathVariable BigDecimal value) {
        String codeTo = toCode.toUpperCase();
        String codeFrom = fromCode.toUpperCase();

        if(!Validator(codeTo,codeFrom)){
            return new ResponseEntity<>("Unsupported currencies",HttpStatus.BAD_REQUEST);
        }
        if(value.compareTo(BigDecimal.ZERO)<0){
            return new ResponseEntity<>("Negative value", HttpStatus.BAD_REQUEST);
        }
        NbpExchangeRate ratesTo = new NbpExchangeRate();
        NbpExchangeRate ratesFrom = new NbpExchangeRate();

        if(codeTo.equals("PLN")){
            ratesTo.setAsk(BigDecimal.ONE);
            ratesTo.setBid(BigDecimal.ONE);
        }else ratesTo = getRates(codeTo);

        if(codeFrom.equals("PLN")){
            ratesFrom.setAsk(BigDecimal.ONE);
            ratesFrom.setBid(BigDecimal.ONE);
        }else ratesFrom = getRates(codeFrom);

        BigDecimal finalValue = value.multiply(ratesFrom.getBid())
                .multiply(BigDecimal.valueOf(0.98))
                .divide(ratesTo.getAsk(), RoundingMode.DOWN)
                .multiply(BigDecimal.valueOf(0.98));

        return new ResponseEntity<>(finalValue, HttpStatus.OK);

    }

    private static boolean  Validator(String toCode, String fromCode) {
        String[] codes = {"USD", "EUR", "PLN", "GBP"};
        if (!Arrays.asList(codes).contains(toCode)) return false;
        return Arrays.asList(codes).contains(fromCode);
    }


}
