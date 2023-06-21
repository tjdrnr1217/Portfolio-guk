package com.finalproject.JE.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.DJ.repository.DJOrcaClientRepository;
import com.finalproject.JE.mapper.ClientMapper;
import com.finalproject.JE.service.JEservice;
import com.finalproject.entity.Client;
import com.finalproject.entity.ClientProjection;
import com.finalproject.entity.ClientProjection2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping(value = "/orca")
@RequiredArgsConstructor
public class OrcaClientController {

    final String format="OrcaJoinCompanyController => {}";
    final JEservice jeservice;
    final DJOrcaClientRepository cRepository; 
    final ClientMapper cMapper;
    final HttpSession httpSession;//세션객체
    final ResourceLoader resourceLoader;

    // @Value("${default.image}") String DEFAULTIMAGE;

    @GetMapping(value = "/join.do")
    public String joinGET(){
        return "JE/join";
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Client obj,@RequestParam(name="tmpfile") MultipartFile file){
        try{
        log.info(format,obj.toString());
        BCryptPasswordEncoder bcpe= new BCryptPasswordEncoder();//salt값을 자동으로 부여함.(이전 암호화 salt값은 아이디로했음)
        obj.setPassword(bcpe.encode(obj.getPassword())); //기존암호를 암호화시켜서 다시 저장
        obj.setChk(BigInteger.valueOf(1)); //Biginteger타입이므로 타입 변환해서 값을 넣어줌
        obj.setFilesize(BigInteger.valueOf(file.getSize()));
        obj.setFiledata(file.getInputStream().readAllBytes());
        obj.setFiletype(file.getContentType());
        obj.setFilename(file.getOriginalFilename());
        log.info(format, obj.toString());

        // cRepository.save(obj); 
        int ret=jeservice.insertCompanyOne(obj);
        return "redirect:/orca/login.do";
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/join.do";
        }
    }

    @GetMapping(value = "/company/deactive.do")
    public String deactiveGET(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("user", user); //user를 보냄
        return "JE/deactive";
    }

    @Transactional
    @PostMapping(value = "/company/deactive.do")
    public String deactivePOST(@ModelAttribute Client obj,@AuthenticationPrincipal User user){
        try{
            String id=user.getUsername();
            String pw=user.getPassword();

            // Client client=cRepository.findById(id).orElse(null);
            Client client=jeservice.deactiveClient(id);

            BCryptPasswordEncoder bcpe= new BCryptPasswordEncoder();//salt값을 자동으로 부여함.(이전 암호화 salt값은 아이디로했음)
            // obj.setPassword(bcpe.encode(obj.getPassword())); //기존암호를 암호화시켜서 다시 저장

            if (bcpe.matches(obj.getPassword(), pw) && id.equals(obj.getId())) {
                
                client.setBrn(null);
                client.setPhone(null);
                client.setPassword(null);
                client.setPostcode(null);
                client.setAddress(null);
                client.setDaddress(null);
                client.setRegdate(null);
                client.setChk(BigInteger.valueOf(0));
                client.setName(null);
                client.setRole(null);
                client.setIdchkq(null);
                client.setIdchka(null);
                client.setEmail(null);
                client.setFiledata(null);
                client.setFilesize(null);
                client.setFilename(null);
                client.setFiletype(null);

                httpSession.invalidate();//로그아웃

                return "redirect:/orca/home.do";
            }
           

        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/company/deactive.do";
        }
    return "redirect:/orca/company/deactive.do";
    }

    @GetMapping(value = "/company/updatepw.do")
    public String updatepwGET(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("user", user); //user를 보냄
        return "JE/updatepw";
    }

