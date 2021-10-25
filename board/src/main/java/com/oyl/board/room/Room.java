package com.oyl.board.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_title", length = 80)
    private String roomTitle;

    @Column(name = "room_passwd", length = 50)
    private String passwd;

    // startDate와 endDate는 정해진 형식에 맞추기 위해 우선 String으로 받는다.
    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(nullable = false, name = "room_id")
    private String roomId;

    // 방 삭제 확인
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
