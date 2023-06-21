package com.finalproject.SG.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.JH.service.JHApplicationService;
import com.finalproject.JH.service.JHShipService;
import com.finalproject.SG.service.CarService;
import com.finalproject.SG.service.CargoService;
import com.finalproject.SG.service.EstimateService;
import com.finalproject.SG.service.LandtransportationService;
import com.finalproject.SG.service.OrdertableService;
import com.finalproject.SG.service.ScheduleService;
import com.finalproject.SG.service.ShipService;
import com.finalproject.SG.service.TransportorderService;
import com.finalproject.common.uuidnum;
import com.finalproject.entity.Application;
import com.finalproject.entity.Cargo;
import com.finalproject.entity.Estimate;
import com.finalproject.entity.Landtransportation;
import com.finalproject.entity.Ordertable;
import com.finalproject.entity.Schedule;
import com.finalproject.entity.Ship;
import com.finalproject.entity.Transportorder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class finalOrderController {

    final ScheduleService sService;
    final HttpSession httpSession;
    final ShipService shipService;
    final CarService carService;
    final EstimateService estimateService;
    final OrdertableService ordertableService;
    final CargoService cargoService;
    final ScheduleService scheduleService;
    final JHApplicationService applicationService;
    final TransportorderService tpService;
    final LandtransportationService landService;
    @Autowired JHShipService jhShipService;

    @Autowired
    uuidnum uuidnum;

    @GetMapping(value = "/admin/ordertable.do")
    public String ordertableGET(
            Model model,
            @AuthenticationPrincipal User user) {
        List<Ordertable> list = ordertableService.ordertableselect();
        List<Transportorder> tlist = tpService.selectall();

        model.addAttribute("list", list);
        model.addAttribute("tlist", tlist);
        model.addAttribute("user", user);
        return "/SG/admin/ordertable";

    }

    // 선박배정
    @GetMapping(value = "/admin/shipassignment.do")
    public String shipassignmentGET(Model model, @AuthenticationPrincipal User user,
            @RequestParam(name = "estimateno", defaultValue = "", required = false) BigInteger estimateno,
            @ModelAttribute Ship ship,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page) {

        try {
            if (page == 0) {
                return "redirect:/orca/admin/shipassignment.do?page=1"
                        + "&estimateno=" + estimateno;
            }

            Optional<Estimate> estimate = estimateService.selectlistone(estimateno);
            // Optional<Cargo> cargo = cargoRepository.findById(estimate.get().getCargo().getCargonumber());//
            Optional<Cargo> cargo = cargoService.selectOne(estimate.get().getCargo().getCargonumber());
            // Optional<Schedule> schedule = scheduleRepository.findById(estimate.get().getSchedule().getScheduleno());//
            Optional<Schedule> schedule = scheduleService.selectone(estimate.get().getSchedule().getScheduleno());//

            String departureport = schedule.get().getDepartureport();

            String arrivalport = schedule.get().getArrivalport();

            String cargotype = cargo.get().getCargotype();
            BigInteger cargoweight = cargo.get().getCargoweight();
            BigInteger totalfare = estimate.get().getTotalfare();
            String locationtype = cargo.get().getLocationtype();

            log.info("seta rtreta => {} ", locationtype);

            long total = shipService.total();
            model.addAttribute("pages", (total - 1) / 5 + 1);

            PageRequest pageRequest = PageRequest.of((page - 1), 5);
            List<Ship> list = jhShipService.selectShipPage(pageRequest);
            String id = estimate.get().getId();
            model.addAttribute("list", list);

            model.addAttribute("scheduleno", estimate.get().getSchedule().getScheduleno());
            model.addAttribute("totalfare", totalfare);
            model.addAttribute("id", id);
            model.addAttribute("user", user);
            model.addAttribute("estimateno", estimateno);
            model.addAttribute("departureport", departureport);
            model.addAttribute("arrivalport", arrivalport);
            model.addAttribute("cargotype", cargotype);
            model.addAttribute("cargoweight", cargoweight);
            model.addAttribute("locationtype", locationtype);
            return "/SG/admin/ship_arrangement";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/home.do";
        }

    }

    // 선박배정
    @PostMapping(value = "/admin/shipassignment.do")
    public String shipassignmentPOST(Model model,
            @ModelAttribute Estimate estimate, @ModelAttribute Ship ship, @ModelAttribute Schedule schedule,
            @RequestParam(name = "srn") String srn,
            @RequestParam(name = "cargoweight") BigInteger cargoweight,
            @RequestParam(name = "estimateno") BigInteger estimateno) {
        try {

             // 최대적재량 무게 계산
             Optional<Ship> obj = shipService.selectoneShip(srn);
             BigInteger max = obj.get().getMaxcargo();
             int total = max.intValue() - cargoweight.intValue();
             BigInteger.valueOf(total);
 
             log.info("{}", BigInteger.valueOf(total));
             
            String id = estimate.getId();
            String departureport = schedule.getDepartureport();
            String arrivalport = schedule.getArrivalport();
            BigInteger totalfare = estimate.getCargo().getCargoweight().multiply(BigInteger.valueOf(178))
                    .add(estimate.getTotalfare());
            // BigInteger estimateno = estimate.getEstimateno();
            // String srn = ship.getSrn();
            String uuid = uuidnum.uuidString();

            Ordertable ordertable = new Ordertable();
            ordertable.setOrderno(uuid);
            ordertable.setId(id);
            ordertable.setDepartureport(departureport);
            ordertable.setArrivalport(arrivalport);
            ordertable.setTotalfare(totalfare);
            ordertable.setEstimate(estimate);
            ordertable.setShip(ship);
            ordertable.setState("결제대기");

            obj.get().setMaxcargo(BigInteger.valueOf(total));


            // 상태변화 완료
            Optional<Estimate> obj2 = estimateService.selectlistone(estimateno);
            log.info("{}", obj2.get().getState());
            obj2.get().setState("완료");

            long ret = ordertableService.count(estimate.getEstimateno());//

            log.info("dfsdraea => {}", ret);

            if (ordertableService.count(estimate.getEstimateno()) == 0) {//
                Ordertable ot = ordertableService.ordertablesave(ordertable);

                if (estimate.getCargo().getLocationtype().equals("야적장")) {
                    return "redirect:/orca/admin/ordertable.do";
                } else {
                    if (ot != null) {
                        return "redirect:/orca/admin/car_assignment.do?orderno=" + ot.getOrderno();
                    }
                }
            }
            return "redirect:/orca/admin/ordertable.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/home.do";
        }
    }

    // 차량배정
    @GetMapping(value = "/admin/car_assignment.do")
    public String cararrangementGET(Model model, 
                                    @RequestParam(name = "orderno") String orderno,
                                    @AuthenticationPrincipal User user) {

        // Optional<Ordertable> list = otRepository.findById(orderno);//
        Optional<Ordertable> list = ordertableService.selectone(orderno);
        BigInteger cargonumber = list.get().getEstimate().getCargo().getCargonumber();
        List<Application> alist = applicationService.findcargonumber(cargonumber);
        String destination = alist.get(0).getDestination().getDestinationname();
        BigInteger cargoweight = list.get().getEstimate().getCargo().getCargoweight();
        BigInteger transportcharge = cargoweight.multiply(BigInteger.valueOf(40000));
        String cargotype = list.get().getEstimate().getCargo().getCargotype();
        BigInteger applicationno = alist.get(0).getApplicationno();

        List<Landtransportation> landlist = landService.selectall();
        model.addAttribute("list", landlist);
        model.addAttribute("cargotype", cargotype);
        model.addAttribute("cargoweight", cargoweight);
        model.addAttribute("destination", destination);
        model.addAttribute("transportcharge", transportcharge);
        model.addAttribute("applicationno", applicationno);
        model.addAttribute("user", user);
  
        log.info("DSFdfsd => {} ", destination);

        return "/SG/admin/car_assignment";
    }

    @PostMapping(value = "/admin/car_assignment.do")
    public String cararrangementPOST(
            @RequestParam(name = "application.applicationno") BigInteger applicationno,
            @RequestParam(name = "destination") String destination,
            @RequestParam(name = "transportcharge") BigInteger transportcharge,
            @RequestParam(name = "number",defaultValue = "") String number,
            @RequestParam(name = "cargoweight") BigInteger cargoweight,
            @RequestParam(name = "weight") BigInteger weight,
            @ModelAttribute Landtransportation landtransportation) {

        try {
            if (tpService.countapplicationno(applicationno) == 0) {//

                int total = weight.intValue() - cargoweight.intValue();
                log.info("{}", BigInteger.valueOf(total));
                
                String invoicenumber = uuidnum.uuidString();
                // String number=landtransportation.getNumber();
                Application app = new Application();
                app.setApplicationno(applicationno);
                log.info("ㅅ=>{}",app);
                

                Landtransportation car = new Landtransportation();
                car.setNumber(number);

                log.info("ㅅ=>{}",car);


                Transportorder obj = new Transportorder();
                obj.setInvoicenumber(invoicenumber);
                obj.setDestination(destination);
                obj.setTransportcharge(transportcharge);
                obj.setLandtransportation(car);
                obj.setApplication(app);

                log.info("최종수송주문{}", obj);

                tpService.transportordersave(obj);
                return "redirect:/orca/admin/ordertable.do";
                // return "redirect:/orca/admin/car_assignment.do?invoicenumber=" + invoicenumber;
            }
            return "redirect:/orca/admin/ordertable.do";
        }catch(Exception e) {
            e.printStackTrace();
            return "redirect:/orca/admin/ordertable.do";
        }
    }
}


