package com.cashRich.assignment.controller;


import com.cashRich.assignment.entity.User;
import com.cashRich.assignment.service.CoinDataService;
import com.cashRich.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinDataService coinDataService;

    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<String> getCoinData(@RequestParam String symbols, Authentication authentication) {
        User user = userService.login(authentication.getName(), authentication.getCredentials().toString()).orElseThrow();
        String data = coinDataService.getCoinData(symbols, user);
        return ResponseEntity.ok(data);
    }
}
