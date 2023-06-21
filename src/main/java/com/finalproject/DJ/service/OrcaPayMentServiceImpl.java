package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.repository.DJOrcaClientRepository;
import com.finalproject.DJ.repository.DJOrcaFinalOrderViewRepository;
import com.finalproject.DJ.repository.DJOrcaNotransviewRepository;
import com.finalproject.DJ.repository.DJOrcaOrderTableRepository;
import com.finalproject.DJ.repository.DJOrcaPaymentRepository;
import com.finalproject.entity.Client;
import com.finalproject.entity.FinalorderView;
import com.finalproject.entity.Notransview;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Payment;

@Service
public class OrcaPayMentServiceImpl implements OrcaPayMentService {

    @Autowired DJOrcaFinalOrderViewRepository finalOrderViewRepository;
    @Autowired DJOrcaNotransviewRepository notransviewRepository;
    @Autowired DJOrcaClientRepository clientRepository;
    @Autowired DJOrcaOrderTableRepository orderTableRepository;
    @Autowired DJOrcaPaymentRepository paymentRepository;

    @Override
    public Notransview allpaylist(String orderno) {
        try {
            return notransviewRepository.findById(orderno).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client clientinfo(String id) {
        try {
            return clientRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int OrderTableUpdateState(String orderno) {
        try {
            Ordertable ordertable = orderTableRepository.findById(orderno).orElse(null);
            ordertable.setState("결제완료");
            if(orderno != null) {
                orderTableRepository.save(ordertable);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public FinalorderView TransPortCharge(String orderno) {
        try {
            return finalOrderViewRepository.findById(orderno).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ordertable> OrderTableList(Pageable pageable) {
        try {
            return orderTableRepository.findAllByOrderByIdDesc(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountOrderTable() {
        try {
            return orderTableRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int insertPaymentTable(Payment payment) {
        try {
            if(payment != null) {
                paymentRepository.save(payment);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Ordertable OrderTableOne(String orderno) {
        try {
            return orderTableRepository.findById(orderno).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}