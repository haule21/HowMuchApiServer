plugins {
	war
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "howmuch.group"
version = "0.0.1-SNAPSHOT"
apply(plugin = "war")

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.api-client:google-api-client:2.4.0")
	implementation("com.google.oauth-client:google-oauth-client-jetty:1.37.0")
	implementation("com.google.apis:google-api-services-androidpublisher:v3-rev142-1.25.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.war {
    archiveFileName.set("howmuch.war")
}

tasks.bootWar{
	archiveFileName.value("howmuch.war")
}