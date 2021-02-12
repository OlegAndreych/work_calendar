import org.gradle.api.JavaVersion.VERSION_11

plugins {
    application
    kotlin("jvm") version "1.4.30"
    id("org.springframework.boot").version("2.4.2")
    id("org.jetbrains.kotlin.plugin.spring").version("1.4.30")
    id("com.github.ben-manes.versions").version("0.36.0")
}

application {
    mainClass.set("org.andreych.workcalendar.MainKt")
}

allprojects {
    group = "org.andreych"
    version = "1.0-SNAPSHOT"
}

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("com.github.ben-manes:gradle-versions-plugin:0.36.0")
    }
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = VERSION_11
    targetCompatibility = VERSION_11
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.30")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.30")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")
    implementation("org.mnode.ical4j:ical4j:3.0.18")

    implementation("org.springframework.boot:spring-boot-devtools:2.4.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.4.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.4.2") {
        exclude("spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow:2.4.2")
    implementation("org.springframework:spring-context-indexer:5.3.3")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.4.2")

    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.commons:commons-lang3:3.11")

    implementation("com.zaxxer:HikariCP:4.0.1")
    implementation("org.flywaydb:flyway-core:7.5.3")
    implementation("org.postgresql:postgresql:42.2.18")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.4.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.30")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions {
        jvmTarget = "11"
    }
}