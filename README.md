![icon120](https://github.com/user-attachments/assets/8dfaabe7-26f0-4301-b03f-e93a9327f52c)
### 현재 운영되고 있는 API 주소예요. [https://marginfairy.store/](https://marginfairy.store/)

## 마진요정
### 무엇을 위한 어플인가요? :question:
 - 요식업 종사자 들을 위해 원가 계산기를 만들었어요.
 - 소스/재료 개념을 사용해 레시피를 만들면, 원가 코스트를 알려주어요.


## 환경 :cherry_blossom:
 - 개발 IDE STS4
 - Spring boot 3.3.4
 - Java 21
 - Sql Server 2019
 - Gradle

# 기능
## 계정
### 로그인
- Session 기반 로그인을 사용했어요.
- CSRF 기능을 활성화 했어요.
- SSL 인증서를 활성화 했어요.
### 이메일 인증
- 회원가입을 위해 이메일 인증 기능을 사용했어요.
- 인증번호를 보내고 검증할 수 있어요.
### 계정관리
- 이메일 변경을 위해 이메일 인증 기능을 사용했어요.
- 패스워드를 찾을 수 있도록 회원가입 때 사용한 이메일을 통해 임시 비밀번호 발급기능을 구현했어요.
## 단위
- 원하는 단위를 만들 수 있어요.
- 원하는 단위의 상위 단위를 선택할 수 있어요.
### 단위 조회
- 만든 단위를 조회할 수 있어요.
### 단위 관리
- 단위를 등록하고 수정할 수 있어요.
## 재료
- 재료를 내 맘대로 등록할 수 있어요.
- 만든 단위를 재료에 적용할 수 있어요.
### 재료 조회
- 등록한 재료를 한 눈에 볼 수 있어요.
- 등록한 재료의 가장 작은 단위의 금액을 확인할 수 있어요.
### 재료 관리
- 재료를 등록하고 수정할 수 있어요.
## 소스
- 내가 만든 재료들로 소스를 만들 수 있어요.
- 만든 소스는 레시피에 사용할 수 있어요.
### 소스 조회
- 만든 소스를 한눈에 볼 수 있어요.
- 소스에 사용된 재료들의 원가를 가장 작은 단위의 금액을 확인할 수 있어요.
### 소스 관리
- 소스를 등록하고 수정할 수 있어요.
- 소스에 사용될 재료를 추가할 수 있어요.
- 재료를 선택할 때 상위 단위로 선택하여 등록할 수 있어요.
## 레시피
- 만든 재료/소스를 사용해 레시피를 만들어 볼 수 있어요.
- 레시피의 가격을 정해주면 원가률을 알 수 있어요.
### 레시피 조회
- 레시피에 사용된 재료와 소스의 원가를 확인할 수 있어요.
- 레시피의 원가율을 확인 할 수 있어요.
### 레시피 관리
- 레시피를 등록하고 수정할 수 있어요.
- 레시피에 내가 만든 소스와 재료를 등록하고 수정할 수 있어요.
## 마진
- 만든 레시피를 통해 판매 수량을 설정해서 순 이익을 확인 할 수 있어요.
- 해당 기능은 데이터베이스에 저장되지 않아요.

# API 명세서
| Domain | URL | Method | Authority |
| --- | --- | --- | --- |
| /api  | /login  | POST | - |
|   | /register  | POST | - |
|   | /sendverifyemail  | POST | - |
|   | /validationcheckemail  | POST | - |
|   | /findPw  | POST | - |
|   | /logout  | POST | - |
|   | /modifyuser  | POST | USER |
|   | /deleteaccount  | POST | USER |
|   | /mailCheck  | GET | - |
| /api/unit  | /getunitingredient  | GET | USER |
|   | /getallunitname  | GET | USER |
|   | /getallunit  | GET | USER |
|   | /modifyunit  | POST | USER |
|   | /addunit  | POST | USER |
| /api/ingredient  | /getallingredient  | GET | USER |
|   | /modifyingredient  | POST | USER |
|   | /addingredient  | POST | USER |
| /api/source  | /getallsource  | GET | USER |
|   | /getallsourcerecipeingredients  | GET | USER |
|   | /modifysoruce  | POST | USER |
|   | /addsoruce  | POST | USER |
|   | /modifysorucerecipe  | POST | USER |
|   | /addsorucerecipe  | POST | USER |
| /api/recipe  | /getallrecipe  | GET | USER |
|   | /getallrecipedetailingredients  | GET | USER |
|   | /ingredientsource  | GET | USER |
|   | /modifyrecipe  | POST | USER |
|   | /addrecipe  | POST | USER |
|   | /addrecipedetail  | POST | USER |
|   | /modifyrecipedetail  | POST | USER |




# 어떻게 사용하면 되나요?
 - 아직 출시 예정이지만, 간단한 이메일을 통해 회원가입을 하고서 모두 무료로 사용할 수 있어요!

