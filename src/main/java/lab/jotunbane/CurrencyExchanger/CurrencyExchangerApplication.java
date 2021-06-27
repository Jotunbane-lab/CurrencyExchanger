package lab.jotunbane.CurrencyExchanger;

import controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
		System.out.println("App Started");

		Controller controller = new Controller();
		System.out.println(controller.getAsk("USD"));
	}

}
