package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.entity.Client;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Payment;

@Service
public interface OrcaPayMentService {
    
    FinalorderView TransPortCharge(String orderno);

    Notransview allpaylist(String orderno);

    Client clientinfo(String id);

    int OrderTableUpdateState(String orderno);

    List<Ordertable> OrderTableList(Pageable pageable);

    Ordertable OrderTableOne(String orderno);

    long CountOrderTable();

    int insertPaymentTable(Payment payment);

}
