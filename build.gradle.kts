import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("plugin.jpa") version "1.4.21"
	id("io.gitlab.arturbosch.detekt").version("1.14.0")
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
	runtimeOnly("org.postgresql:postgresql")
//	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	OPEN API
	implementation("org.springdoc:springdoc-openapi-ui:1.5.2")
//	DEVTOOLS
	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	DETEKT
	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.0")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.14.0")
//	FIELD VALIDATION
	implementation("org.springframework.boot:spring-boot-starter-validation")
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
	toolVersion = "1..0"
	config = files("config/detekt/detekt.yml")
	buildUponDefaultConfig = true
	autoCorrect = true
}