    @Transactional
    @PostMapping(value = "/company/updatepw.do")
    public String updatepwPOST(@ModelAttribute Client obj,@AuthenticationPrincipal User user, @RequestParam(name="newpassword") String newpw){
        try{
            String id=user.getUsername();
            String pw=user.getPassword();

            // Client client=cRepository.findById(id).orElse(null);
            Client client=jeservice.updatePassword(id);

            BCryptPasswordEncoder bcpe= new BCryptPasswordEncoder();//salt값을 자동으로 부여함.(이전 암호화 salt값은 아이디로했음)
            // obj.setPassword(bcpe.encode(obj.getPassword())); //기존암호를 암호화시켜서 다시 저장

            if (bcpe.matches(obj.getPassword(), pw)) {

                client.setPassword(bcpe.encode(newpw));

                log.info(format,bcpe.encode(newpw));
                httpSession.invalidate();//로그아웃

                return "redirect:/orca/home.do";
            }
           

        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/company/updatepw.do";
        }
    return "redirect:/orca/company/updatepw.do";
    }

    @GetMapping(value = "/findid.do")
    public String findidGET(){
        return "JE/findid";
    }

    @PostMapping(value = "/findid.do")
    public String findidPOST(@ModelAttribute Client obj){
        try{
            // ClientProjection client=cRepository.findByIdchkaAndBrn(obj.getIdchka(), obj.getBrn());
            ClientProjection client=jeservice.findClientId(obj);
            String id=client.getId();
    
            log.info(format,id);
            return "redirect:/orca/findid2.do?id="+id;

        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/findid.do";
        }
    }
    @RequestMapping(value = "/findid2.do", method={RequestMethod.GET, RequestMethod.POST})
    public String findid2GET(Model model,@RequestParam(name = "id") String id){
        log.info(format,id);
        model.addAttribute("id", id);
        return "JE/findid2";
    }

    @GetMapping(value = "/findpw.do")
    public String findpwGET(){
        return "JE/findpw";
    }

