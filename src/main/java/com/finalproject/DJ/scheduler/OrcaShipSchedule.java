package com.finalproject.DJ.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finalproject.DJ.dto.Location;
import com.finalproject.DJ.dto.Ship;
import com.finalproject.DJ.service.OrcaSchedulerService;
import com.finalproject.entity.Notransview;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrcaShipSchedule {

    @Autowired
    OrcaSchedulerService oService;
    // 초 분 시간 일 월 요일
    // */5 * * * * * 5초 간격으로 동작
    final String[] BtoP = { "BtoP1", "BtoP2", "BtoPE" };
    final String[] BtoI = { "BtoI1", "BtoI2", "BtoI3", "BtoIE" };
    final String[] BtoU = { "BtoU1", "BtoU2", "BtoUE" };
    final String[] BtoJ = { "BtoJ1", "BtoJ2", "BtoJ3", "BtoJE" };
    final String[] BtoS = { "BtoS1", "BtoS2", "BtoS3", "BtoSE" };
    final String[] UtoB = { "UtoB1", "UtoB2", "UtoBE" };
    final String[] UtoP = { "UtoP1", "UtoP2", "UtoPE" };
    final String[] TtoB = { "TtoB1", "TtoB2", "TtoBE" };
    final String[] JtoB = { "JtoB1", "JtoB2", "JtoB3", "JtoBE" };
    final String[] JtoS = { "JtoS1", "JtoS2", "JtoS3", "JtoSE" };
    final String[] MtoI = { "MtoI1", "MtoI2", "MtoIE" };
    final String[] MtoS = { "MtoS1", "MtoS2", "MtoS3", "MtoS4", "MtoSE" };
    final String[] YtoI = { "YtoI1", "YtoI2", "YtoI3", "YtoIE" };
    final String[] JHtoI = { "JHtoI1", "JHtoI2", "JHtoI3", "JHtoI4", "JHtoIE" };
    final String[] JHtoP = { "JHtoP1", "JHtoP2", "JHtoPE" };
    final String[] SGtoI = { "SGtoI1", "SGtoI2", "SGtoI3", "SGtoIE" };
    final String[] GtoS = { "GtoS1", "GtoS2", "GtoS3", "GtoS4", "GtoSE" };
    final String[] GtoP = { "GtoP1", "GtoP2", "GtoPE" };
    final String[] WtoS = { "WtoS1", "WtoS2", "WtoS3", "WtoS4", "WtoSE" };
    final String[] ItoB = { "ItoB1", "ItoB2", "ItoB3", "ItoBE" };
    final String[] ItoM = { "ItoM1", "ItoM2", "ItoME" };
    final String[] ItoY = { "ItoY1", "ItoY2", "ItoY3", "ItoYE" };
    final String[] ItoJH = { "ItoJH1", "ItoJH2", "ItoJH3", "ItoJH4", "ItoJHE" };
    final String[] ItoSG = { "ItoSG1", "ItoSG2", "ItoSG3", "ItoSGE" };
    final String[] StoM = { "StoM1", "StoM2", "StoM3", "StoM4", "StoME" };
    final String[] StoG = { "StoG1", "StoG2", "StoG3", "StoG4", "StoGE" };
    final String[] StoP = { "StoP1", "StoP2", "StoPE" };
    final String[] StoW = { "StoW1", "StoW2", "StoW3", "StoW4", "StoWE" };
    final String[] StoJ = { "StoJ1", "StoJ2", "StoJ3", "StoJE" };
    final String[] PtoB = { "PtoB1", "PtoB2", "PtoBE" };
    final String[] PtoJH = { "PtoJH1", "PtoJH2", "PtoJHE" };
    final String[] PtoG = { "PtoG1", "PtoG2", "PtoGE" };
    final String[] PtoU = { "PtoU1", "PtoU2", "PtoUE" };
    final String[] PtoS = { "PtoS1", "PtoS2", "PtoSE" };
    final String[] PtoI = { "PtoI1", "PtoI2", "PtoI3", "PtoI4", "PtoIE" };

    public void shiproutine(String srn) {
        Ship ship = oService.shipSelectOne(srn);

        // 선박 추가시 운행 중일때 이동하도록 변환

        if (ship.getState().equals("운행중")) {

            Location location = oService.LocationSelect(ship.getPoint());
            // 부산항 -> 포항항
            if (location.getDepartureport().equals("부산항") && location.getArrivalport().equals("포항항")) {
                for (int i = 0; i < BtoP.length; i++) {
                    ship.setPoint(BtoP[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 부산항 -> 인천항
            if (location.getDepartureport().equals("부산항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < BtoI.length; i++) {
                    ship.setPoint(BtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 부산항 -> 울산항
            if (location.getDepartureport().equals("부산항") && location.getArrivalport().equals("울산항")) {
                for (int i = 0; i < BtoU.length; i++) {
                    ship.setPoint(BtoU[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 부산항 -> 제주항
            if (location.getDepartureport().equals("부산항") && location.getArrivalport().equals("제주항")) {
                for (int i = 0; i < BtoJ.length; i++) {
                    ship.setPoint(BtoJ[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 부산항 -> 속초항
            if (location.getDepartureport().equals("부산항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < BtoS.length; i++) {
                    ship.setPoint(BtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 울산항 -> 부산항
            if (location.getDepartureport().equals("울산항") && location.getArrivalport().equals("부산항")) {
                for (int i = 0; i < UtoB.length; i++) {
                    ship.setPoint(UtoB[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 울산항 -> 포항항
            if (location.getDepartureport().equals("울산항") && location.getArrivalport().equals("포항항")) {
                for (int i = 0; i < UtoP.length; i++) {
                    ship.setPoint(UtoP[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 통영항 -> 부산항
            if (location.getDepartureport().equals("통영항") && location.getArrivalport().equals("부산항")) {
                for (int i = 0; i < TtoB.length; i++) {
                    ship.setPoint(TtoB[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 제주항 -> 부산항
            if (location.getDepartureport().equals("제주항") && location.getArrivalport().equals("부산항")) {
                for (int i = 0; i < JtoB.length; i++) {
                    ship.setPoint(JtoB[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 제주항 -> 부산항
            if (location.getDepartureport().equals("제주항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < JtoS.length; i++) {
                    ship.setPoint(JtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 목포항 -> 인천항
            if (location.getDepartureport().equals("목포항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < MtoI.length; i++) {
                    ship.setPoint(MtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 목포항 -> 인천항
            if (location.getDepartureport().equals("목포항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < MtoS.length; i++) {
                    ship.setPoint(MtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 여수항 -> 인천항
            if (location.getDepartureport().equals("여수항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < YtoI.length; i++) {
                    ship.setPoint(YtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 진해항 -> 인천항
            if (location.getDepartureport().equals("진해항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < JHtoI.length; i++) {
                    ship.setPoint(JHtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 진해항 -> 포항항
            if (location.getDepartureport().equals("진해항") && location.getArrivalport().equals("포항항")) {
                for (int i = 0; i < JHtoP.length; i++) {
                    ship.setPoint(JHtoP[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 서귀포항 -> 인천항
            if (location.getDepartureport().equals("서귀포항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < SGtoI.length; i++) {
                    ship.setPoint(SGtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 광양항 -> 속초항
            if (location.getDepartureport().equals("광양항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < GtoS.length; i++) {
                    ship.setPoint(GtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 광양항 -> 속초항
            if (location.getDepartureport().equals("광양항") && location.getArrivalport().equals("포항항")) {
                for (int i = 0; i < GtoP.length; i++) {
                    ship.setPoint(GtoP[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 완도항 -> 속초항
            if (location.getDepartureport().equals("완도항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < WtoS.length; i++) {
                    ship.setPoint(WtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 인천항 -> 부산항
            if (location.getDepartureport().equals("인천항") && location.getArrivalport().equals("부산항")) {
                for (int i = 0; i < ItoB.length; i++) {
                    ship.setPoint(ItoB[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 인천항 -> 목포항
            if (location.getDepartureport().equals("인천항") && location.getArrivalport().equals("목포항")) {
                for (int i = 0; i < ItoM.length; i++) {
                    ship.setPoint(ItoM[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 인천항 -> 여수항
            if (location.getDepartureport().equals("인천항") && location.getArrivalport().equals("여수항")) {
                for (int i = 0; i < ItoY.length; i++) {
                    ship.setPoint(ItoY[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 인천항 -> 진해항
            if (location.getDepartureport().equals("인천항") && location.getArrivalport().equals("진해항")) {
                for (int i = 0; i < ItoJH.length; i++) {
                    ship.setPoint(ItoJH[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 인천항 -> 서귀포항
            if (location.getDepartureport().equals("인천항") && location.getArrivalport().equals("서귀포항")) {
                for (int i = 0; i < ItoSG.length; i++) {
                    ship.setPoint(ItoSG[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 속초항 -> 목포항
            if (location.getDepartureport().equals("속초항") && location.getArrivalport().equals("목포항")) {
                for (int i = 0; i < StoM.length; i++) {
                    ship.setPoint(StoM[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 속초항 -> 광양항
            if (location.getDepartureport().equals("속초항") && location.getArrivalport().equals("광양항")) {
                for (int i = 0; i < StoG.length; i++) {
                    ship.setPoint(StoG[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 속초항 -> 포항항
            if (location.getDepartureport().equals("속초항") && location.getArrivalport().equals("포항항")) {
                for (int i = 0; i < StoP.length; i++) {
                    ship.setPoint(StoP[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 속초항 -> 완도항
            if (location.getDepartureport().equals("속초항") && location.getArrivalport().equals("완도항")) {
                for (int i = 0; i < StoW.length; i++) {
                    ship.setPoint(StoW[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 속초항 -> 제주항
            if (location.getDepartureport().equals("속초항") && location.getArrivalport().equals("제주항")) {
                for (int i = 0; i < StoJ.length; i++) {
                    ship.setPoint(StoJ[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 부산항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("부산항")) {
                for (int i = 0; i < PtoB.length; i++) {
                    ship.setPoint(PtoB[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 진해항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("진해항")) {
                for (int i = 0; i < PtoJH.length; i++) {
                    ship.setPoint(PtoJH[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 광양항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("광양항")) {
                for (int i = 0; i < PtoG.length; i++) {
                    ship.setPoint(PtoG[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 울산항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("울산항")) {
                for (int i = 0; i < PtoU.length; i++) {
                    ship.setPoint(PtoU[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 속초항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("속초항")) {
                for (int i = 0; i < PtoS.length; i++) {
                    ship.setPoint(PtoS[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }

            // 포항항 -> 속초항
            if (location.getDepartureport().equals("포항항") && location.getArrivalport().equals("인천항")) {
                for (int i = 0; i < PtoI.length; i++) {
                    ship.setPoint(PtoI[i]);
                    oService.shipPointUpdate(ship);
                    try {
                        log.info("point => {}", ship.getPoint());
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int ret = oService.shipStateUpdate(ship.getSrn(), "운행완료");
                log.info("{}", ret);
            }
        }
        log.info("선박정보 => {}", ship);
    }

    // 테스트배등록번호 테스트배이름 컨테이너선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition1() {
        shiproutine("테스트배등록번호");
    }

    // BSR-020419 수성1 화물선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition2() {
        shiproutine("BSR-020419");
    }

    // BSR-040491 수성3 화물선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition3() {
        shiproutine("BSR-040491");
    }

    // BSR-180001 삼표3 화물선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition4() {
        shiproutine("BSR-180001");
    }

    // BSB-922563 세한2호 유조선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition5() {
        shiproutine("BSB-922563");
    }

    // BSR-940415 워터릴리 유조선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition6() {
        shiproutine("BSR-940415");
    }

    // BSR-180025 디엘루비호 부선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition7() {
        shiproutine("BSR-180025");
    }

    // MSB-071211 제103삼일호 부선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition8() {
        shiproutine("MSB-071211");
    }

    // BSR-000025 세한107호 예인선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition9() {
        shiproutine("BSR-000025");
    }

    // BSR-704055 세한108호 예인선
    // @Scheduled(cron = "*/5 * * * * *")
    public void updateShipPosition10() {
        shiproutine("BSR-704055");
    }

    // schedule state 매일 6시 0분 0초
    // @Scheduled(cron = "0 0 6 * * *")    
    public void updateScheduleState() {
        LocalDateTime now = LocalDateTime.now();
        String checkDate = now.toString().substring(0, 10);
        log.info("date => {}", checkDate);
        log.info("date => {}", now.toString());
        List<Notransview> list = oService.DepartureDateCheck();
        for(Notransview obj : list) {
            if(obj.getDeparturedate().equals(checkDate)) {
                oService.shipStateUpdate(obj.getSrn(), "운행중");
                log.info("notransview => {}", obj.getDeparturedate());    
            }
            
        }
        
    }

}
