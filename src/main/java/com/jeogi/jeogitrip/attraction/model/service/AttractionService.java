package com.jeogi.jeogitrip.attraction.model.service;


import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Search;

import java.util.List;
import java.util.Map;

public interface AttractionService {

    List<Map<Integer, String>> listSido();
    List<Attraction> listAttraction();

    List<Attraction> listRecommendAttraction(Search search);

    AttractionDescription getAttractionDetail(int contentId);
}
