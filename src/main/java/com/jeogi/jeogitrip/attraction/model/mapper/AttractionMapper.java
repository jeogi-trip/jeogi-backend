package com.jeogi.jeogitrip.attraction.model.mapper;

import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {

    List<Attraction> selectAttraction();

    List<Attraction> selectRecommendAttraction(Search search);

    AttractionDescription selectAttractionDetail(int contentId);
}
