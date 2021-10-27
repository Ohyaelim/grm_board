package com.oyl.board.room;

import com.oyl.board.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {

    private final RestTemplate restTemplate;
    private final RoomRepository roomRepository;


    // 방 개설하기
    @Transactional
    public void createRoom(RoomRequestDto dto) throws ParseException, org.json.simple.parser.ParseException {

        String url = "https://biz-dev-api.gooroomee.com/api/v1/room";

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

        // startDate와 endDate 원하는 포맷으로 출력하자
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss Z", Locale.US);
        String realStartDate = dateFormat.format(transFormat.parse(dto.getStartDate()));
        String realEndDate = dateFormat.format(transFormat.parse(dto.getEndDate()));


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomTitle", dto.getRoomTitle());
        params.add("roomHost", dto.getRoomHost());
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
                    .roomHost(dto.getRoomHost())
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


    @Transactional
    public String enterRoom( String roomId, Member member) throws org.json.simple.parser.ParseException {

        String url = "https://biz-dev-api.gooroomee.com/api/v1/room/user/otp";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomId", roomId);
        params.add("username", member.getNickname());
        params.add("roleId", "participant");
        params.add("apiUserId", member.getEmail());

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


    @Transactional
    public String enterRoomTest(String roomId, Member member) throws org.json.simple.parser.ParseException {
        String url = "https://biz-dev-api.gooroomee.com/api/v1/room/user/otp";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        for (int i=0; i<1500 ; i++){
            log.info("-------------{}--------",i);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("roomId", roomId);
            params.add("username", member.getNickname());
            params.add("roleId", "participant");
            params.add("apiUserId", member.getEmail());

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
        }
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("시간차이(s) : "+secDiffTime);

        return null; // exception 처리해주자
    }

    // TODO 참여자목록 뽑기
    @Transactional
    public JSONObject participantsList(String roomId) throws org.json.simple.parser.ParseException {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://biz-dev-api.gooroomee.com/api/v1/log/room/roomUser")
                .queryParam("type","roomId")
                .queryParam("roomId",roomId);


        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",  "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());

        log.info(response.getBody());
        return jsonObject;
    }


    // TODO 참여자 강퇴 마저 킵고잉
    public void kickUser(String roomId, Member member) throws org.json.simple.parser.ParseException {
        String url = "https://biz-dev-api.gooroomee.com/api/v1/room/user/kick";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomId", roomId);
        params.add("apiUserId", member.getEmail());
//        params.add("apiUserId", "1234");

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
            System.out.println("잘 퇴장");
        }else{
            System.out.println("Request Failed");
        }
    }


    @Transactional
    public void mainPinned(Long id){
        // 메인에 핀이 꽂혀있는 경우
        Room mainRoom = roomRepository.findRoomByIsPinned(Boolean.TRUE);

        // 메인에 핀이 꽂혀 있었다면 핀 빼자
        if (mainRoom != null)
            mainRoom.pinOut();

        Room soonMainRoom = roomRepository.findById(id).orElseThrow();
        soonMainRoom.pinIn();
    }

    // 메인에 핀 꼽힌 아이찾기
    @Transactional
    public RoomResponseDto getMainRoom() {
        Room mainRoom = roomRepository.findRoomByIsPinned(Boolean.TRUE);
        return new RoomResponseDto(mainRoom);
    }

    // 방 삭제
    @Transactional
    public void deleteRoom(Long id) throws org.json.simple.parser.ParseException {
        Room room = roomRepository.findById(id).orElseThrow();
        if (room.getIsDeleted() == Boolean.FALSE)
            deleteRoomApi(room.getRoomId());
            room.deleteRoom();
    }

    @Transactional
    public void deleteRoomApi(String roomId) throws org.json.simple.parser.ParseException {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("roomId", roomId);

        String url = "https://biz-dev-api.gooroomee.com/api/v1/room/" + roomId;

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",  "application/x-www-form-urlencoded; charset=utf-8");
        headers.add("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        String resultCode = (String) jsonObject.get("resultCode");
    }

}
