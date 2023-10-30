import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("maven-publish")
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "io.github.jarryzhou"
version = "0.1.0"

java.sourceCompatibility = JavaVersion.VERSION_17
val snakeyamlVersion: String by project



repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.postgresql:postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("de.elnarion.util:plantuml-generator-util:2.3.0")
    implementation("net.sourceforge.plantuml:plantuml:1.2021.4")
//    val kotestVersion = "4.6.3"
//    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
//    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}



