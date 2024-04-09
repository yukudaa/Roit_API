package com.MTsauRus.lol.controller;

import com.MTsauRus.lol.dto.*;
import com.MTsauRus.lol.service.*;

import com.MTsauRus.lol.dto.RecordDto.ParticipantDto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class SearchController {

    private final AccountService accountService;
    private final SummonerService summonerService;
    private final ChampionMasteryService championMasteryService;
    private final ChampionDataService championDataService;
    private final ChampionImgService championImgService;
    private final RecordService recordService;

    @GetMapping("/search")
    public String search(@RequestParam String gameName, @RequestParam("tagLine") String tagLine, Model model) {
        // 1. gameName, tagLine으로 PUUID를 받아온다.
        AccountDTO accountDTO = accountService.getAccountInfomation(gameName, tagLine);
        System.out.println("accountDTO = " + accountDTO);

        // 2. PUUID를 가지고 소환사 정보를 받아온다.
        SummonerDTO summonerDTO = summonerService.getSummonerInformationByPUUID(accountDTO.getPuuid());
        System.out.println("summonerDTO = " + summonerDTO);
        model.addAttribute("summonerDTO", summonerDTO);
        model.addAttribute("profileIconURL", summonerService.getSummonerProfileIcon(summonerDTO.getProfileIconId()));


        // 3. PUUID를 가지고 숙련도 정보를 받아온다.
        List<ChampionMasteryDTO> championMasteryDTOList = championMasteryService.getChampionMasteryInformationsByPUUID(accountDTO.getPuuid(), 5);
        for (ChampionMasteryDTO championMasteryDTOEach : championMasteryDTOList) {
            ChampionDataDTO.ChampionDTO championDTO = championDataService.getChampionInfoByChampionId(Long.toString(
                    championMasteryDTOEach.getChampionId()));

            championMasteryDTOEach.setChampionName(championDTO.getName());
            // 이미지 가져오는 서비스 만들기, 그 서비스로 championMasteryDTOEach에다가 url에 넣어줌
            championMasteryDTOEach.setChampionPortraitImgURL(championImgService.getChampionPortraitImgURLByChampionName(championDTO.getId()));
        }
        model.addAttribute("championMasteryDTOList", championMasteryDTOList);
        System.out.println("championMasteryDTOList = " + championMasteryDTOList);

        // 4. encryptedSummonerId를 가지고 티어 반환
        List<LeagueEntryDto> leagueEntryDtoList = championMasteryService.getUserTear(summonerDTO.getId());
        model.addAttribute("userTearList", leagueEntryDtoList);
        System.out.println("LeagueEntryDtoList = " + leagueEntryDtoList);

        // 5.
//        RecordMatchIdDto RecordMatchIdDtoList = recordService.getMatchId(accountDTO.getPuuid(), 5);
        String[] matchId = recordService.getMatchId(accountDTO.getPuuid(), 5);
        System.out.println("recordMatchIdDto = " + matchId[0]);


        // 6.
        ParticipantDto participantDtoList = recordService.getRecord(matchId[0]);
//        model.addAttribute("participantDtoList", participantDtoList);
        System.out.println("LeagueEntryDtoList = " + participantDtoList);

        return "search";
    }
}
