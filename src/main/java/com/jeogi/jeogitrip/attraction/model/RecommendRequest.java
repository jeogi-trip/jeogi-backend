package com.jeogi.jeogitrip.attraction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecommendRequest {
    private int sidoCode;
    private int gugunCode;
    private int range;
}
