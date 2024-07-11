plugins {
    id("java-library")
    id("maven-publish")
}

group = "services.noloy.nolcord"
version = "1.5.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // lombok annotations
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    // lombok for tests
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    // jetbrains annotations
    implementation("org.jetbrains:annotations:24.0.0")

    // discord bot api
    implementation("net.dv8tion:JDA:5.0.0")

    // logging
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // json parser
    implementation("com.google.code.gson:gson:2.8.9")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "services.noloy.nolcord"
            artifactId = "NolCord"
            version = "1.5.1-SNAPSHOT"


            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://repo.noloy.services/repository/nolcord/")
            credentials {
                username = extra["repoUser"].toString()
                password = extra["repoPassword"].toString()
            }
        }
    }
}