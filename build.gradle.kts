plugins {
    id("java-library")
}

group = "org.herolias"
version = "1.5.1"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.hytale.com/release") }
    maven { url = uri("https://maven.hytale.com/pre-release") }
}

dependencies {
    compileOnly("com.hypixel.hytale:Server:0.5.0-pre.8")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    jar {
        archiveBaseName.set("DynamicTooltipsLib")
        archiveClassifier.set("")
    }

    register<Copy>("deploy") {
        dependsOn(jar)
        from(jar.get().archiveFile)
        into("${layout.projectDirectory.dir("../server/Server/mods")}")

        doLast {
            println("Deployed ${jar.get().archiveFile.get().asFile.name} to server/Server/mods")
        }
    }
}