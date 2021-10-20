package com.oyl.board.room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RequiredArgsConstructor
@RestController("/webinar")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody RoomRequestDto requestDto) {
        return roomService.createRoom(requestDto);
    }

    @GetMapping("/list")
    public Object getRoomList() {
        return roomService.getRoomList();
    }

}
