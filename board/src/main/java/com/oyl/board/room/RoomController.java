package com.oyl.board.room;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/webinar")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequestDto requestDto) throws ParseException, org.json.simple.parser.ParseException {
        roomService.createRoom(requestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public Page<Room> getRoomList(@PageableDefault(size = 5) Pageable pageable) {
        return roomService.getRoomList(pageable);
    }


}
