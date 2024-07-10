import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.compiler
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val modName: String by ext.properties
val modId: String by ext.properties
val modGroup: String by ext.properties
val subVersion: String by ext.properties
val repositoryLink: String by ext.properties
val kotlinVersion: String = libs.versions.kotlinVersion.get()
val annotationsVersion: String = libs.versions.annotationsVersion.get()
val coroutinesVersion: String = libs.versions.coroutinesVersion.get()
val serializationVersion: String = libs.versions.serializationVersion.get()
val modDescription: String by ext.properties

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlinVersion}")
    }
}

plugins {
    java
    kotlin("jvm") version libs.versions.kotlinVersion
    id("net.kyori.blossom") version "1.3.1"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.27"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    `maven-publish`
}

version = "$kotlinVersion.$subVersion"
group = modGroup

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(JvmVendorSpec.AZUL)
    }

    withSourcesJar()
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

minecraft {
    mcVersion.set("1.12.2")

    // MCP Mappings
    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    // Set username here, the UUID will be looked up automatically
    username.set("Developer")
}

repositories {
    maven {
        name = "CleanroomMC Maven"
        url = uri("https://maven.cleanroommc.com")
    }
    mavenLocal()
}

dependencies {
    shadow("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    shadow("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    shadow("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    shadow("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    shadow("org.jetbrains:annotations:$annotationsVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
}

blossom {
    replaceTokenIn("src/main/kotlin/io/github/chaosunity/forgelin/Forgelin.kt")
    replaceToken("@version@", "$kotlinVersion.$subVersion")
}

tasks.withType<ShadowJar> {
    archiveBaseName.set(modName)
    archiveClassifier.set("")
    archiveVersion.set("$kotlinVersion.$subVersion")
    configurations = listOf(project.configurations.shadow.get())

    exclude("module-info.class", "META-INF/maven/**", "META-INF/proguard/**", "META-INF/versions/**")

    manifest.attributes(

    )

    finalizedBy("reobfJar")
}

@Suppress("UnstableApiUsage")
tasks.withType<ProcessResources> {
    filesMatching("mcmod.info") {
        expand(
            "version" to "$kotlinVersion.$subVersion",
            "mcversion" to "1.12.2",
            "modname" to modName,
            "modid" to modId,
            "modname" to modName,
            "link" to repositoryLink,
            "description" to modDescription
        )
    }
}

tasks.withType<Jar> {
    manifest.attributes(
        "FMLCorePluginContainsFMLMod" to "true",
        "FMLCorePlugin" to "io.github.chaosunity.forgelin.preloader.ForgelinPlugin"
    )
}

tasks {
    artifacts {
        archives(shadowJar)
        shadow(shadowJar)
    }
}

idea {
    module {
        inheritOutputDirs = true
    }
    project {
        settings {
            runConfigurations {
                add(Gradle("1. Run Client").apply {
                    setProperty("taskNames", listOf("runClient"))
                })
                add(Gradle("2. Run Server").apply {
                    setProperty("taskNames", listOf("runServer"))
                })
                add(Gradle("3. Run Obfuscated Client").apply {
                    setProperty("taskNames", listOf("runObfClient"))
                })
                add(Gradle("4. Run Obfuscated Server").apply {
                    setProperty("taskNames", listOf("runObfServer"))
                })
            }
            compiler.javac {
                afterEvaluate {
                    javacAdditionalOptions = "-encoding utf8"
                    moduleJavacAdditionalOptions = mutableMapOf(
                        (project.name + ".main") to tasks.compileJava.get().options.compilerArgs.joinToString(" ") { "\"$it\"" }
                    )
                }
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = modGroup
            artifactId = modName
            version = "$kotlinVersion.$subVersion"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "CleanroomMaven"
            url = uri("https://repo.cleanroommc.com/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}
