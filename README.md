# ✔ Metube - eGovFrame



## :office:동작 화면
<details markdown="1">
<summary>펼치기</summary>

* **Login Page**
<img src="https://user-images.githubusercontent.com/76906458/130413909-70c5baec-bd5f-424e-94c5-c509f71f1df6.PNG" width="60%" height="60%"/>

* **Main Page**
<img src="https://user-images.githubusercontent.com/76906458/130545155-aaab45af-0388-448d-a948-67b6ad966bbf.PNG"/>

* **Detail Post**
<img src="https://user-images.githubusercontent.com/76906458/130413915-9b6c469a-5024-4d05-a95f-30c2aff4d957.PNG"/>

* **Create Post**
<img src="https://user-images.githubusercontent.com/76906458/130413988-a1022f56-4cf2-42fc-90b3-782bdac35e71.PNG"/>


</details>

## :link: 배포 URL
* http://metube.o-r.kr/
* testID: user@user.com
* testPW: user

## :clipboard: 개발환경
* Eclipse
* Insomnia
* HeidiSql
* GitHub

## :clipboard: 사용 기술

### 프론트엔드
* Javascript
* JQuery
* Vue

### 백엔드
#### Spring
* JAVA 8
* Spring MVC
* Spring Mybatis
* Spring AOP

#### Build tool
* Maven

#### Database
* PostgreSQL
* Mybatis

#### AWS
* EC2
* RDS

## :clipboard: 주요 키워드
* **REST API**
* **트랜잭션**
* **Git 버전관리**
* **Docker**
* **AWS EC2 배포**
* **DB RDS 배포**
* **로그인, 로그아웃, 회원가입**
* **게시물(CRUD)(이미지, 동영상 업로드), 댓글**
* **어드민 회원관리(잠금 기능), 권한별 기능분리, 게시판 분류**
* **검색기능, 좋아요, 구독**

#### >펼쳐서 확인해주세요.<
<details markdown="1">
<summary><strong>Http request range(비디오 스트리밍)</strong></summary>

<img src="https://user-images.githubusercontent.com/76906458/130545397-12016392-ab0e-42a9-a4bc-f147091250c5.gif" width="60%" height="60%"/>

과도한 트래픽 방지하고 동영상 파일이 클경우 로딩이 덜되어도 재생가능<br>
request 헤더에서 range를 읽고 시작 위치를 지정해주고 range 범위만큼 전송 내용을 넣는다<br>
8kb로 잘라서 파일의 크기가 크더라도 문제가 되지않게 만들었다<br>

</details>

<details markdown="1">
<summary><strong>socket (실시간 알림 구현)</strong></summary>

![실시간 알림](https://user-images.githubusercontent.com/76906458/130413923-5875410f-3ccc-4f4f-a078-d4889537fbfa.gif)
![알림 목록](https://user-images.githubusercontent.com/76906458/130413966-df17f542-c88c-44eb-a984-16704b27516a.gif)

알림창은 toastr로 구현하였고 onshown함수를 이용하여 알림이 떴을때<br>
해당 회원이 접속중이라면 toarstr을 띄우고 DB에 저장하고 알림 목록을 다시 가져왔다.<br>
알림을 받는 회원이 접속중이지 않다면 디비에만 저장하여 접속시 확인할 수 있게 구현하였다.

</details>

<details markdown="1">
<summary><strong> scalr (썸네일 자동생성)</strong></summary>

<img src="https://user-images.githubusercontent.com/76906458/130545161-b2db0ea5-260a-4bbb-a755-f816712e46f1.PNG" width="70%" height="70%"/>

원본 사진의 높이와 너비를 기준으로 하여 썸네일의 비율로 높이와 너비를 계산한 후<br>
계산된 크기로 원본이미지를 가운데를 기준으로 scalr 라이브러리의 crop을 이욯하여 자른다.<br>

</details>

<details markdown="1">
<summary><strong>Bcrypt (비밀번호 암호화)</strong></summary>

![user table](https://user-images.githubusercontent.com/76906458/130413904-b62114d5-dd23-484e-8f3d-719337cf4bd1.PNG)

</details>

<details markdown="1">
<summary><strong>Lucy-XSS-Servlet-Filte</strong></summary>

<img src="https://user-images.githubusercontent.com/76906458/130905525-29a12da6-e854-43a3-a0c4-e23c16781879.PNG" />

lucy 의 필터링은 서블릿 설정으로 적용을 했기 때문에 form-data에 대해서만 적용되고 <br>
Request Raw Body로 넘어가는 JSON에 대해서는 처리해주지 않기때문에 JSON데이터는 jstl로 처리하였다.<br>

</details>

<details markdown="1">
<summary><strong>Simple captcha(자동로그인 방지)</strong></summary>

![캡챠](https://user-images.githubusercontent.com/76906458/130418765-99a31e8b-9621-46e6-9c7d-4a297d5c878c.PNG)

</details>

<details markdown="1">
<summary><strong>CKEditor</strong></summary>

![ckeditor](https://user-images.githubusercontent.com/76906458/130413853-fb088b0d-dbb6-43eb-a358-d839f38ab457.PNG)
![cke](https://user-images.githubusercontent.com/76906458/131061878-d469c682-91eb-42fa-b8bd-f4ef3afb4c4e.PNG)

</details>

<details markdown="1">
<summary><strong>조회수 반복증가 쿠키로 막기</strong></summary>

<img src="https://user-images.githubusercontent.com/76906458/130413990-e3a7d394-feb6-4898-83be-23e5e28bb28b.gif" width="70%" height="70%"/>

쿠키에 조회한 게시물PK를 넣고 다시 게시물에 방문할때 현재 게시물PK가 쿠키에 있나 확인후 없다면 <br>
조회수를 증가시키고 있다면 조회수를 증가시키지 않는다.<br>

</details>



## :factory: 시스템 구조

## :link: ERD 설계
![erd](https://user-images.githubusercontent.com/76906458/130908917-b39398ea-4471-4225-8965-de5550bb4b12.PNG)

## :link: 추가할 예정
* **무한 스크롤**
* **영상 썸네일**
* **데이터 서버 분리**
* **자동화 배포**


## 📝: 기타 메모
<details markdown="1">
<summary>펼치기</summary>

**subscribe**<br>
p_user_pk : parents_user_pk<br>
c_user_pk: child_user_pk<br>

**role**<br>
1 = 게스트<br>
2 = user<br>
3 = admin<br>

**kind**<br>
1 커뮤 게시판<br>
2 자유 게시판 (user, admin)<br>
3 공지 게시판 (admin만)<br>

**is_delete**<br>
0 정상<br>
1 삭제<br>

**lock**<br>
0 정상<br>
1 잠금<br>


</details>
