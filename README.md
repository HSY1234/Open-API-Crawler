# 전국 아파트 상세 실거래 데이터 크롤링 머신
## 날짜 변경만으로 쉽게!
 - 전국 지역코드를 활용해 날짜 변경만으로 모든 데이터를 DB에 바로 저장합니다.
 ### 사용법
![image](/uploads/2cf72a2e4fdebf46281c53438416d592/image.png)
 - 공공데이터 서비스 키와 카카오 RestApi 서비스 키를 각각 입력한 후,
 - 시작, 끝나는 년 월을 설정하고,
 - target String을 해당하는 지역의 이름으로 바꾸고 실행하면 됩니다.
 - (ex) 서울 -> seoul, 대전 -> daejeon, 전국 -> korea 등 LocalData 클래스 참조!

 ### DB 설정
  - 먼저 HouseInfo 테이블의 PK를 Auto Increase로 설정해야 하고 aptName을 Unique로 설정해야 합니다.
   이는 SQL 파일을 첨부했습니다.

 ### 해결해야 하는 점
  - MySQL의 정책 변경으로 replace into 실행 시 Auto Increase의 값이 증가합니다. 따라서 이를 해결하기 위한 해답을 찾고 있습니다.
