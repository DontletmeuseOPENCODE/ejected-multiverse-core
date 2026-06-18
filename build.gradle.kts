// ─────────────────────────────────────────────────────────────────────
//  Ejected Multiverse — build.gradle.kts
//  NeoForge 1.21.1, Java 21, Mojmap, Kotlin DSL
// ─────────────────────────────────────────────────────────────────────

plugins {
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
    id("org.spongepowered.gradle.plugin") version "0.2.1-SNAPSHOT"
    id("io.github.gleaming8.maven-publish") version "0.2.1-SNAPSHOT"
}

group = "com.dontletmeuseopencode.ejectedmultiverse"
version = "0.1.0-alpha"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

minecraft {
    version = "1.21.1"
    parchment {
        mappingsVersion = "2024.07.28"
        minecraftVersion = "1.21"
    }
    runs {
        configureEach {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.enabledGameTestNamespaces", "ejectedmultiverse")
        }
        create("client") {
            client()
        }
        create("server") {
            server()
        }
        create("gameTestServer") {
            type = "gameTestServer"
        }
    }
}

repositories {
    mavenLocal()
    maven("https://maven.neoforged.net/releases")
}

dependencies {
    implementation("net.neoforged:neoforge:21.1.77")
}
