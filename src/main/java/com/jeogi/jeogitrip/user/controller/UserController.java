package com.jeogi.jeogitrip.user.controller;

import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;
import com.jeogi.jeogitrip.user.model.UserRequest;
import com.jeogi.jeogitrip.user.model.service.UserService;
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
@Controller
@RequiredArgsConstructor
@Tag(name = "User", description = "User 관리")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 목록", description = "회원 정보를 확인한다.")
    @GetMapping("/list")
    public ResponseEntity<?> listUser(){
        try{
            List<User> list = userService.listUser();
            if(list!=null &&!list.isEmpty()){
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

    @Operation(summary = "회원 상세 정보", description = "회원 상세 정보를 확인한다.")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@Parameter(required = true) @PathVariable String userId){
        try{
            UserDetail userDetail = userService.getUserDetail(userId);
            if(userDetail != null){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok().headers(headers).body(userDetail);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            }
        }catch ( Exception e){
            return  exceptionHandling(e);
        }
    }

    @Operation(summary = "회원 가입", description = "회원 가입한다.")
    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@RequestBody(required = true) UserRequest userRequest){
        System.out.println(1);
        try {

            int valid = userService.joinUser(userRequest);
            if(valid > 0) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok("회원 등록 성공");
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 등록에 실패하였습니다.");
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "회원 정보 업데이트", description = "회원 정보를 업데이트한다.")
    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody(required = true) UserRequest userRequest){
        try {
            int valid = userService.updateUser(userRequest);
            if(valid >0){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                return ResponseEntity.ok("회원 업데이트 성공");
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 수정에 실패하였습니다.");
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴한다. 단, 이전 회원가입 기록을 남겨둔다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeUser(@Parameter(required = true)@PathVariable String userId){
        try {
            userService.removeUser(userId);
            List<User> list = userService.listUser();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }





    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
    }
}
