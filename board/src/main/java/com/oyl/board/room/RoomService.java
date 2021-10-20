package com.oyl.board.room;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;


@Service
public class RoomService {

    private final RestTemplate restTemplate;

    public RoomService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // 방 개설하기
    public ResponseEntity<Room> createRoom(RoomRequestDto dto){
        String url = "https://biz-dev-api.gooroomee.com/api/v1/room";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // set custom header
        headers.set("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("roomTitle",dto.getRoomTitle())
                .build(false);

        // send POST request
        return restTemplate.postForEntity(url, request, Room.class);

    }

    // 리스트 불러오기
    public Object getRoomList() {
        String url = "https://biz-dev-api.gooroomee.com/api/v1/log/room?date=20211019";

//        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
//                .queryParam("date","20211020")
//                .build(false);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // set custom header
        headers.set("X-GRM-AuthToken", "1aa271b4af192d114c55199a81cc211093b170481d15119584");
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return response;
    }

}
