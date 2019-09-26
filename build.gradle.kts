import org.gradle.api.JavaVersion.VERSION_11

plugins {
    application
    kotlin("jvm") version "1.3.50"
    id("org.springframework.boot").version("2.1.8.RELEASE")
    id("org.jetbrains.kotlin.plugin.spring").version("1.3.50")
}

application {
    mainClassName = "org.andreych.workcalendar.MainKt"
}

allprojects {
    group = "org.andreych"
    version = "1.0-SNAPSHOT"
}

repositories {
    jcenter()
    maven("http://repo.maven.apache.org/maven2")
}

java {
    sourceCompatibility = VERSION_11
    targetCompatibility = VERSION_11
}

kotlin {
    sourceSets {
        register("src/main/kotlin")
    }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
    compile("org.jetbrains.kotlin:kotlin-reflect:1.3.50")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.0.pr3")
    compile("org.mnode.ical4j:ical4j:3.0.10")

    compile("org.springframework.boot:spring-boot-devtools:2.1.8.RELEASE")
    compile("org.springframework.boot:spring-boot-starter-jdbc:2.1.8.RELEASE")
    compile("org.springframework.boot:spring-boot-starter-web:2.1.8.RELEASE") {
        exclude("spring-boot-starter-tomcat")
    }
    compile("org.springframework.boot:spring-boot-starter-undertow:2.1.8.RELEASE")
    compile("org.springframework:spring-context-indexer:5.1.9.RELEASE")
    compile("org.springframework.boot:spring-boot-configuration-processor:2.1.8.RELEASE")

    compile("org.apache.commons:commons-collections4:4.4")
    compile("org.apache.commons:commons-lang3:3.9")

    compile("com.zaxxer:HikariCP:3.4.1")
    compile("org.flywaydb:flyway-core:6.0.4")
    compile("org.postgresql:postgresql:42.2.8")

    testCompile("org.springframework.boot:spring-boot-starter-test:2.1.8.RELEASE")
    testCompile("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:1.3.50")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}