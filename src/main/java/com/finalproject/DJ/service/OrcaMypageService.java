package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.dto.Waitingview;
import com.finalproject.SG.dto.Client;
import com.finalproject.entity.Application;
import com.finalproject.entity.Cargo;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.LastorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.OrderView;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Transportorder;

@Service
public interface OrcaMypageService {
    
    // 테이블 리스트 불러오기
    // jpa
    List<Ordertable> OrderTableList(String id, Pageable pageable);

    List<Estimate> EstimateTableList(String id, Pageable pageable);

    List<Cargo> CargoTableList(String id, Pageable pageable);

    List<Application> ApplicationTableList(String id);

    List<Transportorder> TransportorderTableList(String id);

    List<LastorderView> LastorderViewTableList(String id, Pageable pageable);

    List<Notransview> NotransViewTableList(String id, Pageable pageable);

    List<OrderView> OrderViewTableList(String id, Pageable pageable);

    FinalorderView FinalOrderViewOne(String orderno);

    Notransview NoTransViewOne(String orderno);

    // mybatis
    List<Waitingview> WaitingViewTableList(String id, int page, int end);

    Client clientSelectOne(String id);

    // Count 테이블
    // jpa
    long CountPaymentState(String state, String id);

    long CountEstimate(String id);

    long CountCargo(String id);

    long CountOrderTable(String id);

    long CountLastorder(String id);

    long CountLastorderState(String state, String id);

    long CountNoTrans(String id);

    long CountNoTransState(String state, String id);

    long CountOrderView(String id);

    long CountOrderViewState(String state, String id);

    long CountEstimateState(String state, String id);

    // mybatis

    long CountWaitingView(String id);

    // 삭제
    
    int CacelOrderTable(String orderno);


}
