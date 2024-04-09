package com.MTsauRus.lol.service;

import com.MTsauRus.lol.dto.AccountDTO;
import com.MTsauRus.lol.dto.RecordDto.ParticipantDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RecordService {

    @Value("${riot.api.key}")
    private String API_KEY;

    // matchId 가져오기
    public String[] getMatchId(String puuid, Integer count) {
        String api_url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + count + "&api_key=" + API_KEY;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                api_url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<String>() {
                }
        );
        String match = response.getBody().replaceAll("\\[|\\]", "");
        String[] matchId = match.split(",");
        return matchId;

    }

    public ParticipantDto getRecord(String matchId) {
        String api_url = "https://asia.api.riotgames.com/lol/match/v5/matches/KR_7001064002?api_key=RGAPI-564cfe3b-b18e-465e-ad16-6f1bd1331e6c";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ParticipantDto> response = restTemplate.exchange(
                api_url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<ParticipantDto>() {
                }
        );
        System.out.println("reponse = " + response );
        ParticipantDto participantDtoList = response.getBody();
        System.out.println("participantDtoList = " + participantDtoList);
        return participantDtoList;

    }


}
