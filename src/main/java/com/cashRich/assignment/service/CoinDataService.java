package com.cashRich.assignment.service;


import com.cashRich.assignment.entity.ApiRequestLog;
import com.cashRich.assignment.entity.User;
import com.cashRich.assignment.repository.ApiRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CoinDataService {

    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    @Value("${coinmarketcap.api.url}")
    private String apiUrl;

    @Autowired
    private ApiRequestLogRepository apiRequestLogRepository;

    public String getCoinData(String symbols, User user) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + "?symbol=" + symbols,
                HttpMethod.GET,
                entity,
                String.class
        );

        ApiRequestLog log = ApiRequestLog.builder()
                .user(user)
                .symbol(symbols)
                .response(response.getBody())
                .timestamp(LocalDateTime.now())
                .build();

        apiRequestLogRepository.save(log);

        return response.getBody();
    }
}
