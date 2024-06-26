### 뱅킹서비스
- 역할
    - 멤버 어그리거트 (mvp)
    - 계좌연동 등록정보 어그리거트(mvp)
        - RegisteredBankAccount
        - RegisteredBakAccountHistory
    - 펑뱅킹 계약 어그리거트
    - 펑뱅킹 내역 어그리거트(mvp)
        - RequestFirmBanking
        - RequestFirmBankingHistory
        - BankAccount
    - 외부은행과 입/출금요청 펌뱅킹 통신을 담당하고, 내부 고객의 계좌정보를 등록하는 서비스
- 외부은행과의 직접적인 통신을 담당하고 펌뱅킹의 계약이나 수수료 관리등 외부은행 사용과 관련된 모든 기능 제공
    - A계좌로부터 출금하여, B계좌로 입금을 하라는 요청을 받아서 은행망으로 펑뱅킹 명령 송신
    - 현제 계약되어 있는 은행들의 상태와 만료기간 등을 관리
    - 모든 펑뱅킹 명령에 대해서 기록하고, 정상적으로 완료된 명령들의 수수료 계산
    - 특정 계좌가 정상상태인지 체크하는 요청 지원
- API
  - Query
      - 입금/출금 요청(펌뱅킹) 내역 조회
      - 고객의 연동된 계좌(고객 계좌 연동정보) 조회
  - Command
      - 고객 정보에 대해 요청된 Account정보를 매핑, 연동
      - 실제 실물계좌에서의 입/출금을 요청하는 펌뱅킹 요청, 수행


## EDA refactoring
1. Axon Server Setup
2. Dependency for AxonFramework. Connect to Axon Server(BankingService)
3. Axon Adapter 패키지 생성, EventSourcing기반, Banking-Service Refactoring
4. Axon Server Admin에서 EventSourcing 확인