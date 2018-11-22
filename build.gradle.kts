import org.gradle.api.JavaVersion.VERSION_11
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.10"
    id("org.springframework.boot").version("2.1.0.RELEASE")
    id("org.jetbrains.kotlin.plugin.spring").version("1.3.10")
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
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.10")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0")
    compile("org.jetbrains.kotlin:kotlin-reflect:1.3.10")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    compile("org.mnode.ical4j:ical4j:3.0.1")
    compile("org.springframework.boot:spring-boot-devtools:2.1.0.RELEASE")
    compile("org.springframework.boot:spring-boot-starter-web:2.1.0.RELEASE") {
        exclude("spring-boot-starter-tomcat")
    }
    compile("org.springframework.boot:spring-boot-starter-undertow:2.1.0.RELEASE")
    compile("org.springframework:spring-context-indexer:5.1.2.RELEASE")
    compile("org.springframework.boot:spring-boot-configuration-processor:2.1.0.RELEASE")
    testCompile("org.springframework.boot:spring-boot-starter-test:2.1.0.RELEASE")
    testCompile("com.nhaarman:mockito-kotlin:1.6.0")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:1.3.10")
    testCompile("net.bytebuddy:byte-buddy:1.9.3")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}