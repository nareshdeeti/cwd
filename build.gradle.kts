plugins {
    java
}

group = "com.ndeeti"
version = "0.1.0"

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
    var jpackageCommand = ""
    var shellType = "bash"
    var secondArg = "-c"
    val vendorName = "ndeeti"
    val license = "LICENSE"
    val appVersion = "0.1.0"
    var icon = "cwd.ico"
    val mainJarFileName = project.name + "-" + project.version + ".jar"
    if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
        shellType = "cmd"
        secondArg = "/c"
        jpackageCommand =
            "jpackage --input build/libs/ -n cwd --main-jar $mainJarFileName " +
                    "--main-class com.ndeeti.cwd.CwdApplication -t exe --app-version $appVersion " +
                    "--license-file $license --vendor $vendorName --icon $icon " +
                    "-d build/ --win-dir-chooser"
    } else {
        icon = "cwd.png"
        jpackageCommand =
            "jpackage --input build/libs/ -n cwd --main-jar $mainJarFileName " +
                    "--main-class com.ndeeti.cwd.CwdApplication -t rpm --app-version $appVersion " +
                    "--license-file $license --vendor $vendorName --icon $icon -d build/"
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
