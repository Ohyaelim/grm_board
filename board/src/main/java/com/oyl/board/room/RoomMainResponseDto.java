package com.oyl.board.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomMainResponseDto {

    private String roomTitle;
    private String startDate;
    private String endDate;
    private String roomId;

    public RoomMainResponseDto(Room room) {
        this.roomTitle = room.getRoomTitle();
        this.startDate = room.getStartDate();
        this.endDate = room.getEndDate();
        this.roomId = room.getRoomId();
    }

}
