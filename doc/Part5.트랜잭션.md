# Transaction 관리
## `AxonFramework` 를 활용한 아키텍처 구성

![img.png](md-resource/axon_framework_refactoring01.png)
- 뱅킹, 머니 서비스를 EDA로 구성하고, 이를 AxonFramework를 활용하여 구현(using EventSourcing)
- "충전" Saga정의, Pattern 적용, Test, Monitoring


## `money-service`의 EDA refactoring
1. Axon Server Setup
2. Dependency for AxonFramework. Connect to Axon Server(MoneyService)
3. Axon Adapter 패키지 생성, EventSourcing기반, MoneyService Refactoring
4. Axon Server Admin에서 EventSourcing 확인

