package com.jeogi.jeogitrip.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Board {
    private int boardId;
    private String userId;
    private String title;
    private int viewCount;
    private String createTime;
    private String content;
}
