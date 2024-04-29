package com.jeogi.jeogitrip.attraction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AttractionDescription {
    private int contentId;
    private String homepage;
    private String overview;
    private String telname;
}
