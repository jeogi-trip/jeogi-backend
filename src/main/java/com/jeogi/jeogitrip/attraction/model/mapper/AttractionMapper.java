package com.jeogi.jeogitrip.attraction.model.mapper;

import com.jeogi.jeogitrip.attraction.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {

    List<Map<Integer, String>> selectSido();

    List<Gugun> selectGugun(int sidoCode);

    List<Attraction> selectAttractionBySearch(SearchAttraction searchAttraction);

    List<Attraction> selectAttractionByKeyword(SearchRequest searchRequest);

    List<Attraction> selectRecommendAttraction(SearchRecommend searchRecommend);

    List<Attraction> selectRecommendAttractionBySidoAndGugun(RecommendRequest recommendRequest);

    AttractionDescription selectAttractionDetail(int contentId);
}
