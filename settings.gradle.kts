pluginManagement {
    repositories {
        maven {
            // RetroFuturaGradle
            name = "GTNH Maven"
            url = uri("https://nexus.gtnewhorizons.com/repository/public/")
            mavenContent {
                includeGroup("com.gtnewhorizons")
                includeGroup("com.gtnewhorizons.retrofuturagradle")
            }
        }
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

plugins {
    // Automatic toolchain provisioning
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlinVersion", "2.1.0")
            version("annotationsVersion", "26.0.1")
            version("coroutinesVersion", "1.9.0")
            version("serializationVersion", "1.7.3")
        }
    }
}
