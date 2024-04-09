package com.MTsauRus.lol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LeagueEntryDto {
   private String leagueId;
   private String queueType;
   private String tier;
   private String rank;
   private String summonerId;
   private String summonerName;
   private Long leaguePoints;
   private Long wins;
   private Long losses;
   private boolean veteran;
   private boolean inactive;
   private boolean freshBlood;
   private boolean hotStreak;
}
