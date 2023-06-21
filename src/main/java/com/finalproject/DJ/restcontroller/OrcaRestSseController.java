package com.finalproject.DJ.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.service.OrcaLocationScheduleService;
import com.finalproject.entity.Ordertable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrcaRestSseController {
    // 접속하는 클라이언트의 정보를 보관할 변수
    private static final Map<String, SseEmitter> clients = new HashMap<>();
    @Autowired OrcaLocationScheduleService locationScheduleService;

    // 클라이언트 접속했을때 수행
    @GetMapping(value = "/api/sse/subscribe")
    public SseEmitter subscribe(@RequestParam(name = "id") String id) {
        // sse 객체 생성
        SseEmitter emitter = new SseEmitter( 1000L * 1200 ); // 1초 * 2분 ( 2분동안 접속 유지 )
        clients.put(id, emitter);

        // 클라이언트 연결 중지 및 완료 되면 clients변수에서 정보 제거
        emitter.onTimeout(() -> clients.remove(id));
        emitter.onCompletion(() -> clients.remove(id));

        return emitter;
    }


    // 클라이언트가 전송을 했을때 수행
    // {"ret":1, "abc":"def"}
    @GetMapping(value = "/api/sse/publish")
    public void publish(@RequestParam(name = "message") String message) {
            // map에 보관된 개수만큼 반복하면 키값을 꺼냄
            for (String id : clients.keySet()) {
                try {
                    // map의 키를 이용해서 value값을 꺼냄
                    SseEmitter emitter = clients.get(id);
                    Ordertable ordertable = locationScheduleService.orderTableSelectOne(message);
                    Ship ship = locationScheduleService.shipSelectOne(ordertable.getShip().getSrn());
                    Location location = locationScheduleService.shipLocationSelect(ship.getPoint());
                    log.info("otb => {}", ordertable.getShip().getSrn());
                    log.info("message => {}", message);
                    log.info("ship => {}", ship.toString());
                    log.info("location => {}", location.toString());
                    // 클라이언트로 메시지전송
                    Map<String, Object> retMap = new HashMap<>();
                    retMap.put("latitude", location.getLatitude());
                    retMap.put("longitude", location.getLongitude());
                    retMap.put("title", location.getPoint());
                    emitter.send(retMap, MediaType.APPLICATION_JSON);
                }
                catch (Exception e) {
                    clients.remove(id);
                }   
            } 

    }
}
