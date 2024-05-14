package com.jeogi.jeogitrip.attraction.model.mapper;

import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Gugun;
import com.jeogi.jeogitrip.attraction.model.SearchRecommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {

    List<Map<Integer, String>> selectSido();

    List<Gugun> selectGugun(int sidoCode);

    List<Attraction> selectAttraction();

    List<Attraction> selectRecommendAttraction(SearchRecommend searchRecommend);

    AttractionDescription selectAttractionDetail(int contentId);
}
