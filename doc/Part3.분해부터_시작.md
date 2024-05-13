## 모듈 분리
- Global 모듈 분리
  - java로 작성
  - root grable에 include
    ``` gradle
       rootProject.name = "ms2709.pay-service"
       include(":member-service")
       include(":global")
    ```
## Dockerizing
  - Dockerizing란 무엇인가?
    - 어플리케이션을 docker build를 통해 docker image로 만들고, docker run을 통해 docker container로 실행하는 것
    - 일반적으로 Dockerfile이라는 파일에 image를 빌드하는 일련의 작업을 의미
    - CI단계에서 docker image를 만들어서 docker hub에 올리고, CD단계에서 docker image를 pull해서 docker container로 실행?
    - gradle을 활용하여 docker image를 빌드하는 방법?

  - 프로젝트의 gradle 수정
    - plugin 추가 - ```id("com.palantir.docker") version "0.36.0" ```
    - docker image 빌드 task 추가
    ```
    docker{
      println(tasks.bootJar.get().outputs.files)
      //    이미지 이름
      name=rootProject.name+"."+project.name + ":" + version
      //  도커파일 경로
      setDockerfile(file("./Dockerfile"))
      //  어떤 파일들을 Dockerfile로 복사할지 결정
      this.files(tasks.bootJar.get().outputs.files)
      //   빌드할 때 사용할 인자를 설정
      buildArgs(mapOf("JAR_FILE" to tasks.bootJar.get().outputs.files.singleFile.name))
    }
    ```

- Dockerfile (루트 프로젝트에 존재)
```dockerfile
FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```
- docker-compose.yml 파일추가
```yml

version: '3'
services:
    mysql:
        image: mysql:8.0
        networks:
            - ms2709_pay_service_network
        volumes:
            - ./db/conf.d:/etc/mysql/conf.d
            - ./db/data:/var/lib/mysql
            - ./db/initdb.d:/docker-entrypoint-initdb.d
        env_file: .env
        ports:
            - "3306:3306"
        environment:
            - TZ=Asia/Seoul
            - MYSQL_ROOT_PASSWORD=rootpassword
            - MYSQL_USER=mysqluser
            - MYSQL_PASSWORD=mysqlpw

    membership-service:
        image: ms2709.pay-service.member-service:0.0.1
        networks:
            - ms2709_pay_service_network
        ports:
            - "8081:8080"
        depends_on:
            - mysql
        environment:
            - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ms2709_pay?useSSL=false&allowPublicKeyRetrieval=true
            - SPRING_DATASOURCE_USERNAME=mysqluser
            - SPRING_DATASOURCE_PASSWORD=mysqlpw
            - SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect
            - SPRING_JPA_HIBERNATE_DDL_AUTO=update
            - SPRINGDOC_SWAGGER_UI_PATH=/swagger-ui.html
            - LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=INFO
            - LOGGING_LEVEL_ORG_HIBERNATE_SQL=DEBUG
            - LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL_BASICBINDER=TRACE

networks:
    ms2709_pay_service_network:
        driver: bridge

```
  
- 장정
  - 운영체제와 하드웨어에 독립적
  - 환경일관성
  - 격리성
  - 빠른배포와 롤백가능
- 단점
  - 러닝커브가 있다.
  - docker라는 추가적인 리소스가 필요하다.
  - 보안이슈 : 패키지하면서 보안이슈가 발생할 수 있음
  - 이미지크기 최적화에 어려움이 있다. : 레이어로 구성되는데 레이어를 잘못구성하면 이미지의 크기가 늘어날 수도 있다.
- docker-compose
  - docker image들을 이용해서 여러개의 컨테이너를 실행하고 이 컨테이너들의 관리를 위한 도구
  - 여러 컨테이너로 구성된 복잡한 애플리케이션 환경을 관리해주는 도구
  - 주로 docker-compose.yaml 파일에 정의를 하고 네트워크, 볼류 등을 정의하여 한번에 여러 컨테이너 실행
  - 제한된 환경에서 MSA를 구축하고 스터디하기에 유용

## 서비스 정의

### 회원서비스
