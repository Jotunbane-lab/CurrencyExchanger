package lab.jotunbane.CurrencyExchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
		System.out.println("App Started");
//		//Some quick manual testing
//		Controller lab.jotunbane.CurrencyExchanger.controller = new Controller();
//		BigDecimal bigDecimal = lab.jotunbane.CurrencyExchanger.controller.mainFunctionality("EUR", "USD", BigDecimal.valueOf(100));
//		System.out.println(bigDecimal);

	}
}
