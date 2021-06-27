package lab.jotunbane.CurrencyExchanger.controller;

import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRate;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRateDownloader;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRateResult;
import lab.jotunbane.CurrencyExchanger.exchanger.NbpExchangeRateSeries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @Mock
    NbpExchangeRate mockedRate;
    @Mock
    NbpExchangeRateDownloader mockedDownloader;
    @Mock
    Controller mockedController;

    @InjectMocks
    Controller controller;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this); }


    @Test
    void ExchangerShouldReturn200Status() {
        // given
        String properCode1 = "EUR";
        String properCode2 = "USD";
        NbpExchangeRateSeries mockedSeries = new NbpExchangeRateSeries();
        Mockito.when(mockedRate.getAsk()).thenReturn(BigDecimal.TEN);
        Mockito.when(mockedRate.getBid()).thenReturn(BigDecimal.TEN);
        Mockito.when(mockedDownloader.check(properCode1)).thenReturn(new NbpExchangeRateResult(new NbpExchangeRateSeries(), 200, null));
        Mockito.when(mockedDownloader.check(properCode2)).thenReturn(new NbpExchangeRateResult(new NbpExchangeRateSeries(), 200, null));
        HttpStatus expected = HttpStatus.OK;

        BigDecimal properAmount = BigDecimal.valueOf(100);
        // when
        HttpStatus result = controller.mainFunctionality(properCode1, properCode2, properAmount).getStatusCode();
        // then
        assertEquals(expected,result);

    }

    @Test
    void ExchangerShouldReturnProperValue() {
        // given
        Mockito.when(mockedRate.getAsk()).thenReturn(BigDecimal.TEN);
        Mockito.when(mockedRate.getBid()).thenReturn(BigDecimal.TEN);
        BigDecimal expected = BigDecimal.valueOf(96.0400D);
        String properCode1 = "pln";
        String properCode2 = "PLN";
        BigDecimal properAmount = BigDecimal.valueOf(100);
        // when
        BigDecimal result = (BigDecimal) controller.mainFunctionality(properCode1, properCode2, properAmount).getBody();
        // then
        assertEquals(expected.stripTrailingZeros(),result.stripTrailingZeros());

    }

    @Test
    void ExchangerShouldReturnProperValueWithDifferentCurrency() {
        // given
        String properCode1 = "EUR";
        String properCode2 = "USD";
        NbpExchangeRateSeries mockedSeries = new NbpExchangeRateSeries();
        Mockito.when(mockedRate.getAsk()).thenReturn(BigDecimal.TEN);
        Mockito.when(mockedRate.getBid()).thenReturn(BigDecimal.TEN);
        Mockito.when(mockedDownloader.check(properCode1)).thenReturn(new NbpExchangeRateResult(new NbpExchangeRateSeries(), 200, null));
        Mockito.when(mockedDownloader.check(properCode2)).thenReturn(new NbpExchangeRateResult(new NbpExchangeRateSeries(), 200, null));
        BigDecimal expected = BigDecimal.valueOf(96.0400D);
        BigDecimal properAmount = BigDecimal.valueOf(100);
        // when
        BigDecimal result = (BigDecimal) controller.mainFunctionality(properCode1, properCode2, properAmount).getBody();
        // then
        assertEquals(expected.stripTrailingZeros(),result.stripTrailingZeros());

    }
}


// given

// when

// then
