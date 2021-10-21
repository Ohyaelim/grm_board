package com.oyl.board.room;

import com.oyl.board.member.Member;
import com.oyl.board.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Service
@Slf4j
public class RoomService {

    private final RestTemplate restTemplate;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    public RoomService(RestTemplateBuilder restTemplateBuilder, RoomRepository roomRepository, MemberRepository memberRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.roomRepository = roomRepository;
        this.memberRepository = memberRepository;
    }


    // 방 개설하기
    public void createRoom(RoomRequestDto dto) throws ParseException, org.json.simple.parser.ParseException {

        String url = "https://biz-dev-api.gooroomee.com/api/v1/room";

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // startDate와 endDate 원하는 포맷으로 출력하자
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss Z", Locale.US);
        String realStartDate = dateFormat.format(transFormat.parse(dto.getStartDate()));
        String realEndDate = dateFormat.format(transFormat.parse(dto.getEndDate()));


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomTitle", dto.getRoomTitle());
        params.add("passwd", dto.getPasswd());
        params.add("startDate", realStartDate);
        params.add("endDate", realEndDate);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",  "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);


        String response = restTemplate.postForObject(
                url,
                entity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        String resultCode = (String) jsonObject.get("resultCode");

        // 잘 출력되면
        if(resultCode.equals("GRM_200")){
            JSONObject data = (JSONObject) jsonObject.get("data");
            JSONObject room = (JSONObject) data.get("room");
            String roomId = (String) room.get("roomId");
            roomRepository.save(Room.builder()
                    .roomTitle(dto.getRoomTitle())
                    .passwd(dto.getPasswd())
                    .startDate(realStartDate)
                    .endDate(realEndDate)
                    .roomId(roomId)
                    .build()
            );

            System.out.println(response);
        }else{
            System.out.println("Request Failed");
        }

    }


    public Page<Room> getRoomList (@PageableDefault(size = 5) Pageable pageable) {
        return roomRepository.findAll(pageable);

    }


    public String enterRoom( String roomId, Member member) throws org.json.simple.parser.ParseException {

        String url = "https://biz-dev-api.gooroomee.com/api/v1/room/user/otp";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomId", roomId);
//        params.add("username", member.getNickname());
        params.add("username", "yali");
        params.add("roleId", "participant");
//        params.add("apiUserId", member.getEmail());
        params.add("apiUserId", "1234");

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",  "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        String response = restTemplate.postForObject(
                url,
                entity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        String resultCode = (String) jsonObject.get("resultCode");

        if(resultCode.equals("GRM_200")){
            JSONObject data = (JSONObject) jsonObject.get("data");
            JSONObject roomUserOtp = (JSONObject) data.get("roomUserOtp");
            String otp = (String) roomUserOtp.get("otp");
            return otp;
        }
        return null; // TODO 오류라고 알려주기
    }

}
