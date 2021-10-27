package com.oyl.board.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto {

    private String roomTitle;
    private String startDate;
    private String endDate;
    private String roomId;
    private String roomHost;
    private Long id;

    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.roomTitle = room.getRoomTitle();
        this.startDate = room.getStartDate();
        this.endDate = room.getEndDate();
        this.roomId = room.getRoomId();
        this.roomHost = room.getRoomHost();
    }

}
