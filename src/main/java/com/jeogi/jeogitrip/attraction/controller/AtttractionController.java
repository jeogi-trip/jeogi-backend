package com.jeogi.jeogitrip.attraction.controller;

import com.jeogi.jeogitrip.attraction.model.Attraction;
import com.jeogi.jeogitrip.attraction.model.AttractionDescription;
import com.jeogi.jeogitrip.attraction.model.Search;
import com.jeogi.jeogitrip.attraction.model.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name="Attraction",description = "관광지 리스트와, 추천 여행지를 나타냅니다.")
@RequestMapping("/api/attraction")
public class AtttractionController {
    private final AttractionService attractionService;

    @Operation(summary = "관광지 목록", description = " 현재 위치에서 n키로 내의 관광지 목록")
    @GetMapping("/list")
    public ResponseEntity<?> listAttraction(){
        try {
            List<Attraction> list = attractionService.listAttraction();
            if(list!= null && !list.isEmpty()){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(list);
            }else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "추천 관광지", description = "추천 관광지를 보여준다.")
    @GetMapping("/recommend")
    public ResponseEntity<?> listRecommendAttraction(@Parameter(required = true) Search search){
        try {
            List<Attraction> list = attractionService.listRecommendAttraction(search);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "관광지 상세정보", description = "관광지의 설명을 보여준다.")
    @GetMapping("/list/{contentId}")
    public ResponseEntity<?> getAttractionDetail(@Parameter(required = true) @PathVariable int contentId){
        try{
            AttractionDescription attraction = attractionService.getAttractionDetail(contentId);
            if(attraction != null){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(attraction);
            }else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch ( Exception e){
            return  exceptionHandling(e);
        }
    }




    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
    }
}