    @PostMapping(value = "/findpw.do")
    public String findpwPOST(@ModelAttribute Client obj){
        try{
            ClientProjection2 client=jeservice.findClientPassword(obj);
            // ClientProjection2 client=cRepository.findByIdAndBrn(obj.getId(), obj.getBrn());
            log.info(format,client.toString());
            String pw=client.getPassword();
            String id=client.getId();
    
            // log.info(format,id);
            return "redirect:/orca/findpw2.do?id="+id+"&"+"pw="+pw;

        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/findpw.do";
        }
    }
    @RequestMapping(value = "/findpw2.do", method={RequestMethod.GET, RequestMethod.POST})
    public String findpw2GET(Model model,@RequestParam(name = "id") String id, @RequestParam(name="pw")String pw){
        try{
        log.info(format,id);
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        
        Client client=jeservice.deactiveClient(id);

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
		'U', 'V', 'W', 'X', 'Y', 'Z' };

        int idx = 0;
        StringBuffer sb = new StringBuffer();
        
        System.out.println("charSet.length :::: "+charSet.length);
        
        for (int i = 0; i < 10; i++) {
            
            idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를  Int로 추출 (소숫점제거)
            System.out.println("idx :::: "+idx);
            sb.append(charSet[idx]);
            }
            model.addAttribute("pw", sb.toString());
            String newpw=sb.toString();
            log.info(format, newpw);


            BCryptPasswordEncoder bcpe= new BCryptPasswordEncoder();//salt값을 자동으로 부여함.(이전 암호화 salt값은 아이디로했음)
            client.setPassword(bcpe.encode(newpw));
            cRepository.save(client);
            log.info(format, bcpe.encode(newpw));
            log.info(format, client.toString());
            log.info(format, client.getPassword());
    
        return "JE/findpw2";
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/orca/findpw.do";
        }
    }

    @GetMapping(value = "/company/mypage/updateinfo.do")
    public String mypageinfoGET(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user); //user를 보냄
        // Client client=cRepository.findById(user.getUsername()).orElse(null);
        Client client=jeservice.updateInfo(user.getUsername());
        log.info("client 체크하기333 => {}",client.toString());
        // Client(id=z, brn=6158127080, phone=z, postcode=48557, address=부산 남구 무민사로 5,
        // daddress=zzz, regdate=2023-05-26 10:27:31.494, chk=1, name=z, role=COMPANY, 
        //idchkq=유년시절 가장 기억나는 친구 이름은?, idchka=zzz, email=wjdekddl1@naver.com, 
        // filesize=null, filename=null, filetype=null)

        model.addAttribute("client", client);
        return "JE/updateinfo";
    }

    @PostMapping(value = "/company/mypage/updateinfo.do")
    public String mypageinfoPOST(Model model,@ModelAttribute Client obj,@AuthenticationPrincipal User user,@RequestParam(name="tmpfile") MultipartFile file){
        try{
                String id=user.getUsername();
                String pw=user.getPassword();

                 model.addAttribute("id", id);
                 model.addAttribute("pw",pw);

                 Client client=jeservice.updateInfo(user.getUsername());
                //  Client client=cRepository.findById(user.getUsername()).orElse(null);
                 log.info(format,model);
                // BCryptPasswordEncoder bcpe= new BCryptPasswordEncoder();//salt값을 자동으로 부여함.(이전 암호화 salt값은 아이디로했음)
                
                if(!file.getOriginalFilename().equals("")){//새 파일이 있을 때
                    // client.setPassword(bcpe.encode(obj.getPassword()));
                    client.setFilesize(BigInteger.valueOf(file.getSize()));
                    client.setFiledata(file.getInputStream().readAllBytes());
                    client.setFiletype(file.getContentType());
                    client.setFilename(file.getOriginalFilename());
                    log.info("client확인=>{}",client.getFilename());
                    log.info("obj확인=>{}",file.getOriginalFilename());
                    
                }
                else{ //새 파일이 없을 때
                    client.setFilesize(client.getFilesize());
                    client.setFiledata(client.getFiledata());
                    client.setFiletype(client.getFiletype());
                    client.setFilename(client.getFilename());
                }   
                    client.setBrn(obj.getBrn());
                    client.setName(obj.getName());
                    client.setPhone(obj.getPhone());
                    client.setEmail(obj.getEmail());
                    client.setPostcode(obj.getPostcode());
                    client.setAddress(obj.getAddress());
                    client.setDaddress(obj.getDaddress());
                    log.info(format,client.toString());

            
                    // cRepository.save(client);
                    return "redirect:/orca/company/mypage.do";
               
    
            }catch(Exception e){
                e.printStackTrace();
                return "redirect:/orca/company/mypage/updateinfo.do";
            }
            //  return "redirect:/orca/company/mypage/updateinfo.do";
    }

    @GetMapping(value = "/portauxiliarybusiness.do")
    public String pabGET(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("user", user); //user를 보냄
        return "JE/portauxiliarybusiness";
    }

    //이미지
    @GetMapping(value="/company/image")
    public ResponseEntity<byte[]> image(Model model,@AuthenticationPrincipal User user) throws IOException{
        String id=user.getUsername();

        model.addAttribute("id", id);
        Client obj=jeservice.image(user.getUsername());
        // Client obj=cRepository.findById(user.getUsername()).orElse(null);
        HttpHeaders headers = new HttpHeaders();

        if(obj!= null){//이미지가 존재하는지 확인
            if(obj.getFilesize().longValue()>0){
                headers.setContentType(MediaType.parseMediaType(obj.getFiletype()));
                return new ResponseEntity<>(obj.getFiledata(),headers,HttpStatus.OK);
            }
        }
        //이미지가 없을 경우
        InputStream is=resourceLoader.getResource("classpath:/static/JE/images/default.jpg").getInputStream();//exception발생됨.
        //InputStream is=resourceLoader.getResource(DEFAULTIMAGE).getInputStream();//exception발생됨.
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(is.readAllBytes(),headers,HttpStatus.OK);
    }

    @GetMapping(value = "/organization.do")
    public String organizationGET(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("user", user); //user를 보냄
        return "JE/organization";
    }
}
