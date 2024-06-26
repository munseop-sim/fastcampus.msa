## 서로다른 도메인 간의 join이 필요한 경우?
- 지금까지는 각각의 서비스에 API를 call해서 결과를 가져와서 데이터를 표현했다.
- 아래의 경우에는 API호출로 가능하긴 하나, 많은 API호출이 필요하다. -> 비효율적이며 대용량 데이터라면 부하증가
  - 회원의 전체 계좌현황을 알고 싶다면?
  - 일정기간동안 "충전"이 동반된 일정금액이상의 "송금"내역의 합을 알고 싶다면?

## API AGGREGATION PATTERN
- API를 호출해서 데이터를 가져오는 것이 아니라, 데이터를 가져와서 필요한 데이터를 조합해서 결과를 내는 방식
- 대용량 데이터가 아니고, 실시간 부하가 크지 않다면 조회해서 비즈니스 로직을 수행해도 괜찮다.(쉽고 직관적)
- 사용시 주의점
  - API 호출빈도 고려 
    - 비즈니스 로직이 표함된 API의 호출빈도를 파악해야함 : 자주 호출된다면 Aggregation을 위한 각 서비스의 API가 부하를 줄 수 있다.
    - 대용량 DB의 경우, 추가적으로 호출되는 API로 인해 `DB의 부하` 또는 `다른 서비스에 장애를 전파시킬수 있음`을 고려해야 한다.
  - Aggregation API의 중요도
    - 여러번의 api호출 중 한번이라도 실패시에 그 호출은 실패가 되어야 한다.(transaction) 또한 여러번의 호출로 인해 다른 서비스 API보다 긴 Latency가 발생할 수 있다.
- 쉽고 직관적이지만, 전체시스템의 영향도를 파악해야 하며, 성능과 안정성을 고려해야한다.

## CQRS PATTERN (Command, Query의 분리)
- 정의 : Command(데이터 변동)와 Query(데이터 조회)를 분리함으로써 MSA환경에서 비즈니스 로직이 포함된 API를 구현하는 패턴
- AxonFramework에서 CQRS 패턴을 프레임워크 내부에서 지원하고 있음
- `MSA 원칙 중 하나 : 모든 데이터는 데이터 ownership을 가진 서비스에서만 그 데이터를 관리해야 한다`
다만 `데이터를 변경시키는 주체는 ownership을 가진 서비스`라면 다른 서비스에서 데이터를 조회를 하기 위한 목적으로 소유하는 것은 괜찮다. 하지만 조회를 위한 데이터 서빙의 주체는 data ownership을 가진 주체 서비스이여야 한다.
- Command : 데이터 변동
- Query : 데이터 조회 or 접근 (변동 불가), Query를 위한 별도의 서비스 식별
- `API Aggregation Pattern`보다 구현이 어렵지만 `API Aggregation Pattern`의 단점을 거의 모두 극복할 수 있다. 
![axon framework에서 제공하는 CQRS](md-resource/axon-cqrs.png)

### 실습 (data-generator 모듈)
1. 고객 DummyData 삽입
2. 고객 DummyData 가 페이 서비스의 충전/감액을 하는 상황을 가정한 데이터 삽입
![img.png](md-resource/part6-01.png)