package com.jeogi.jeogitrip.attraction.controller;

import com.jeogi.jeogitrip.attraction.model.*;
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
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name="Attraction",description = "관광지 리스트와, 추천 여행지를 나타냅니다.")
@RequestMapping("/api/attraction")
public class AttractionController {
    private final AttractionService attractionService;

    @Operation(summary = "시도 목록", description = "전체 시도 목록")
    @GetMapping("/sido")
    public ResponseEntity<?> listSido(){
        try {
            List<Map<Integer, String>> list = attractionService.listSido();
            if (list != null && !list.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(list);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "구군 목록", description = "시도에 따른 구군 목록")
    @GetMapping("/sido/{sidoCode}")
    public ResponseEntity<?> getGugunBySido(@Parameter(required = true) @PathVariable int sidoCode){
        try{
            List<Gugun> list = attractionService.listGugun(sidoCode);
            if (list != null && !list.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(list);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "시도, 구군, 타입에 따른 관광지 목록", description = "사용자로부터 선택된 시도, 구군, 타입 코드에 맞는 관광지 목록")
    @GetMapping("/list/search")
    public ResponseEntity<?> getAttractionBySearch(   @Parameter(description = "시도 코드", required = true) @RequestParam Integer sidoCode,
                                                      @Parameter(description = "구군 코드", required = true) @RequestParam Integer gugunCode,
                                                      @Parameter(description = "콘텐츠 타입 ID", required = true) @RequestParam Integer contentTypeId,
                                                      @Parameter(description = "최대 아이템 개수", required = false) @RequestParam(required = false, defaultValue = "10") Integer maxItems){
        try{
            SearchAttraction searchAttraction = new SearchAttraction(sidoCode, gugunCode, contentTypeId,maxItems);
            List<Attraction> list = attractionService.getAttractionBySearch(searchAttraction);
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
    public ResponseEntity<?> listRecommendAttraction(@Parameter(required = true) SearchRecommend searchRecommend){
        try {
            List<Attraction> list = attractionService.listRecommendAttraction(searchRecommend);
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
