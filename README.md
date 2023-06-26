# PORTFOLIO
# :clipboard: 목차

- :books: <a href="#outline">개요</a>
- :wrench: <a href="#tech">기술스택</a>
- :family: <a href="#team">팀원소개</a>
- :bookmark_tabs: <a href="#function">기능구현</a>
- :bulb: <a href="#result">결론</a>
- :mag_right: <a href="#fullfill">보완할점</a>
- :bookmark: <a href="#url">배포</a>

# :books: <a name="outline">개요</a>
<img src="https://raw.githubusercontent.com/ldj8196/Portfolio/main/src/main/resources/static/DJ/portfolioimage/Mainpage.png">

>**프로젝트**: 항만물류사이트 제작
>
>**기획 및 제작** : Team Orca
>
>**분류**: 개인포트폴리오
>
>**제작 기간**: 2023.05.15 ~ 2023.06.09
>
>**담당 기능**: Spring Security 로그인 로그아웃, 차량 선박 배정, 운행정보관리(추가,일괄수정), 주문목록조회, 전체적인 레이아웃 및 디자인

# :wrench: <a name="tech">기술스택</a>
<h4>데이터베이스</h4>
<div align="left">
 	<img src="https://img.shields.io/badge/ORACLE-F80000?style=flat&logo=oracle&logoColor=white" />
  <img src="https://img.shields.io/badge/H2-232F3E?style=flat&logo=h2&logoColor=white" />
</div> 
<h4>백엔드</h4>
<div align="left">
 	<img src="https://img.shields.io/badge/JAVA-007396?style=flat&logo=Java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=springsecurity&logoColor=white" />
  <img src="https://img.shields.io/badge/JPA-59666C?style=flat&logo=hibernate&logoColor=white" />
  <img src="https://img.shields.io/badge/MyBatis-232F3E?style=flat&logo=mybatis&logoColor=white" />
</div> 
<h4>프론트엔드</h4>
<div align="left">
	<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white" />
	<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" />
  <img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=flat&logo=javascript&logoColor=white" />
</div>
<h4>UI프로토타입</h4>
<div align="left">
	<img src="https://img.shields.io/badge/FIGMA-F24E1E?style=flat&logo=figma&logoColor=white" />
</div>
<h4>배포</h4>
<div align="left">
  <img src="https://img.shields.io/badge/Linux-FCC624?style=flat&logo=linux&logoColor=white" />
	<img src="https://img.shields.io/badge/AWS-232F3E?style=flat&logo=amazonaws&logoColor=white" />
</div>
<h4>협업도구</h4>
<div align="left">
	<img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white" />
	<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white" />
</div>

# :family: <a name="team">팀원소개</a>
<img src="src/main/resources/static/SG/image/팀원소개.png"> <br/><br/>
# :bookmark_tabs: <a name="function">기능구현</a>

**1. Spring Security 로그인 로그아웃**
<img src="./src/main/resources/static/SG/pfimages/시큐리티.gif"> <br/><br/>
- SecurityConfig를 이용하여 경로에 따른 접근 권한 설정
- Securityimpl에서 id와password를 확인하여 고객의 정보를 가져온다.
- successHandler를 이용하여 하나의 로그인 창에서 로그인 시 권한에 맞는 페이지로 이동
- AuthenticationFilter 에서부터 user DB까지 타고 들어가 DB에 있는 유저라면 UserDetails로 꺼내서 유저의 session 생성
<br/>

**2. 차량 선박 배정**
<img src="/src/main/resources/static/SG/pfimages/차량_선박-배정.gif"> <br/><br/>
- 가젼적서를 이용하여 주문정보를 받아 화물종류와 하역 위치에 따라 차량과 선박 배치
- 하역장소가 하역장이면 선박 배정, 지역창고면 선박과 차량 배정이 되도록 필터링하여 구현
- 화물을 선박과 차량에 배정할때 마다 최대적재량에서 화물무게를 제외하고 최대적재량이 화물무게 보다 작을때 선박과 차량을 배정을 할수없다. 
<br/>

**3. 운행정보관리(추가,일괄수정)**
![운행정보](https://github.com/tjdrnr1217/Portfolio-guk/assets/123910866/a42dc207-008f-498e-b31c-1c9d83fdcaef)
- DB에 있는 운행정보를 내림차순하여 정렬하고 보기 쉽게 페이지네이션으로 구현
- 일반수정과 달리 일괄수정으로 구현하여 운행정보 여러 개를 한꺼번에 수정 가능
<br/>

**4. 주문목록조회**
<img src="/src/main/resources/static/SG/pfimages/화물조회.gif"> <br/><br/>
- 로그인시 세션에 저장된 아이디를 통하여 주문한 화물 조회
- 페이지네이션과 필터 검색을 구현을 통해 고객의 편리성 제공
<br/>

**4. 전체적인 레이아웃 및 디자인**
![메인1](https://github.com/tjdrnr1217/Portfolio-guk/assets/123910866/0f941320-9c50-477d-a334-248bb724735a)<br/><br/>
![메인2](https://github.com/tjdrnr1217/Portfolio-guk/assets/123910866/0ff89b0c-c296-43e1-a519-b2a12237ae9c)<br/><br/>
- javascript를 이용하여 swiper 라이브러리를 사용하여 웹 페이지 내의 요소를 슬라이드 할 수 있는 기능을 추가
- javascript를 이용하여 스크롤시 헤더 색상 변경
- AOS를 통해 글씨에 모션을 설정하여 애니메이션 라이브러리 동작

# :bulb: <a name="result">결론</a>
이전과 달리 다른 언어기반으로 웹 관련 프로젝트를 하면서 주제 선정, 데이터베이스설계, 기능 구현, 프로젝트 배포까지의
과정을 거치면서 전반적인 개발의 흐름을 알 수 있었고, mybatis 뿐 아니라 jpa를 사용하며 개발자는
프로젝트 수행 중 자신이 주로 사용하는 스택뿐 아니라 여러가지 기술스택을 사용함으로써
프로젝트를 하나씩 수행할 수록 점차 성장할 수 있으며, 이번에 수행한 프로젝트가 항만물류였는데
이쪽 분야의 자료를 조사하면서 물류와 유통관련 지식도 함께 얻어가서 많은 것을 습득할 수 있었던
프로젝트였습니다.<br/><br/>

# :mag_right: <a name="fullfill">보완할점</a>
 - 관리자 계정의 선박 배정 및 차량 배정이 목적지에 따른 배정 유효성 검사가 없었던 점
 - 반응형 웹을 이용하여 사이즈에 따른 페이지 깨짐이 없었어야한 점
 - 토큰 사용 및 aop등 기능들을 다 사용하지 못해본점
 - 고객을 위한 게시판 구현 및 게시판 댓글기능을 구현하지 못한 점

# :bookmark: <a name="url">배포</a>
<a href="http://13.125.14.162:8080/ROOT2/orca/home.do">Orca Web Page</a>
- AWS를 이용한 배포 */
