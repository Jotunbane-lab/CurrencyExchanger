package exchanger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class NbpExchangeRateDownloader {
    private static NbpExchangeRateDownloader instance;

    private NbpExchangeRateDownloader() {
    }

    public static NbpExchangeRateDownloader getInstance() {
        if (instance == null) {
            instance = new NbpExchangeRateDownloader();
        }
        return instance;
    }
    private LocalDate getLastToday(){
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (dayOfWeek) {
            case SUNDAY: return date.minusDays(2L);
            case SATURDAY: return date.minusDays(1L);
            default: return date;
        }
    }

    public NbpExchangeRateResult check(String currencyCode) {
        LocalDate date = getLastToday();
        int responseCode;
        NbpExchangeRateSeries series = null;
        String responseMessage;
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/C/" + currencyCode + "/" + date);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            responseCode = conn.getResponseCode();
            responseMessage = conn.getResponseMessage();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                series = new ObjectMapper().readValue(br.readLine(), NbpExchangeRateSeries.class);

            }
            conn.disconnect();
            return new NbpExchangeRateResult(series, responseCode, responseMessage);


        } catch (
                IOException e) {
            System.out.println(e);
        }
        return null;
    }


}
