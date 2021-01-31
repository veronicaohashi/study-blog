import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("plugin.jpa") version "1.4.21"
	id("io.gitlab.arturbosch.detekt").version("1.16.0-RC1")
}

group = "com.veronicaohashi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	OPEN API
	implementation("org.springdoc:springdoc-openapi-ui:1.5.2")
//	DEVTOOLS
	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	DETEKT
	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.16.0-RC1")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.16.0-RC1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
	toolVersion = "1.16.0-RC1"
	config = files("config/detekt/detekt.yml")
	buildUponDefaultConfig = true
}