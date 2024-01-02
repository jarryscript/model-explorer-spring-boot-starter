import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("maven-publish")
    id("signing")
    `java-library`
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    jacoco
}

description = ""
group = "io.github.jarryzhou"
version = "0.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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
    implementation("net.sourceforge.plantuml:plantuml:1.2023.12")

    implementation("org.springframework.boot:spring-boot-configuration-processor:3.1.5")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.1.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:4.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "model-explorer"
            version = project.version.toString()

            from(components["java"])
            withType<MavenPublication> {
                pom {
                    packaging = "jar"
                    name.set("model-explorer-spring-boot-starter")
                    description.set("Model Explorer is designed for checking domain models in runtime.")
                    url.set("https://github.com/jarryscript/model-explorer-spring-boot-starter")
                    licenses {
                        license {
                            name.set("The MIT License")
                            url.set("http://opensource.org/licenses/MIT")
                        }
                    }
                    issueManagement {
                        system.set("Github")
                        url.set("https://github.com/jarryscript/model-explorer-spring-boot-starter/issues")
                    }
                    scm {
                        connection.set("scm:git:git://github.com/jarryscript/model-explorer-spring-boot-starter.git")
                        developerConnection.set("scm:git:git@github.com/jarryscript/model-explorer-spring-boot-starter.git")
                        url.set("https://github.com/jarryscript/model-explorer-spring-boot-starter")
                    }
                    developers {
                        developer {
                            name.set("Jarry Zhou")
                            email.set("jarryzhow@qq.com")
                        }
                    }
                }
            }

        }
    }
    repositories {
        if (version.toString().endsWith("SNAPSHOT")) {
            maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
                name = "sonatypeReleaseRepository"
                credentials(PasswordCredentials::class)
            }
        } else {
            maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
                name = "sonatypeSnapshotRepository"
                credentials(PasswordCredentials::class)
            }
        }

    }
}