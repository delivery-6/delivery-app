## 🛵 개발의 민족
![개발의 민족](https://ifh.cc/g/6Z186j.png)

## 📌 목차

1. [🛵 개발의 민족(아웃소싱 프로젝트)](#-개발의-민족-아웃소싱-프로젝트)
2. [📌 목차](#-목차)
    - [🗂️ Project info](#-Project-info)
    - [🗂👥 Introduce team members](#-Introduce-team-members)
    - [🧾 API Specification](#-API-specification)
    - [📂 ERD Diagram](#-ERD-Diagram)
    - [🚀 Stacks](#-Stacks)
    - [💻 Program operation screen](#-Program-operation-screen)
    - [☄️ Major functional code](#-Major-functional-code)
    - [📂 Architecture](#-Architecture)
    - [🛠️ Trouble shooting](#-Trouble-shooting)

## 🗂️ Project info

- 프로젝트 이름 : Outsourcing Project < 개발의 민족 >
- 개발기간 : 2025.01.07 - 2025.01.12

## 👥 Introduce team members

<table>
  <tbody>
    <tr>
      <td align="center"><a href=""><img src="https://ifh.cc/g/9jJJ2z.jpg" width="200px;" alt=""/><br><sub><b>팀장 : 박지안</b></sub></a><br></td>
      <td align="center"><a href=""><img src="https://ifh.cc/g/H4wKvC.jpg" width="200px;" alt="배달의 민족 안경잡고 있는 이미지"/><br><sub><b>팀원 : 우혁규</b></sub></a><br></td>
      <td align="center"><a href=""><img src="https://ifh.cc/g/hlaxNM.jpg" width="200px;" alt="배달의 민족 장갑끼고 있는 이미지"/><br><sub><b>팀원 : 문규민</b></sub></a><br></td>
      <td align="center"><a href=""><img src="https://ifh.cc/g/oqcr13.jpg" width="200px;" alt=""/><br><sub><b>팀원 : 최순우</b></sub></a><br></td>
   </tr>
  </tbody>
</table>

## 🧾 API 명세서
| Method | 기능       | URL                               | Request                                                                                       | Response                                                                                                   |
|--------|----------|-----------------------------------|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| POST   | 사용자 생성   | /api/users                        | RequestBody<br><br>email → 필수  ⭕<br>password → 필수  ⭕                                          | {<br>email,<br>created_at,<br>updated_at<br>}                                                              |
| DELETE | 사용자 삭제   | /api/users                        | RequestBody<br><br>email → 필수  ⭕<br>password → 필수  ⭕                                          | 계정 삭제가 완료되었습니다.                                                                                            |
| POST   | 로그인      | /api/users                        | RequestBody<br><br>email → 필수  ⭕<br>password → 필수  ⭕                                          | {name} 님이 로그인 되었습니다.                                                                                       |
| POST   | 로그아웃     | /api/users                        |                                                                                               | 로그아웃 완료되었습니다.                                                                                              |
| POST   | 점포 생성    | /api/shops                        | RequestBody<br><br>user → 필수  ⭕<br>name → 필수  ⭕                                               | {<br>id,<br>user,<br>name<br>}                                                                             |
| PATCH  | 점포 수정    | /api/shops/{id}                   | RequestBody<br><br>user → 필수  ⭕<br>name → 필수  ⭕<br><br>수정 전/후가 같을 경우 수정되지 않음                  | {<br>id,<br>user,<br>name<br>}                                                                             |
| PATCH  | 점포 조회    | /api/shops                        | RequestParam<br><br>user → 필수  ❌<br>name → 필수  ❌                                              | {<br>“content”: [ {<br>id,<br>user,<br>name,<br>created_at,<br>updated_at<br>} ],<br>“pageInfo” : { }<br>} |
| DELETE | 점포 삭제    | /api/shops/{id}                   | RequestBody<br><br>user → 필수  ⭕<br>name → 필수  ⭕                                               | 삭제가 완료되었습니다.                                                                                               |
| POST   | 메뉴 생성    | /api/shops/{shopId}/menu          | RequestBody<br><br>shop → 필수  ⭕<br>name → 필수  ⭕<br>price → 필수  ⭕<br><br>수정 전/후가 같을 경우 수정되지 않음 | {<br>name,<br>price<br>}                                                                                   |
| PATCH  | 메뉴 수정    | /api/shops/{shopId}/menu/{menuId} | RequestBody<br><br>shop → 필수  ⭕<br>name → 필수  ⭕<br>price → 필수  ⭕<br><br>수정 전/후가 같을 경우 수정되지 않음 | {<br>name,<br>price<br>}                                                                                   |
| DELETE    | 메뉴 삭제    | /api/shops/{shopId}/menu/{menuId} | RequestBody<br><br>shop → 필수  ⭕<br>name → 필수  ⭕<br>price → 필수  ⭕<br><br>수정 전/후가 같을 경우 수정되지 않음 | 삭제가 완료되었습니다.                                                                                               |
| POST  | 리뷰 생성    | /api/shops/{shopId}/reviews       | RequestBody<br><br>user  → 필수  ⭕<br>description → 필수  ⭕                                       | 리뷰가 생성되었습니다.                                                                                               |
| PATCH  | 리뷰수정     | /api/reviews/{reviewId}           | RequestBody<br><br>user  → 필수  ⭕<br>description → 필수  ⭕                                       | 수정이 완료되었습니다.                                                                                               |
| GET  | 리뷰 조회    | /api/shops/{shopId}/page/{page}   | RequestParam<br><br>user → 필수  ❌<br>description → 필수  ❌                                              | {<br>userName,<br>rating,<br>decription,<br>createAt,<br>updateAt,<br>}                                    |
| DELETE  | 리뷰 삭제    | /api/reviews/{reviewId}           | RequestBody<br><br>user → 필수  ⭕ | 삭제 완료되었습니다.                                                                                                |
| POST  | 주문 생성    | /api/orders                       |  |   |

## 📂 ERD Diagram
![ERD 다이어그램](https://ifh.cc/g/tmpkq4.jpg)

<br>

## 🚀 Stacks

### Environment
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![GitGub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

### Skills
![Spring Boot](https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
### OS
![Mac os](https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=apple&logoColor=white)
![windows](https://img.shields.io/badge/Windows_11-008080?style=for-the-badge&logo=windows-95&logoColor=white)

<br>

## 💻 Program operation screen

<details>
   <summary>📍 실행 화면 보기</summary>

### 📍 실행 화면

</details>

<br>

## 📂 Architecture

```java
📦 DeliveryApp
│
├─ 📂 auth
│    ├─ 📂 controller
│    │    └─ AuthController.java
│    │
│    ├─ 📂 dto
│    │    └─ LoginRequestDto.java
│    │
│    └─ 📂service
│         └─ AuthService.java
│
├─ 📂 common
│    └─ 📂 entity
│         └─ BaseEntity.java
│
├─ 📂 cofig
│    ├─ QuerydslConfig.java
│    └─ SecurityConfig.java
│
├─ 📂 filter
│    └─ JwtFilter.java
│
├─ 📂 menu
│    │
│    ├─ 📂 controller
│    │    └─ MenuController.java
│    │
│    ├─ 📂 dto
│    │    │
│    │    ├─ 📂 request
│    │    │    ├─ MenuCreateRequestDto.java
│    │    │    └─ MenuUpdateRequestDto.java
│    │    │
│    │    └─ 📂 response
│    │         ├─ MenuCreateRequestDto.java
│    │         └─ MenuUpdateRequsetDto.java
│    │
│    ├─ 📂 entity
│    │    └─ Menu.java
│    │
│    ├─ 📂 repository
│    │    ├─ MenuQueryRepository.java
│    │    ├─ MenuQueryRepositoryImpl.java
│    │    └─ MenuRepository.java
│    │
│    └─ 📂service
│         └─ MenuService.java
│
├─ 📂 order
│    │
│    ├─ 📂 controller
│    │    └─ OrderController.java
│    │
│    ├─ 📂 entity
│    │    ├─ Order.java
│    │    └─ OrderMenu.java
│    │
│    ├─ 📂 repository
│    │    ├─ OrderQueryRepository.java
│    │    └─ OrderQueryRepositoryImpl.java
│    │
│    └─ 📂service
│         └─ OrderService.java
│
├─ 📂 review
│    │
│    ├─ 📂 controller
│    │    └─ ReviewController.java
│    │
│    ├─ 📂 dto
│    │    │
│    │    ├─ 📂 request
│    │    │    ├─ ReviewCreateRequestDto.java
│    │    │    └─ ReviewUpdateRequestDto.java
│    │    │
│    │    └─ 📂 response
│    │         ├─ ReviewCreateRequestDto.java
│    │         └─ ReviewUpdateRequsetDto.java
│    │
│    ├─ 📂 entity
│    │    └─ Review.java
│    │
│    ├─ 📂 repository
│    │    ├─ ReviewQueryRepository.java
│    │    ├─ ReviewQueryRepositoryImpl.java
│    │    └─ ReviewRepository.java
│    │
│    └─ 📂service
│         └─ ReviewService.java
│
├─ 📂 security
│    ├─ UserDetailsImpl.java
│    └─ UserDetailsServiceImpl.java
│
├─ 📂 shop
│    │
│    ├─ 📂 controller
│    │    └─ ShopController.java
│    │
│    ├─ 📂 dto
│    │    │
│    │    ├─ 📂 request
│    │    │    ├─ ShopCreateRequestDto.java
│    │    │    └─ ShopUpdateRequestDto.java
│    │    │
│    │    └─ 📂 response
│    │         ├─ ShopCreateRequestDto.java
│    │         ├─ ShopReadRequestDto.java
│    │         └─ ShopUpdateRequsetDto.java
│    │
│    ├─ 📂 entity
│    │    └─ Shop.java
│    │
│    ├─ 📂 repository
│    │    ├─ ShopRepository.java
│    │    ├─ ShopRepositoryCustom.java
│    │    └─ ShopRepositoryCustomImpl.java
│    │
│    └─ 📂service
│         └─ ShopService.java
│
├─ 📂 shop
│    │
│    ├─ 📂 controller
│    │    └─ UserController.java
│    │
│    ├─ 📂 dto
│    │    │
│    │    ├─ MeResponseDto.java
│    │    ├─ RegisterRequestDto.java
│    │    └─ RegisterResponseDto.java
│    │
│    ├─ 📂 entity
│    │    ├─ Role.java
│    │    └─ User.java
│    │
│    ├─ 📂 repository
│    │    └─ UserRepository.java
│    │
│    └─ 📂service
│         └─ UserService.java
│
└─ 📂 Utils
     ├─ AuthUtil.java
     └─ JwtUtil.java

```


## 🛠️ Trouble shooting
[☄️ 최순우 트러블 슈팅 바로가기](https://promicing.com/blog/43/)