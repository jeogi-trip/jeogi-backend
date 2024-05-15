package com.jeogi.jeogitrip.attraction.model.service;

import com.jeogi.jeogitrip.attraction.model.*;
import com.jeogi.jeogitrip.attraction.model.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AttractionServiceImpl implements AttractionService{

    private final AttractionMapper attractionMapper;

    @Override
    public List<Map<Integer, String>> listSido() {
        return attractionMapper.selectSido();
    }

    @Override
    public List<Gugun> listGugun(int sidoCode) {
        return attractionMapper.selectGugun(sidoCode);
    }

    @Override
    public List<Attraction> getAttractionBySearch(SearchAttraction searchAttraction) {
        return attractionMapper.selectAttractionBySearch(searchAttraction);
    }

    @Override
    public List<Attraction> listAttraction() {
        return attractionMapper.selectAttraction();
    }

    @Override
    public List<Attraction> listRecommendAttraction(SearchRecommend searchRecommend) {
        return attractionMapper.selectRecommendAttraction(searchRecommend);
    }

    @Override
    public AttractionDescription getAttractionDetail(int contentId) {
        return attractionMapper.selectAttractionDetail(contentId);
    }
}
