import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.palantir.docker") version "0.36.0"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

group = "ms2709.pay-service.taskconsumer"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    testRuntimeOnly("com.h2database:h2")
    implementation(project(":global"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


docker{
    println(tasks.bootJar.get().outputs.files)
//    이미지 이름
    name=rootProject.name+"."+project.name + ":" + version
//  도커파일 경로
    setDockerfile(file("../Dockerfile"))
//  어떤 파일들을 Dockerfile로 복사할지 결정
    this.files(tasks.bootJar.get().outputs.files)
//   빌드할 때 사용할 인자를 설정
    buildArgs(mapOf("JAR_FILE" to tasks.bootJar.get().outputs.files.singleFile.name))
}
