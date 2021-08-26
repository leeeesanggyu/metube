# ✔ Metube - eGovFrame

## :office:동작 화면
* **Login Page**
<img src="https://user-images.githubusercontent.com/76906458/130413909-70c5baec-bd5f-424e-94c5-c509f71f1df6.PNG" width="60%" height="60%"/>

* **Main Page**
<img src="https://user-images.githubusercontent.com/76906458/130545155-aaab45af-0388-448d-a948-67b6ad966bbf.PNG"/>

* **Detail Post**
<img src="https://user-images.githubusercontent.com/76906458/130413915-9b6c469a-5024-4d05-a95f-30c2aff4d957.PNG"/>

* **Create Post**
<img src="https://user-images.githubusercontent.com/76906458/130413988-a1022f56-4cf2-42fc-90b3-782bdac35e71.PNG"/>

## :link: 배포 URL
* ..
* ..

## :clipboard: 개발환경
* Eclipse
* Insomnia
* HeidiSql
* GitHub

## :clipboard: 사용 기술
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

### 프론트엔드
* Javascript
* JQuery
* Vue

## :clipboard: 주요 키워드
* **REST API**
* **트랜잭션**
* **Lucy-XSS-Servlet-Filter**
* **Git 버전관리**
* **Docker**
* **AWS EC2 배포**
* **DB RDS 배포**

* **Http request range(비디오 스트리밍)**

<img src="https://user-images.githubusercontent.com/76906458/130545397-12016392-ab0e-42a9-a4bc-f147091250c5.gif" width="60%" height="60%"/>

* **소켓 알림 구현**

![실시간 알림](https://user-images.githubusercontent.com/76906458/130413923-5875410f-3ccc-4f4f-a078-d4889537fbfa.gif)
![알림 목록](https://user-images.githubusercontent.com/76906458/130413966-df17f542-c88c-44eb-a984-16704b27516a.gif)


* **썸네일 자동생성(크롭)**

<img src="https://user-images.githubusercontent.com/76906458/130545161-b2db0ea5-260a-4bbb-a755-f816712e46f1.PNG" width="70%" height="70%"/>


* **Bcrypt 비밀번호 암호화**

![user table](https://user-images.githubusercontent.com/76906458/130413904-b62114d5-dd23-484e-8f3d-719337cf4bd1.PNG)


* **Simple captcha**

![캡챠](https://user-images.githubusercontent.com/76906458/130418765-99a31e8b-9621-46e6-9c7d-4a297d5c878c.PNG)


* **CKEditor**

![ckeditor](https://user-images.githubusercontent.com/76906458/130413853-fb088b0d-dbb6-43eb-a358-d839f38ab457.PNG)


* **조회수 반복증가 쿠키로 막기**

<img src="https://user-images.githubusercontent.com/76906458/130413990-e3a7d394-feb6-4898-83be-23e5e28bb28b.gif" width="70%" height="70%"/>


## :factory: 시스템 구조

## :link: ERD 설계
![Metube ERD](erd.PNG)

## :link: Rest API 문서
* [Metube Rest API 문서](https://github.com/)

<details markdown="1">
<summary>기타 메모(접기/펼치기)</summary>

subscribe
p_user_pk : parents_user_pk
c_user_pk: child_user_pk

**role**
1 = 게스트
2 = user
3 = admin

**kind**
1 커뮤 게시판
2 자유 게시판 (user, admin)
3 공지 게시판 (admin만)

**is_delete**
0 정상
1 삭제

**lock**
0 정상
1 잠금


</details>
