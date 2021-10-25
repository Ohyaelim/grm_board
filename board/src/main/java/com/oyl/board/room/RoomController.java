package com.oyl.board.room;

import com.oyl.board.member.Member;
import com.oyl.board.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.text.ParseException;

@Slf4j
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


    @PostMapping("/enter/{id}")
    public String enterRoom(@PathVariable String id) throws org.json.simple.parser.ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member  = (Member) authentication.getPrincipal();
        String otp = roomService.enterRoom(id, member);
//        String otp = roomService.enterRoomTest(id, member);
        log.info(otp);
        return "https://biz-dev.gooroomee.com/room/otp/"+ otp;
    }




//    @PostMapping("/user/kick")
//    public ResponseEntity<?> createRoom(PathVariable String id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member  = (Member) authentication.getPrincipal();
//        roomService.kickUser(id,member);
//        return ResponseEntity.ok().build();
//    }
}