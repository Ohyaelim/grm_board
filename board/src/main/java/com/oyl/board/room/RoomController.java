package com.oyl.board.room;

import com.oyl.board.member.Member;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public Page<Room> getRoomList(@PageableDefault(size = 10) Pageable pageable) {
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
    public RoomResponseDto getMainRoom () {
        return roomService.getMainRoom();
    }


    // 방 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRoom (@PathVariable Long id) throws org.json.simple.parser.ParseException {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }


    // 참여자 목록 뽑기
    @GetMapping("/participants/{roomId}")
    public JSONObject getParticipantsList(@PathVariable String roomId) throws org.json.simple.parser.ParseException {
        return roomService.participantsList(roomId);
    }

//    @PostMapping("/user/kick")
//    public ResponseEntity<?> createRoom(PathVariable String id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member  = (Member) authentication.getPrincipal();
//        roomService.kickUser(id,member);
//        return ResponseEntity.ok().build();
//    }

    // TODO update api 생성
//    @PutMapping("/update")
//    public ResponseEntity updateRoom(@RequestBody RoomRequestDto dto, @PathVariable Long id) throws NotFoundException {
//        roomService.updateRoom(id, dto);
//        return ResponseEntity.ok().build();
//    }
}