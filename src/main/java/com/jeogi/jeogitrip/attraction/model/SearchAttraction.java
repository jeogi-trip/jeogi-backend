package com.jeogi.jeogitrip.attraction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchAttraction {
    private int sidoCode;
    private int gugunCode;
    private int contentTypeId;
    private int maxItems;
}
