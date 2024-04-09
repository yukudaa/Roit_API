package com.MTsauRus.lol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@ToString
public class AccountDTO {
    private String puuid;
    private String gameName;
    private String tagLine;

    private List<String> matchIds;

}
