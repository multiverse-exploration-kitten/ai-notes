plugins {
    java
    checkstyle
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.18.0"
    id("org.flywaydb.flyway") version "7.15.0"
}

group = "com.abx"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    gradlePluginPortal()

}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.18.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")


    implementation ("com.theokanning.openai-gpt3-java:service:0.12.0")

    implementation ("org.apache.kafka:kafka-clients")
    implementation ("org.springframework.kafka:spring-kafka")
    testImplementation ("org.springframework.kafka:spring-kafka-test")

    annotationProcessor("org.immutables:value:2.9.3")
    compileOnly("org.immutables:value:2.9.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<Checkstyle>("checkstyleMain").configure {
    source = fileTree("src/main/java")
}
tasks.named<Checkstyle>("checkstyleTest").configure {
    source = fileTree("src/test/java")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release = 17
}

val checkstylePublicTask = tasks.register("checkstyle") {
    group = "verification"
    description = "Runs all Checkstyle checks."
}

tasks.withType<Checkstyle>().forEach { checkstyleTask ->
    checkstylePublicTask { dependsOn(checkstyleTask) }
}

spotless {
    isEnforceCheck = false
    java {
        palantirJavaFormat("2.28.0")
        targetExclude("**/build/generated/**")
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5432/ainotes_db"
    user = "postgres"
    password = "postgres"
    schemas = arrayOf("ai_notes")
    locations = arrayOf("classpath:db.migration")
}

