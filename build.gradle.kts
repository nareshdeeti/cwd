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
    val osName = System.getProperty("os.name")
    var jpackageCommand = ""
    var shellType = "bash"
    var secondArg = "-c"
    val vendorName = "Naresh Deeti"
    if (osName.contains("windows", true)) {
        shellType = "cmd"
        secondArg = "/c"
        jpackageCommand =
            "jpackage --input build/libs/ -n cwd --main-jar cwd-0.0.1-SNAPSHOT.jar --main-class com.ndeeti.cwd.CwdApplication -t exe -d build/"
    } else {
        jpackageCommand =
            "jpackage --input build/libs/ -n cwd --main-jar cwd-0.0.1-SNAPSHOT.jar --main-class com.ndeeti.cwd.CwdApplication -t rpm -d build/"
    }
    commandLine(
        shellType, secondArg,
        jpackageCommand
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
