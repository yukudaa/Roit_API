package com.MTsauRus.lol.service;

import com.MTsauRus.lol.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    @Value("${riot.api.key}")
    private String API_KEY;

    public AccountDTO getAccountInfomation(String gameName, String tagLine) {
        String api_url ="https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccountDTO> response = restTemplate.exchange(
                api_url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                AccountDTO.class

        );
        AccountDTO accountDTO = response.getBody();
        return accountDTO;
    }
    //    "puuid": "TpzMbzVobyFhKzClgMlSf3gwag1L1WO52AaxSAbzEvn1ct5FvdYUtPEk20E5kVaWbcVPmG6Ckk0jbA",
    //    "gameName": "그리운 그 시절",
    //    "tagLine": "KR 1"
}
