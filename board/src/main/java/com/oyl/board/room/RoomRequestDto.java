package com.oyl.board.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomRequestDto {

    private String roomTitle;

    private String passwd;

    private String startDate;

    private String endDate;

    private String roomId;

    private String roomHost;

    private roomStatus roomStatus;
}
