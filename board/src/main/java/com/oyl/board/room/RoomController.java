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
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public Page<Room> getRoomList(@PageableDefault(size = 5) Pageable pageable) {
        return roomService.getRoomList(pageable);
    }


    @PostMapping("/enter/{roomId}")
    public String enterRoom(@PathVariable String roomId) throws org.json.simple.parser.ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member  = (Member) authentication.getPrincipal();
        String otp = roomService.enterRoom(roomId, member);
//        String otp = roomService.enterRoomTest(id, member);
        log.info(otp);
        return "https://biz-dev.gooroomee.com/room/otp/"+ otp;
    }


    // TODO 관리자 권한으로 주고 putmapping으로 바꾸자
    @PostMapping("/main/{id}")
    public ResponseEntity mainPinned(@PathVariable Long id) {
        roomService.mainPinned(id);
        return ResponseEntity.ok().build();
    }


    // main에 게시할 웨비나 찾기
    @GetMapping()
    public RoomMainResponseDto getMainRoom () {
        return roomService.getMainRoom();
    }




//    @PostMapping("/user/kick")
//    public ResponseEntity<?> createRoom(PathVariable String id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member  = (Member) authentication.getPrincipal();
//        roomService.kickUser(id,member);
//        return ResponseEntity.ok().build();
//    }
}