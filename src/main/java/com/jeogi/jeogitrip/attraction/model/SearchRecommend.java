package com.jeogi.jeogitrip.attraction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchRecommend {
    private double mapxLat;
    private double mapyLon;
    private int range;
    private int maxItems;
}
