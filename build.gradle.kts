import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

plugins {
    id("java")
    id("com.bmuschko.docker-remote-api") version "9.4.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.5.5")

    implementation("org.mongodb:mongodb-driver-sync:5.0.1")

    implementation("org.java-websocket:Java-WebSocket:1.5.6")
}

docker {
    url.set("unix:///var/run/docker.sock")
}

tasks.create("fatJar", Jar::class) {
    group = "build"

    archiveBaseName = project.name
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "com.github.ioloolo.judge.JudgeContainer"
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

    with(tasks["jar"] as CopySpec)
}

tasks.create("createImage", DockerBuildImage::class) {
    group = "docker"

    dependsOn("fatJar")

    inputDir.set(file("./"))
    images.add("judge-container")
}
