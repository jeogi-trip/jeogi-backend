package com.jeogi.jeogitrip.attraction.model.service;


import com.jeogi.jeogitrip.attraction.model.*;

import java.util.List;
import java.util.Map;

public interface AttractionService {

    List<Map<Integer, String>> listSido();

    List<Gugun> listGugun(int sidoCode);

    List<Attraction> getAttractionBySearch(SearchAttraction searchAttraction);
    List<Attraction> listAttractionByKeyword(SearchRequest searchRequest);

    List<Attraction> listRecommendAttraction(SearchRecommend searchRecommend);

    List<Attraction> listRecommendAttractionBySidoAndGugun(RecommendRequest recommendRequest);

    AttractionDescription getAttractionDetail(int contentId);
}
