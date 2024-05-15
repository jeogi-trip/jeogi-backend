package com.jeogi.jeogitrip.attraction.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SearchAttraction {
    private int sidoCode;
    private int gugunCode;
    private int contentTypeId;
    private int maxItems;
}
