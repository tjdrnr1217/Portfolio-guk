package com.finalproject.DJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.DJ.dto.Waitingview;
import com.finalproject.DJ.mapper.OrcaWaitingViewMapper;
import com.finalproject.DJ.repository.DJOrcaApplicationRepository;
import com.finalproject.DJ.repository.DJOrcaCargoRepository;
import com.finalproject.DJ.repository.DJOrcaEstimateRepository;
import com.finalproject.DJ.repository.DJOrcaFinalOrderViewRepository;
import com.finalproject.DJ.repository.DJOrcaLastOrderViewRepository;
import com.finalproject.DJ.repository.DJOrcaNotransviewRepository;
import com.finalproject.DJ.repository.DJOrcaOrderTableRepository;
import com.finalproject.DJ.repository.DJOrcaOrderViewRepository;
import com.finalproject.DJ.repository.DJOrcaTransportorderRepository;
import com.finalproject.SG.dto.Client;
import com.finalproject.SG.mapper.OrcaClientMapper;
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
public class OrcaMypageServiceImpl implements OrcaMypageService {

    // jpa Repository
    @Autowired DJOrcaOrderTableRepository orderTableRepository;
    @Autowired DJOrcaEstimateRepository estimateRepository;
    @Autowired DJOrcaCargoRepository cargoRepository;
    @Autowired DJOrcaApplicationRepository applicationRepository;
    @Autowired DJOrcaTransportorderRepository transportorderRepository;
    @Autowired DJOrcaLastOrderViewRepository lastOrderViewRepository;
    @Autowired DJOrcaFinalOrderViewRepository finalOrderViewRepository;
    @Autowired DJOrcaNotransviewRepository notransviewRepository;
    @Autowired DJOrcaOrderViewRepository orderViewRepository;

    // mybatis mapper
    @Autowired OrcaWaitingViewMapper orcaWaitingViewMapper;
    @Autowired OrcaClientMapper clientMapper;

    @Override
    public List<Ordertable> OrderTableList(String id, Pageable pageable) {
        try {
            return orderTableRepository.findAllByIdOrderByIdDesc(id,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Estimate> EstimateTableList(String id, Pageable pageable) {
        try {
            return estimateRepository.findAllByIdOrderByIdDesc(id,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountPaymentState(String state, String id) {
        try {
            return orderTableRepository.countByStateEqualsAndId(state, id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }

    @Override
    public long CountEstimate(String id) {
        try {
            return estimateRepository.countById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long CountCargo(String id) {
        try {
            return cargoRepository.countByClient_id(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long CountOrderTable(String id) {
        try {
            return orderTableRepository.countById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Cargo> CargoTableList(String id, Pageable pageable) {
        try {
            return cargoRepository.findAllByClient_idOrderByClient_idDesc(id, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Application> ApplicationTableList(String id) {
        try {
            return applicationRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Transportorder> TransportorderTableList(String id) {
        try {
            return transportorderRepository.findByApplication_id(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int CacelOrderTable(String orderno) {
        try {
            Ordertable ordertable = orderTableRepository.findById(orderno).orElse(null);
            ordertable.setState("결제취소");
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
    public List<Waitingview> WaitingViewTableList(String id, int page, int end) {
        try {
            return orcaWaitingViewMapper.selectWaitingViewList(id,page,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountWaitingView(String id) {
        try {
            return orcaWaitingViewMapper.countWaitingView(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<LastorderView> LastorderViewTableList(String id, Pageable pageable) {
        try {
            return lastOrderViewRepository.findAllByIdOrderByIdDesc(id, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountLastorder(String id) {
        try {
            return lastOrderViewRepository.countById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long CountLastorderState(String state, String id) {
        try {
            return lastOrderViewRepository.countByStateEqualsAndId(state, id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public FinalorderView FinalOrderViewOne(String orderno) {
        try {
            return finalOrderViewRepository.findById(orderno).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client clientSelectOne(String id) {
        try {
            return clientMapper.selectClientone(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Notransview> NotransViewTableList(String id, Pageable pageable) {
        try {
            return notransviewRepository.findAllByIdOrderByIdDesc(id, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountNoTrans(String id) {
        try {
            return notransviewRepository.countById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long CountNoTransState(String state, String id) {
        try {
            return notransviewRepository.countByStateEqualsAndId(state, id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<OrderView> OrderViewTableList(String id, Pageable pageable) {
        try {
            return orderViewRepository.findAllByIdOrderByIdDesc(id, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountOrderView(String id) {
        try {
            return orderViewRepository.countById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long CountOrderViewState(String state, String id) {
        try {
            return orderViewRepository.countByStateEqualsAndId(state, id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Notransview NoTransViewOne(String orderno) {
        try {
            return notransviewRepository.findById(orderno).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long CountEstimateState(String state, String id) {
        try {
            return estimateRepository.countByStateEqualsAndId(state, id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
