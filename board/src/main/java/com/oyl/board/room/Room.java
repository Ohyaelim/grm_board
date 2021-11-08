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

    @Column(name = "room_host")
    private String roomHost;

    @Column(name = "room_status")
    @Enumerated(EnumType.STRING)
    private roomStatus roomStatus;

    // 방 삭제 확인
    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;


    // 핀이 꽂혔는지 아닌지 확인
    @Builder.Default
    @Column(name = "is_pinned", nullable = false)
    private Boolean isPinned = Boolean.FALSE;


    // 메인에 꼽힌 핀을 뽑는다.
    public void pinOut() {
        this.isPinned = Boolean.FALSE;
    }

    // 메인에 핀을 꼽는다.
    public void pinIn(){
        this.isPinned = Boolean.TRUE;
    }

    // 방을 삭제한다.
    public void deleteRoom() {
        this.isDeleted = Boolean.TRUE;
    }

    // 방을 수정한다.
    public void update(RoomRequestDto dto) {
        this.roomTitle = dto.getRoomTitle();
        this.roomHost = dto.getRoomHost();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.roomStatus = dto.getRoomStatus();
    }


}
