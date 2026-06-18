// ─────────────────────────────────────────────────────────────────────
//  Ejected Multiverse — settings.gradle.kts
//  Plugin management + repo declarations for NeoGradle
// ─────────────────────────────────────────────────────────────────────

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        maven("https://maven.neoforged.net/releases")
    }
}

rootProject.name = "ejectedmultiverse"
