package com.jeogi.jeogitrip.attraction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Attraction {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readcount;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mlevel;
}
