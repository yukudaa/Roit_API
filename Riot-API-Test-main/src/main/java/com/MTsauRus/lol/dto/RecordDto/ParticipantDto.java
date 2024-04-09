package com.MTsauRus.lol.dto.RecordDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@ToString
public class ParticipantDto {

    private String championName;
    private int champLevel;
    private int kills;
    private int deaths;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private boolean win;

}
