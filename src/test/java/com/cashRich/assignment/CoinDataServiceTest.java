package com.cashRich.assignment;


import com.cashRich.assignment.entity.ApiRequestLog;
import com.cashRich.assignment.entity.User;
import com.cashRich.assignment.repository.ApiRequestLogRepository;
import com.cashRich.assignment.service.CoinDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoinDataServiceTest {

    @InjectMocks
    private CoinDataService coinDataService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiRequestLogRepository apiRequestLogRepository;

    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    @Value("${coinmarketcap.api.url}")
    private String apiUrl;

    @Test
    public void testGetCoinData() {
        User user = new User();
        String symbols = "BTC,ETH";
        String responseBody = "{\"data\":{}}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        when(restTemplate.exchange(
                apiUrl + "?symbol=" + symbols,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        )).thenReturn(ResponseEntity.ok(responseBody));

        String response = coinDataService.getCoinData(symbols, user);

        verify(apiRequestLogRepository, times(1)).save(any(ApiRequestLog.class));
    }
}

