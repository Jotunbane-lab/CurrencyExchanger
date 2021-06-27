package lab.jotunbane.CurrencyExchanger;

import lab.jotunbane.CurrencyExchanger.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyExchangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangerApplication.class, args);
        System.out.println("App Started");
    }
}
