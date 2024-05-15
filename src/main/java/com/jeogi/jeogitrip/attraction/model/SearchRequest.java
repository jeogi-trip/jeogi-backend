package com.jeogi.jeogitrip.attraction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchRequest {
    private String keyword;
    private int contentTypeId;
}
