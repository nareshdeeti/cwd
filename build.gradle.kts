plugins {
    java
}

group = "com.ndeeti"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.ndeeti.CwdApplication"
    }
}

tasks.register<Exec>("buildNative") {
    dependsOn("build")
    workingDir = file("$projectDir")
    commandLine(
        "bash",
        "-c",
        "jpackage --input build/libs/ -n cwd --main-jar cwd-0.0.1-SNAPSHOT.jar --main-class com.ndeeti.cwd.CwdApplication -t rpm -d build/"
    ).doFirst {
        println("Crafting Native executable")
    }
    doLast {
        print("Executable is ready")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
