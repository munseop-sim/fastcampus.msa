# 어플리케이션 보안
어플리케이션 보안이란, 고객의 요청부터 그것을 처리하는 모든 과정 전체에 있어서 외부에 데이터를 유출하지 않고 안전하게 처리하기 위해 갖춰야 할 모든 것
1. Gateway, Proxy, Load Balancer..
   1. who?
   2. 요청에 대한 권한소유 여부
   3. 적할한 API Limit 내인지? -> DDos
   4. 허용된 IP로부터의 요청인지? -> white list 
2. Application
   1. Secure Coding
      1. Code Inspection -> 정적분석, 동적분석
   2. SQL Injection
   3. Open Source Dependency -> 오픈소스에 대한 위협요소
   4. CI / CD
3. SQL (Persistent )
   1. DB Access Control : 접근제어
   2. Encryption
   3. Backup, Restoration
## 인증 : 누구인지 확인
- 프로젝트내의 인증에 대한 실습: 요청에 대한 JWT 토큰적용
### JWT (Json Web Token)
- JWT는 JSON 객체를 사용하여 정보를 안전하게 전송하는 방법을 정의하는 개방형 표준 (RFC 7519)
- Access Token, Refresh Token

---
- 인가 : 누가 무슨 권한을 가지고있는지 확인 (요청에 대한 권한 소유 여부 확인)

- Secure SDLC (Software Development Life Cycle)
- OWASP (Open Web Application Security Project)
  - OWASP는 웹어플리케이션 보안에 대한 정보공유, 보아 취약점발견과 해결, 보안 도구 및 가이드의 개발을 목표하는 개방형 커뮤니티
  - OWASP Top 10 : 웹어플리케이션 보안 취약점 중 가장 위험한 10가지 취약점을 매년 발표

## 2023 OWASP Top 10
1. Broken Object Level Authorization (BOLA)
2. Broken Authentication
3. Broken Object Property Level Authorization (BOPA)
4. Unrestricted Resource Consumption : 클라이언트의 요청으로부터 제한된 리소스만을 사용하도록 제한 (prevent DDoS)
5. Broken Function Level Authorization (BFLA) : RBAC(Role Based Access Control)을 통해 사용자의 권한을 제어 , 일반유저가 Admin User의 권한을 가지면 안된다.
6. Unrestricted Access to Sensitive Business Flows
   - 모든 API호출에 대해 강한인증을 통해 BusinessFlow에 대한 접근을 제한 -> 추가적인 감사 로그 모니터링등을 진행
7. Server-Side Request Forgery (SSRF) : 서버에서 검증되지 않은 외부 요청을 하는 경우, 이런정보를 바탕으로 외부에서 요청을 조작하여 내부에 침투 할 수 있음
8. Security Misconfiguration : OS나 오픈소스, 시스템들의 보안 관련 속성을 적절하게 설정하지 않을 경우 보안 취약점이 야기 될 수 있다.
9. Improper Inventory Management : 조직 내의 관리되지 않는 api, 내부리소스 등을 활용해서 공격자가 취약점을 이용할 수 있다.
10. Unsafe cunsumption of APIs : 검증되지 않은 third-party library 를 활용하여 공격자가 취약점을 이용할 수 있다.


## DB 보안 (암호화)
- VAULT : HashiCorp에서 개발한 오픈소스 툴로, 보안정보를 안전하게 저장하고 접근할 수 있는 툴
  - AES의 대칭키를 Vault에 저장하고 이 저장된 키를 이용하여 데이터를 암/복호화
  - Application 기동시에 Vault를 사용하기 위한 token정보 GET
  - 얻어온 token기반, Vault에 원하는 시크릿 키를 요청
- 실습
  1. Vault에 DB암호화를 위한 대칭키 정의
  2. 정의된 키에 접근할 수 있는 VaultPolicy 생성
  3. Vault에 접근하여 대칭키를 가져오기 위해 Membership서비스 기동시 token을 발급받고, 대칭키 GET
  4. 기동된 이후 발급받은 Key를 이용해서 암/복호화 진행