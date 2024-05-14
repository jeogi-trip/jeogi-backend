package com.jeogi.jeogitrip.attraction.model.service;


import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Gugun;
import com.jeogi.jeogitrip.attraction.model.SearchRecommend;

import java.util.List;
import java.util.Map;

public interface AttractionService {

    List<Map<Integer, String>> listSido();

    List<Gugun> listGugun(int sidoCode);
    List<Attraction> listAttraction();

    List<Attraction> listRecommendAttraction(SearchRecommend searchRecommend);

    AttractionDescription getAttractionDetail(int contentId);
}
