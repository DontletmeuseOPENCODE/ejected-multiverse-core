// ─────────────────────────────────────────────────────────────────────
//  Ejected Multiverse — build.gradle.kts
//  NeoForge 1.21.1, Java 21, Mojmap, Kotlin DSL
//  Plugin: net.neoforged.moddev 2.0.141 (nowoczesny setup, NG_7.1+)
// ─────────────────────────────────────────────────────────────────────

plugins {
    id("net.neoforged.moddev") version "2.0.141"
}

version = "0.1.0-alpha"
group = "com.dontletmeuseopencode.ejectedmultiverse"

neoForge {
    version = "21.1.77"

    // Validate AT files (recommended by NeoGradle docs)
    validateAccessTransformers = true

    runs {
        register("client") { client() }
        register("server") { server() }
        register("gameTestServer") { type = "gameTestServer" }
    }

    mods {
        register("ejectedmultiverse") {
            sourceSet(sourceSets.main.get())
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}