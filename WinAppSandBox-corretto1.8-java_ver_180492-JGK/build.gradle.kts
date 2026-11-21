plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.BeeAndHoney06.WinAppSandBox"
version = "Dev-0.0.1-Debug"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}

application {
    mainClass.set("org.BeeAndHoney06.WinAppSandBox.Main")
}

tasks.test {
    useJUnitPlatform()
}