# MemberService

- port : 10010


# 서비스 정의
- 비즈니스에서 제공하는 멤버십(고객)서비스로서, 서비스에 가입하는 개인/고객 고객의 정보를 소유(ownership)하고 관련 정보의 변경에 대한 의무를 가진 서비스
- Membership(개인/법인)
  - membershipId : string
  - name : string
  - email : string
  - address : string
  - isValid : boolean
  - isCorp : boolean


## API
### 보안을 위한 API
- /membership/login 
  - 실제로 로그인을 하지는 않고 mebershipID에 대해서 JWT Token, Refresh Token을 발급
  - jwt token은 10초, refresh token은 30초로 설정
- /membership/refresh-token
  - refresh token을 활용하여 새로운 JWT Token을 발급하기 위한 API
- /membership/auth
  - jwt 토큰으로 고객을 특정하고, 인증하기 위한 API (사용자 정보 조회)