package com.jeogi.jeogitrip.attraction.model.service;

import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Search;
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
    public List<Attraction> listAttraction() {
        return attractionMapper.selectAttraction();
    }

    @Override
    public List<Attraction> listRecommendAttraction(Search search) {
        return attractionMapper.selectRecommendAttraction(search);
    }

    @Override
    public AttractionDescription getAttractionDetail(int contentId) {
        return attractionMapper.selectAttractionDetail(contentId);
    }
}
