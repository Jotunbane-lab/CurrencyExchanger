package lab.jotunbane.CurrencyExchanger;

import controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
		System.out.println("App Started");
//		Some quick manual testing
//		Controller controller = new Controller();
//		BigDecimal bigDecimal = controller.mainFunctionality("EUR", "USD", BigDecimal.valueOf(100));
//		System.out.println(bigDecimal);

	}

}
